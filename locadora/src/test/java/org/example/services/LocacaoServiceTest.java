package org.example.services;

import org.example.entities.Filme;
import org.example.entities.Locacao;
import org.example.entities.Usuario;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

public class LocacaoServiceTest {

    @Rule
    public ErrorCollector error = new ErrorCollector();

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
        LocacaoService locacaoService = new LocacaoService();

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
                new Filme("O Poderoso Chefão", 1
                        6, 25.0),
                new Filme("A Origem", 14, 22.0)
        ));
        Usuario usuario = new Usuario("Raffael", "1234567890");
        LocacaoService locacaoService = new LocacaoService();

        // Ação
        Locacao locacao = locacaoService.alugarFilme(usuario, filmes);

        // Validações
        error.checkThat("O valor da locação está incorreto", locacao.getValor(), is(37.6));
    }


    //


    /* 1. Testes de Criação de Locação

         // domingo nao funciona


    Deve permitir que um usuário alugue um filme disponível.

    Não deve permitir locação se o usuário for inválido.

    Não deve permitir locação se o filme não estiver disponível.

    Deve registrar a data correta de início e fim da locação.

    Deve calcular corretamente o preço do aluguel.

2. Testes de Regras de Negócio

    Deve aplicar corretamente um desconto caso existam promoções (ex: na locação de múltiplos filmes).

    Deve definir corretamente a data de devolução com base no tipo de filme (ex: filmes normais têm 3 dias, lançamentos têm 1 dia).

     Deve impedir que um usuário alugue um filme caso tenha locações pendentes ou atrasadas.

    Deve permitir que um usuário com histórico limpo alugue um novo filme.

3. Testes de Devolução e Multas

     Deve calcular multa corretamente caso a devolução ocorra após a data prevista.

     Deve registrar corretamente a devolução de um filme.

    Não deve aplicar multa se a devolução for feita dentro do prazo.

4. Testes de Exceções e Erros

     Deve lançar uma exceção se um filme não existir.

     Deve lançar uma exceção se um usuário não existir.

     Deve lançar uma exceção se um usuário tentar alugar mais filmes do que o permitido.*?

    */

}
