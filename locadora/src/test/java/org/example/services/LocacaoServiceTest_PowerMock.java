package org.example.services;

import org.example.entities.Filme;
import org.example.entities.Usuario;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LocacaoServiceTest_PowerMock {

    public void  deveDevolverNaSegundaAoAlugarNoSabado(){

        // Cenário
        Usuario usuario = new Usuario("Raffael", "1234567890");
        Set<Filme> filmes = new HashSet<>(Arrays.asList(new Filme("Filme 1", 2, 4.0)));

        // Ação
       // Locacao locacao = locacaoService.alugarFilme(usuario, filmes);

        // Validação
        //Assert.assertEquals(Calendar.MONDAY, );


    }
}
