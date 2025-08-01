package org.example.calculadora;

import org.example.exceptions.NaoPodeDividirPorZeroException;
import org.example.outros.Calculadora;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

public class CalculadoraMockTest {

    @Mock
    private Calculadora calculadoraMock;

    @Spy
    private Calculadora calculadoraSpy;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    // Mock -> Objeto falso
    @Test
    public void testeCalculadoraMock(){

        // Cenário
        Mockito.when(calculadoraMock.somar(1.0,2.0)).thenReturn(5.0);

        // Ação
        Double resultado = calculadoraMock.somar(1.0,2.0);

        // Validação
        Assert.assertEquals(5.0, resultado, 0.0001);
    }

    // Spy -> Objeto real
    @Test(expected = NaoPodeDividirPorZeroException.class)
    public void testeCalculadoraSpy(){

        // Cenário
        // O objeto já iria ter esse comportamento por padrão.
        Mockito.doThrow(NaoPodeDividirPorZeroException.class).when(calculadoraSpy).dividir(2.0, 0.0);

        // Ação
        Double resultado = calculadoraSpy.dividir(2.0, 0.0);

    }
}
