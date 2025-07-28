package org.example.services;

import org.example.builders.UsuarioBuilder;
import org.example.daos.LocacaoDao;
import org.example.entities.Filme;
import org.example.entities.Locacao;
import org.example.entities.Usuario;
import org.example.exceptions.FilmeSemEstoqueException;
import org.example.exceptions.UsuarioInvalidoException;
import org.example.utils.SPCService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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

    @Test
    public void deveLancarExcecaoQuandoUsuarioForInvalido() {
        // Cenário: Usuario invalido
        Set<Filme> filmes = new HashSet<>();
        filmes.add(new Filme("O Poderoso Chefão", 16, 25.0));

        // Configura a exceção esperada e a mensagem
        exception.expect(UsuarioInvalidoException.class);
        exception.expectMessage("Usuario invalido!");

        // Ação: Deve lançar a exceção configurada
        locacaoService.alugarFilme(null, filmes);
    }

    @Test
    public void deveLancarExcecaoQuandoListaDeFilmesForNulaOuVazia() {
        // Cenário: Usuario invalido
        Usuario usuario = new Usuario("Raffael", "1234567890");

        // Configura a exceção esperada e a mensagem
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("A lista de filmes não pode estar vazia.");

        // Ação: Deve lançar a exceção configurada
        locacaoService.alugarFilme(usuario, null);
    }

    @Test
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

        // Configuração da exceção esperada
        exception.expect(FilmeSemEstoqueException.class);

        // Ação: Deve lançar a exceção configurada
        locacaoService.alugarFilme(usuario, filmes);
    }

//     Deve definir corretamente a data de devolução com base no tipo de filme (ex: filmes normais têm 3 dias, lançamentos têm 1 dia).

    //      domingo nao funciona, então a devolução nao pode ser no domingo

    //     Não deve aplicar multa se a devolução for feita dentro do prazo.

    // Deve registrar corretamente a devolução de um filme. // algum atributo boleano que valide a entrega ?

    //      Deve calcular multa corretamente caso a devolução ocorra após a data prevista. // cria
    // uma entidade para multa e associa ao usuario// pode ate se relacionar o o spc service


    //     Deve permitir que um usuário com histórico limpo alugue um novo filme.// pode ser controlado por mockito


    Usuario usuario = UsuarioBuilder.umUsuario().comNomeDe("Nome usuario").agora();

    Usuario usuario1 = UsuarioBuilder.umUsuario().comNomeDe("Nome usuario").agora();

}
