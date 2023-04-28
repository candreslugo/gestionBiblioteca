package com.springBoot.gestionBiblioteca.service;

import com.springBoot.gestionBiblioteca.model.Libro;
import com.springBoot.gestionBiblioteca.model.Status;
import com.springBoot.gestionBiblioteca.repository.LibroRepository;
import com.springBoot.gestionBiblioteca.repository.StatusRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LibroServiceImpTest {

    @Mock
    private LibroRepository libroRepository;

    @Mock
    private StatusRepository statusRepository;

    @InjectMocks
    private LibroServiceImp libroServiceImp;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDisponibles() {

        List<Libro> libros = new ArrayList<>();
        libros.add(new Libro("1", "Libro 1 prestado"));
        libros.add(new Libro( "2","libro 2 prestado"));
        when(libroRepository.disponibles()).thenReturn(libros);

        List<Libro> disponibles = libroServiceImp.disponibles();

        assertNotNull(disponibles);
        assertEquals(1, disponibles.size());
        assertEquals("Libro 1", disponibles.get(0).getNombre());
        assertEquals("Disponible", disponibles.get(0).getStatus());
    }

    @Test
    public void testEstado() {

        Integer id = 1;
        Libro libroPrestado = new Libro("1", "Libro 1");
        when(libroRepository.existsById(id)).thenReturn(true);
        when(libroRepository.findById(id)).thenReturn(Optional.of(libroPrestado));
        when(statusRepository.findById(1)).thenReturn(Optional.of(new Status()));
        when(statusRepository.findById(2)).thenReturn(Optional.of(new Status()));

        Libro libroDevuelto = libroServiceImp.estado(id);

        // Then
        assertNotNull(libroDevuelto);
        assertEquals("Libro 1", libroDevuelto.getNombre());
        assertEquals("Disponible", libroDevuelto.getStatus());
        verify(libroRepository, times(1)).save(libroDevuelto);
    }

    @Test
    public void testSave() throws Exception {
        // Given
        Libro libro = new Libro( "Libro 1", "Disponible");
        when(statusRepository.findById(1)).thenReturn(Optional.of(new Status()));
        when(libroRepository.save(libro)).thenReturn(libro);

        // When
        Libro savedLibro = libroServiceImp.save(libro);

        // Then
        assertNotNull(savedLibro);
        assertEquals("Libro 1", savedLibro.getNombre());
        assertEquals("Disponible", savedLibro.getStatus());
        verify(libroRepository, times(1)).save(libro);
    }

}