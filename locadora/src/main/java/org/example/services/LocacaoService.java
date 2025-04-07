package org.example.services;

import org.example.entities.Filme;
import org.example.entities.Locacao;
import org.example.entities.Usuario;

import java.util.Date;
import java.util.Set;

import static org.example.utils.DataUtils.adicionarDias;

public class LocacaoService {
	
	public Locacao alugarFilme(Usuario usuario, Set<Filme> filmes) {

		Locacao locacao = new Locacao();
		locacao.setFilmes(filmes);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());

		// Aplica desconto no valor total da locação com base no número de filmes alugados
		locacao.setValor(calculaValorComDesconto(filmes));

		//Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1);
		locacao.setDataRetorno(dataEntrega);
		
		//Salvando a locacao...	
		//TODO adicionar método para salvar
		
		return locacao;
	}


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

}