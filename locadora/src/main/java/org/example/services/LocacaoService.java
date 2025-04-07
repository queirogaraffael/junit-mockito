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

		double valorTotalFilmes = filmes.stream().mapToDouble(Filme::getPrecoLocacao).sum();
		locacao.setValor(valorTotalFilmes);

		//Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1);
		locacao.setDataRetorno(dataEntrega);
		
		//Salvando a locacao...	
		//TODO adicionar método para salvar
		
		return locacao;
	}
}