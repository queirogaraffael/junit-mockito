package org.example;

import org.example.daos.LocacaoDao;
import org.example.entities.Filme;
import org.example.entities.Locacao;
import org.example.entities.Usuario;
import org.example.services.LocacaoService;
import org.example.daos.LocacaoDaoFake;
import org.example.services.SPCService;
import org.example.services.SPCServiceFake;

import java.util.Arrays;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        Filme filme1 = new Filme("Filme 1", 2, 4.0);
        Filme filme2 = new Filme("Filme 2", 2, 4.0);

        Usuario usuario = new Usuario("Raffael", "1234567890");

        LocacaoDao dao = new LocacaoDaoFake();
        SPCService spc = new SPCServiceFake();

        LocacaoService service = new LocacaoService(dao, spc);

        Locacao locacao = service.alugarFilme(usuario, new HashSet<>(Arrays.asList(filme1, filme2)));

        System.out.println("Valor da locação: " + locacao.getValor());
    }
}

