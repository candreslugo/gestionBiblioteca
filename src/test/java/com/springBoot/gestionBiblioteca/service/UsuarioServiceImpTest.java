package com.springBoot.gestionBiblioteca.service;


import com.springBoot.gestionBiblioteca.model.Libro;
import com.springBoot.gestionBiblioteca.model.Usuario;
import com.springBoot.gestionBiblioteca.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
        import org.mockito.InjectMocks;
        import org.mockito.Mock;
        import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UsuarioServiceImpTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private LibroService libroService;

    @Mock
    private PrestamoService prestamoService;

    @InjectMocks
    private UsuarioServiceImp usuarioService;

    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void prestarLibroTest() {
        // Configurar comportamiento de los mocks
        Libro libroMock = mock(Libro.class);
        when(libroService.estado(anyInt())).thenReturn(libroMock);

        doNothing().when(prestamoService).registro(anyString(), eq(libroMock));

        Usuario usuarioMock = mock(Usuario.class);
        when(usuarioRepository.buscarConDocumento(anyString())).thenReturn(usuarioMock);

        // Ejecutar método a probar
        Usuario result = usuarioService.prestarLibro(1, "12345678");

        // Verificar resultados
        verify(libroService, times(1)).estado(anyInt());
        verify(prestamoService, times(1)).registro(anyString(), eq(libroMock));
        verify(usuarioRepository, times(1)).buscarConDocumento(anyString());

        assertEquals(usuarioMock, result);
    }
}