package org.example.outros;

import org.example.exceptions.NaoPodeDividirPorZeroException;

public class Calculadora {

    public Calculadora(){
    }

    public Double somar(Double numero1, Double numero2){
        return numero1 + numero2;
    }

    public Double subtrair(Double numero1, Double numero2){
        return numero1 - numero2;
    }

    public Double dividir(Double numero1, Double numero2) throws NaoPodeDividirPorZeroException{
        if(numero2 == 0){
            throw new NaoPodeDividirPorZeroException("Nao pode dividir por zero");
        }

        return numero1/numero2;
    }

    public Double multiplicar(Double numero1, Double numero2){
        return numero1 * numero2;
    }

}
