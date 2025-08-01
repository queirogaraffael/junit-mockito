package org.example.calculadora;

import org.example.entities.Usuario;
import org.junit.Assert;
import org.junit.Test;

public class AssertTest {

    @Test
    public void test() {

        //Assert.assertTrue(false); // Vai falhar.
        Assert.assertFalse(false);

        Usuario usuario1 = new Usuario("Raffael", "12345678901");
        Usuario usuario2 = new Usuario("Raffael", "12345678901");
        Usuario usuario4 = new Usuario();

        Assert.assertEquals(usuario1, usuario2); // Avalia apenas o "conteudo", que nesse caso é o cpf(implementado no hashcode e equals)
        Assert.assertNotEquals(usuario1, usuario4);

        // Assert.assertSame(usuario1, usuario2); // vai falhar, pois não são o mesmo objeto
        Assert.assertNotSame(usuario1, usuario2);
        Assert.assertNotSame(usuario1, usuario2);

        Usuario usuario3 = usuario1;

        Assert.assertSame(usuario1, usuario3); // Aponta pro mesmo local da memória, então é o mesmo obejeto.

        usuario3 = null;

        Assert.assertNull(usuario3);
        Assert.assertNotNull(usuario1);

    }
}
