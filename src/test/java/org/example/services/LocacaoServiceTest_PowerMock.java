package org.example.services;

import org.example.daos.LocacaoDao;
import org.example.entities.Filme;
import org.example.entities.Locacao;
import org.example.entities.Usuario;
import org.example.matchers.MatchersProprios;
import org.example.utils.DataUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(PowerMockRunner.class)
@PrepareForTest({LocacaoService.class})
public class LocacaoServiceTest_PowerMock {

    @InjectMocks
    private LocacaoService locacaoService;

    @Mock
    private SPCService spc;
    @Mock
    private LocacaoDao dao;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        locacaoService = PowerMockito.spy(locacaoService);
    }

    @Test
    public void  deveDevolverNaSegundaAoAlugarNoSabado() throws Exception {

        // Cenário
        Usuario usuario = new Usuario("Raffael", "1234567890");
        Set<Filme> filmes = new HashSet<>(Arrays.asList(new Filme("Filme 1", 2, 4.0)));

        // Cenário
        // PowerMock faz sentido aqui pois -> Mockar chamadas a new (construtores)
        PowerMockito.whenNew(Date.class).withNoArguments().thenReturn(DataUtils.obterData(26,7,2025));

        Locacao locacao = locacaoService.alugarFilme(usuario, filmes);

        // Validação
        assertThat(locacao.getDataRetorno(), MatchersProprios.caiNumaSegunda());

    }
}
