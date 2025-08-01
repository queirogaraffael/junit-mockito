package org.example.services;

import org.example.builders.FilmeBuilder;
import org.example.builders.UsuarioBuilder;
import org.example.daos.LocacaoDao;
import org.example.entities.Filme;
import org.example.entities.Locacao;
import org.example.entities.Usuario;
import org.example.exceptions.FilmeSemEstoqueException;
import org.example.exceptions.LocadoraException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

@RunWith(Parameterized.class)
public class CalculoValorLocacaoTest {

    @InjectMocks
    private LocacaoService service;

    @Mock
    private LocacaoDao locacaoDao;

    @Mock
    private SPCService spcService;

    @Parameter
    public Set<Filme> filmes;

    @Parameter(value = 1)
    public Double valor;

    @Parameter(value = 2)
    public String cenario;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    private static Filme filme1 = new Filme("Filme 1", 10, 4.0);
    private static Filme filme2 = new Filme("Filme 2", 10, 4.0);
    private static Filme filme3 = new Filme("Filme 3", 10, 4.0);
    private static Filme filme4 = new Filme("Filme 4", 10, 4.0);
    private static Filme filme5 = new Filme("Filme 5", 10, 4.0);
    private static Filme filme6 = new Filme("Filme 6", 10, 4.0);

    @Parameters(name="{2}") // Esse 2 diz pros testes qual parametro mostrar, para diferenciar o teste. Nesse caso é o cenário.
    public static Collection<Object[]> getParametros(){
        return Arrays.asList(new Object[][] {
                {new HashSet<>(Collections.singletonList(filme1)), 4.0, "1 Filme: Sem Desconto"},
                {new HashSet<>(Arrays.asList(filme1, filme2)), 6.4, "2 Filmes: Desconto de 20%"},
                {new HashSet<>(Arrays.asList(filme1, filme2, filme3)), 8.4, "3 Filmes: Desconto de 30%"},
                {new HashSet<>(Arrays.asList(filme1, filme2, filme3, filme4)), 9.6, "4 Filmes: Desconto de 40%"},
                {new HashSet<>(Arrays.asList(filme1, filme2, filme3, filme4, filme5)), 10.0, "5 Filmes: Desconto de 50%"},
                {new HashSet<>(Arrays.asList(filme1, filme2, filme3, filme4, filme5, filme6)), 12.0, "6 Filmes: Desconto de 50%"}
        });
    }


    @Test
    public void deveCalcularValorLocacaoConsiderandoDescontos() throws FilmeSemEstoqueException, LocadoraException {

        // Cenário
        Usuario usuario = UsuarioBuilder.umUsuario().agora();

        // Ação
        Locacao locacao = service.alugarFilme(usuario, filmes);

        // Validação
        Assert.assertEquals(valor, locacao.getValor(), 0.0001);

    }


}
