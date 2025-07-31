package org.example.builders;

import org.example.entities.Filme;

public class FilmeBuilder {

    private String nome;
    private Integer estoque;
    private Double precoLocacao;

    private FilmeBuilder() {}

    public static FilmeBuilder umFilme() {
        FilmeBuilder builder = new FilmeBuilder();
        builder.nome = "Filme Padrão";
        builder.estoque = 2;
        builder.precoLocacao = 4.0;
        return builder;
    }

    public static FilmeBuilder umFilmeSemEstoque() {
        return umFilme().semEstoque();
    }

    public FilmeBuilder comNome(String nome) {
        this.nome = nome;
        return this;
    }

    public FilmeBuilder semEstoque() {
        this.estoque = 0;
        return this;
    }

    public FilmeBuilder comValorDe(Double valor) {
        this.precoLocacao = valor;
        return this;
    }

    public Filme build() {
        Filme filme = new Filme();
        filme.setNome(this.nome);
        filme.setEstoque(this.estoque);
        filme.setPrecoLocacao(this.precoLocacao);
        return filme;
    }
}