package com.springBoot.gestionBiblioteca.controller;

import com.springBoot.gestionBiblioteca.model.Libro;
import com.springBoot.gestionBiblioteca.service.LibroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
@WebMvcTest(LibroController.class)
class LibroControllerTest {

    @Test
    void disponibles() {
    }
        @Mock
        private LibroService servicio;

        @InjectMocks
        private LibroController controlador;

        @BeforeEach
        public void setUp() {
            MockitoAnnotations.initMocks(this);
        }

        @Test
        public void testDisponibles() throws Exception {
            List<Libro> libros = new ArrayList<>();
            libros.add(new Libro("NEGRO","PRESTADO"));
            libros.add(new Libro("andres","lugo"));
            when(servicio.disponibles()).thenReturn(libros);

            ResponseEntity<?> resultado = controlador.disponibles();

            assertEquals(HttpStatus.NOT_FOUND, resultado.getStatusCode());
            //assertEquals(HttpStatus.NOT_FOUND, resultado.getStatusCode());
        }

}