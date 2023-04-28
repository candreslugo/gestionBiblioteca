package com.springBoot.gestionBiblioteca.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.springBoot.gestionBiblioteca.model.Libro;
import com.springBoot.gestionBiblioteca.repository.LibroRepository;


public class LibroServiceTest {

    @Mock
    private LibroRepository libroRepositoryMock;

    @InjectMocks
    private LibroService libroService = new LibroServiceImp(libroRepositoryMock);

    private Libro libro;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        libro = new Libro("","");
        libro.setId(1);
        libro.setNombre("matrix");
    }

    @Test
    public void testDisponibles() throws Exception {
        List<Libro> libros = Arrays.asList(libro);
        when(libroRepositoryMock.disponibles()).thenReturn(libros);

        List<Libro> result = libroService.disponibles();

        assertEquals(1, result.size());
        assertEquals(libro, result.get(0));
    }

    @Test
    public void testEstado() throws Exception {
        when(libroRepositoryMock.findById(1)).thenReturn(Optional.ofNullable(libro));

        Libro result = libroService.estado(1);

        assertEquals(libro, result);
    }
}
