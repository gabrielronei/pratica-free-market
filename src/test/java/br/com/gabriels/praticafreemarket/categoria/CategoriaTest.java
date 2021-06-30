package br.com.gabriels.praticafreemarket.categoria;

import org.junit.jupiter.api.*;

class CategoriaTest {

    @Test
    void hashCode__devem_ser_iguais() {
        Categoria mobile = new Categoria("Mobile");
        Categoria mobileDois = new Categoria("Mobile");

        Assertions.assertEquals(mobile, mobileDois);
        System.out.println(mobile.hashCode());
        System.out.println(mobileDois.hashCode());
    }

    @Test
    void hashCode__devem_ser_diferentes() {
        Categoria mobile = new Categoria("Mobile");
        Categoria android = new Categoria("Android");

        Assertions.assertNotEquals(mobile, android);
        System.out.println(mobile.hashCode());
        System.out.println(android.hashCode());
    }

}