package org.example.services;

import org.example.builders.UsuarioBuilder;
import org.example.daos.LocacaoDao;
import org.example.entities.Filme;
import org.example.entities.Locacao;
import org.example.entities.Multa;
import org.example.entities.Usuario;
import org.example.exceptions.FilmeSemEstoqueException;
import org.example.exceptions.ListaDeFilmesVaziaException;
import org.example.exceptions.UsuarioInvalidoException;
import org.example.exceptions.UsuarioNegativadoSPC;
import org.example.utils.DataUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class LocacaoServiceTest {

    @Mock
    private LocacaoDao locacaoDao;

    @Mock
    private SPCService spcService;

    @InjectMocks
    private LocacaoService locacaoService;

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void alugaFilmesComSucessoTest() {
        // Cenário
        Set<Filme> filmes = new HashSet<>(Arrays.asList(
                new Filme("Titanic", 10, 15.0),
                new Filme("Interestelar", 12, 20.0),
                new Filme("Clube da Luta", 18, 18.5),
                new Filme("O Poderoso Chefão", 16, 25.0),
                new Filme("A Origem", 14, 22.0)
        ));
        Usuario usuario = new Usuario("Raffael", "1234567890");

        // Ação
        Locacao locacao = locacaoService.alugarFilme(usuario, filmes);

        // Validações usando ErrorCollector
        error.checkThat("A locação não deveria ser nula", locacao, notNullValue());
        error.checkThat("O valor da locação está incorreto", locacao.getValor(), is(50.25));
        error.checkThat("O usuário da locação está incorreto", locacao.getUsuario(), is(usuario));
        error.checkThat("A quantidade de filmes na locação está incorreta", locacao.getFilmes().size(), is(5));
    }

    @Test
    public void alugarFilmesComDescontoTest() {
        // Cenário
        Set<Filme> filmes = new HashSet<>(Arrays.asList(
                new Filme("O Poderoso Chefão", 16, 25.0),
                new Filme("A Origem", 14, 22.0)
        ));
        Usuario usuario = new Usuario("Raffael", "1234567890");

        // Ação
        Locacao locacao = locacaoService.alugarFilme(usuario, filmes);

        // Validações
        assertThat("O valor da locação está incorreto", locacao.getValor(), is(37.6));
    }

    @Test(expected = UsuarioInvalidoException.class)
    public void deveLancarExcecaoQuandoUsuarioForInvalido() {

        // Cenário: Usuario invalido
        Set<Filme> filmes = new HashSet<>();
        filmes.add(new Filme("O Poderoso Chefão", 16, 25.0));

        // Ação: Deve lançar a exceção configurada
        locacaoService.alugarFilme(null, filmes);

    }

    @Test(expected = ListaDeFilmesVaziaException.class)
    public void deveLancarExcecaoQuandoListaDeFilmesForNulaOuVazia() {
        // Cenário: Apenas usuario valido
        Usuario usuario = new Usuario("Raffael", "1234567890");

        // Ação: Deve lançar a exceção configurada
        locacaoService.alugarFilme(usuario, null);
    }

    @Test(expected = FilmeSemEstoqueException.class)
    public void deveLancarExcecaoQuandoHouverFilmesSemEstoque() {
        // Cenário: Criação do conjunto de filmes, incluindo alguns sem estoque
        Set<Filme> filmes = new HashSet<>(Arrays.asList(
                new Filme("Titanic", 0, 15.0),
                new Filme("Interestelar", 0, 20.0),
                new Filme("Clube da Luta", 18, 18.5),
                new Filme("O Poderoso Chefão", 16, 25.0),
                new Filme("A Origem", 14, 22.0)
        ));
        Usuario usuario = new Usuario("Raffael", "1234567890");

        // Ação: Deve lançar a exceção configurada
        locacaoService.alugarFilme(usuario, filmes);
    }


    @Test(expected = UsuarioNegativadoSPC.class)
    public void naoDeveAlugarFilmeParaNegativadoSPC(){

        // Cenário
        Usuario usuario = UsuarioBuilder.umUsuario().agora();
        Set<Filme> filmes = new HashSet<>(Arrays.asList(
           new Filme("Filme 1", 2, 4.0),
                new Filme("Filme 2", 2, 4.0)));

        Mockito.when(spcService.possuiNegativacao(usuario)).thenReturn(true);

        // Ação
        locacaoService.alugarFilme(usuario, filmes);
    }

    @Test
    public void deveProrrogarUmaLocacao(){
        // Cenário
        Usuario usuario = new Usuario("Raffael", "1234567890");
        Set<Filme> filmes = new HashSet<>(Arrays.asList(new Filme("Filme 1", 2, 4.0)));

        Date dataInicial = new Date();
        Locacao locacao = locacaoService.alugarFilme(usuario, filmes);

        // Ação
        Locacao locacaoComDataRetornoAlterada = locacaoService.prorrogarLocacao(locacao, 4);

        // Validação
        Date dataEsperada = DataUtils.adicionarDias(dataInicial, 4);
        Assert.assertTrue(DataUtils.isMesmaData(dataEsperada, locacaoComDataRetornoAlterada.getDataRetorno()));
    }

    @Test
    public void deveAplicarMultaCorretamente() {
        // Cenário
        Usuario usuario = new Usuario("Raffael", "1234567890");
        Set<Filme> filmes = new HashSet<>(Arrays.asList(
                new Filme("Filme 1", 2, 4.0)
        ));

        Locacao locacao = locacaoService.alugarFilme(usuario, filmes);

        Date dataLocacao = DataUtils.obterDataComDiferencaDias(-3);
        Date dataRetornoAtrasada = DataUtils.obterDataComDiferencaDias(-1);

        locacao.setDataLocacao(dataLocacao);
        locacao.setDataRetorno(dataRetornoAtrasada);

        // Ação
        Optional<Multa> multa = locacaoService.devolverLocacao(locacao);

        // Validação
        Assert.assertTrue("A multa deveria estar presente", multa.isPresent());
        Assert.assertEquals("Valor da multa incorreto",
                locacao.getUsuario().getMulta().getValor(),
                multa.get().getValor(),
                0.01);
    }


}
