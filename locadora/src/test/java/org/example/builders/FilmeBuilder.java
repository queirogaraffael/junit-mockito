package org.example.builders;

import org.example.entities.Filme;

// Apos uma longa investigação, descobri que o FilmeBuilder tem apresentado problemas
// Até o momento não consegui descobrir o que tem causado o erro, mas sei que se encontra nessa classe.
public class FilmeBuilder {

    private Filme filme;

    private FilmeBuilder() {
    }

    public static FilmeBuilder umFilme() {
        FilmeBuilder builder = new FilmeBuilder();
        builder.filme = new Filme();
        builder.filme.setNome("Filme");
        builder.filme.setEstoque(2);
        builder.filme.setPrecoLocacao(4.0);

        return builder;
    }

    public static FilmeBuilder umFilmeSemEstoque() {
        FilmeBuilder builder = new FilmeBuilder();
        builder.filme = new Filme();
        builder.filme.setNome("Filme");
        builder.filme.setEstoque(0);
        builder.filme.setPrecoLocacao(4.0);
        return builder;
    }

    public FilmeBuilder semEstoque() {
        filme.setEstoque(0);
        return this;
    }

    public FilmeBuilder comValorDe(Double valor) {
        filme.setPrecoLocacao(valor);
        return this;
    }

    public Filme agora() {
        return filme;
    }

}
