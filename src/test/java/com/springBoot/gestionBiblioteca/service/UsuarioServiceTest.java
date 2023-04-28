package com.springBoot.gestionBiblioteca.service;


import com.springBoot.gestionBiblioteca.model.Libro;
import com.springBoot.gestionBiblioteca.model.Usuario;
import com.springBoot.gestionBiblioteca.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UsuarioServiceTest {

    @Mock
    private LibroService libroService;

    @Mock
    private PrestamoService prestamoService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioServiceImp usuarioService;

    @Test
    public void prestarLibroTest() {
        Integer id = 1;
        String documento = "12345678";
        Libro libro = new Libro("a", "e");
        Usuario usuario = new Usuario();

        when(libroService.estado(id)).thenReturn(libro);
        when(usuarioRepository.buscarConDocumento(documento)).thenReturn(usuario);

        Usuario result = usuarioService.prestarLibro(id, documento);

        verify(prestamoService, times(1)).registro(documento, libro);
        verify(usuarioRepository, times(1)).buscarConDocumento(documento);
        assertEquals(usuario, result);
    }
}
