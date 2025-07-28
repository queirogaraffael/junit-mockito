package org.example.services;

import org.example.daos.LocacaoDao;
import org.example.entities.Filme;
import org.example.entities.Locacao;
import org.example.entities.Usuario;
import org.example.exceptions.FilmeSemEstoqueException;
import org.example.exceptions.UsuarioInvalidoException;
import org.example.utils.SPCService;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import static org.example.utils.DataUtils.adicionarDias;

public class LocacaoService {

	private LocacaoDao locacaoDao;
	private SPCService spcService;

	public LocacaoService(LocacaoDao locacaoDao, SPCService spcService) {
		this.locacaoDao = locacaoDao;
		this.spcService = spcService;
	}

	public Locacao alugarFilme(Usuario usuario, Set<Filme> filmes) {

		if(usuario == null){
			throw new UsuarioInvalidoException("Usuario invalido!");
		}

		if(filmes == null || filmes.isEmpty()){
			throw new IllegalArgumentException("A lista de filmes não pode estar vazia.");
		}

		// Valida se ha estoque para o(s) filme(s)
		validaSeHaEstoque(filmes);

		Locacao locacao = new Locacao();
		locacao.setFilmes(filmes);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());

		//Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1); // TODO se for num domingo, deve colocar pra segunda
		locacao.setDataRetorno(dataEntrega);

		// Aplica desconto no valor total da locação com base no número de filmes alugados
		locacao.setValor(calculaValorComDesconto(filmes));

		//Salvar locacao
		locacaoDao.salva(locacao);

		return locacao;
	}


	// TODO: Não deve aplicar multa se a devolução for feita dentro do prazo
	// Um novo metodo para devolução// deve receber uma locação ?


	private double calculaValorComDesconto(Set<Filme> filmes){

		double total = filmes.stream().mapToDouble(Filme::getPrecoLocacao).sum();

		double desconto;

		if(filmes.size() >= 5){
			desconto = 0.5;
		}else if(filmes.size() == 4){
			desconto = 0.6;
		}else if(filmes.size() == 3){
			desconto = 0.7;
		}else if(filmes.size() == 2){
			desconto = 0.8;
		}else {
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

}