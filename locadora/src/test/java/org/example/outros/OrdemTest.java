package org.example.outros;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) // JVM, DEFAULT
public class OrdemTest {

    // testeA vai ser executado primeiro que o testeB

    @Test
    public void testeB(){
        System.out.println("Teste B");
    }

    @Test
    public void testeA(){
        System.out.println("Teste A");
    }

}
