package com.springBoot.gestionBiblioteca;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

class SumaTest {

    @Test
    void obtenerSuma() {
        Suma suma = new Suma();
        int resultado = suma.obtenerSuma(4,6);
        int esperado = 5;
        assertNotEquals(esperado,resultado);
    }
}