package org.example.builders;

import org.example.entities.Filme;
import org.example.entities.Locacao;
import org.example.entities.Usuario;
import org.example.utils.DataUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

public class LocacaoBuilder {

    private Locacao locacao;

    private LocacaoBuilder() {
    }

    public static LocacaoBuilder umaLocacao() {
        LocacaoBuilder builder = new LocacaoBuilder();
        inicializarDadosPadroes(builder);
        return builder;
    }

    public static void inicializarDadosPadroes(LocacaoBuilder builder) {
        builder.locacao.setUsuario(UsuarioBuilder.umUsuario().agora());
        builder.locacao.setDataLocacao(new Date());
        builder.locacao.setDataRetorno(DataUtils.obterDataComDiferencaDias(2));
        builder.locacao.setValor(4.0);
        builder.locacao.setFilmes(new HashSet<>(Arrays.asList(FilmeBuilder.umFilme().build())));
    }

    public LocacaoBuilder comUsuario(Usuario usuario) {
        locacao.setUsuario(usuario);
        return this;
    }

    public LocacaoBuilder comListaDeFilmes(Filme... filmes) {
        locacao.setFilmes(new HashSet<>(Arrays.asList(filmes)));
        return this;
    }

    public LocacaoBuilder comDataDeLocacao(Date dataLocacao) {
        locacao.setDataLocacao(dataLocacao);
        return this;
    }

    public LocacaoBuilder comDataDeRetorno(Date dataRetorno) {
        locacao.setDataRetorno(dataRetorno);
        return this;
    }

    public LocacaoBuilder atrasada() {
        locacao.setDataRetorno(DataUtils.obterDataComDiferencaDias(-4));
        locacao.setDataRetorno(DataUtils.obterDataComDiferencaDias(-2));
        return this;
    }

    public LocacaoBuilder comValor(Double valor) {
        locacao.setValor(valor);
        return this;
    }

    public Locacao agora() {
        return locacao;
    }

}
