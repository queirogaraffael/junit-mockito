package org.example.outros;

import org.example.exceptions.NaoPodeDividirPorZeroException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalculadoraTest {

    private Calculadora calculadora;

    @Before
    public void setup(){
        calculadora = new Calculadora();
    }

    @Test
    public void deveSomarDoisValores(){

        // Cenário
        double a = 1.0;
        double b = 2.0;

        // Ação
        double soma = calculadora.somar(a, b);

        // Validação
        Assert.assertEquals(3.0, soma, 0.1);
    }

    @Test
    public void deveSubtrairDoisValores(){
        // Cenário
        double a = 1.0;
        double b = 2.0;

        // Ação
        double resultado = calculadora.subtrair(a, b);

        // Validação
        Assert.assertEquals(-1.0, resultado, 0.1);
    }

    @Test
    public void deveDividirDoisValores() throws NaoPodeDividirPorZeroException{
        // Cenário
        double a = 1.0;
        double b = 2.0;

        // Ação
        double resultado = calculadora.dividir(a, b);

        // Validação
        Assert.assertEquals(0.5, resultado, 0.01);
    }

    @Test(expected = NaoPodeDividirPorZeroException.class)
    public void deveLancarExcecaoAoDividirPorZero() throws NaoPodeDividirPorZeroException {
        // Cenário
        double a = 2.0;
        double b = 0.0;

        // Ação
        double resultado = calculadora.dividir(a, b);
    }

    @Test
    public void deveMultiplicar(){
        // Cenário
        double a = 5.0;
        double b = 2.0;

        // Ação
        double resultado = calculadora.multiplicar(a, b);

        // Validação
        Assert.assertEquals(10.0, resultado, 0.1);
    }

}
