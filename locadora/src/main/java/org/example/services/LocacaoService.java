package org.example.services;

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

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class LocacaoService {

    private LocacaoDao locacaoDao;
    private SPCService spcService;

    private Double valorBaseMulta = 10.0;

    public LocacaoService(LocacaoDao locacaoDao, SPCService spcService) {
        this.locacaoDao = locacaoDao;
        this.spcService = spcService;
    }

    public Locacao alugarFilme(Usuario usuario, Set<Filme> filmes) {

        if (usuario == null) {
            throw new UsuarioInvalidoException("Usuario invalido!");
        }

        if (filmes == null || filmes.isEmpty()) {
            throw new ListaDeFilmesVaziaException("A lista de filmes não pode estar vazia.");
        }


        boolean usuarioEhNetivado = spcService.possuiNegativacao(usuario);

        if (usuarioEhNetivado) {
            throw new UsuarioNegativadoSPC("Usuário negativado pelo SPC");
        }

        // Valida se ha estoque para o(s) filme(s)
        validaSeHaEstoque(filmes);

        Locacao locacao = new Locacao();
        locacao.setFilmes(filmes);
        locacao.setUsuario(usuario);
        locacao.setDataLocacao(new Date());

        //Entrega no dia seguinte
        Date dataEntrega = DataUtils.obterDataDeRetorno(2);
        locacao.setDataRetorno(dataEntrega);

        // Aplica desconto no valor total da locação com base no número de filmes alugados
        locacao.setValor(calculaValorComDesconto(filmes));

        //Salvar locacao
        locacaoDao.salvar(locacao);

        return locacao;
    }


    public Optional<Multa> devolverLocacao(Locacao locacao) {

        if (houveAtraso(locacao)) {
            double valorMulta = calculaValorMulta(locacao);

            Multa multa = new Multa(valorMulta);

            locacao.getUsuario().setMulta(multa);

            return Optional.of(multa);
        }

        return Optional.empty();
    }

    public Locacao prorrogarLocacao(Locacao locacao, int numeroDeDiasParaProrrogar) {

        Date novaDataDeRetorno = DataUtils.obterDataDeRetorno(numeroDeDiasParaProrrogar);

        locacao.setDataRetorno(novaDataDeRetorno);

        locacaoDao.salvar(locacao);

        return locacao;
    }

    private double calculaValorMulta(Locacao locacao) {

        int numeroDeDomingos = DataUtils.quantosDiasHa(Calendar.SUNDAY, locacao.getDataLocacao(), locacao.getDataRetorno());

        long numeroDeDiasValidosLocacao = DataUtils.calculaDiferencaDeDias(locacao.getDataLocacao(), locacao.getDataRetorno()) - numeroDeDomingos;

        return valorBaseMulta * numeroDeDiasValidosLocacao + locacao.getValor();

    }

    private double calculaValorComDesconto(Set<Filme> filmes) {

        double total = filmes.stream().mapToDouble(Filme::getPrecoLocacao).sum();

        double desconto;

        if (filmes.size() >= 5) {
            desconto = 0.5;
        } else if (filmes.size() == 4) {
            desconto = 0.6;
        } else if (filmes.size() == 3) {
            desconto = 0.7;
        } else if (filmes.size() == 2) {
            desconto = 0.8;
        } else {
            desconto = 1;
        }

        return total * desconto;
    }

    private void validaSeHaEstoque(Set<Filme> filmes) {
        Set<Filme> filmesSemEstoque = filmes.stream()
                .filter(filme -> filme.getEstoque() == 0)
                .collect(Collectors.toSet());

        if (!filmesSemEstoque.isEmpty()) {
            throw new FilmeSemEstoqueException("Os seguintes filmes estão sem estoque: "
                    + filmesSemEstoque.stream().map(Filme::getNome).collect(Collectors.joining(", ")));
        }
    }


    private boolean houveAtraso(Locacao locacao) {
        return locacao.getDataRetorno().before(new Date());
    }

}