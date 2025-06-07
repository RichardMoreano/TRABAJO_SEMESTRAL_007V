package com.edutech.micros.edutech.service;

import com.edutech.micros.edutech.model.Usuario;
import com.edutech.micros.edutech.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        Usuario usuario1 = new Usuario();
        usuario1.setId(1L);
        usuario1.setNombre("Ana");
        usuario1.setApellido("González");

        Usuario usuario2 = new Usuario();
        usuario2.setId(2L);
        usuario2.setNombre("Luis");
        usuario2.setApellido("Martínez");

        List<Usuario> usuarios = Arrays.asList(usuario1, usuario2);

        when(usuarioRepository.findAll()).thenReturn(usuarios);

        List<Usuario> result = usuarioService.findAll();

        assertEquals(2, result.size());
        assertEquals("Ana", result.get(0).getNombre());
        assertEquals("Luis", result.get(1).getNombre());
        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    public void testFindByIdusuario() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Ana");
        usuario.setApellido("González");

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        Usuario result = usuarioService.findByIdusuario(1L);

        assertEquals("Ana", result.getNombre());
        verify(usuarioRepository, times(1)).findById(1L);
    }

    @Test
    public void testFindByIdusuarioNotFound() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());

        Usuario result = usuarioService.findByIdusuario(1L);

        assertEquals(null, result);
        verify(usuarioRepository, times(1)).findById(1L);
    }

    @Test
    public void testFindByCorreo() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Luis");
        usuario.setApellido("Martínez");

        when(usuarioRepository.findByCorreo("luis@example.com")).thenReturn(Arrays.asList(usuario));

        List<Usuario> result = usuarioService.findByCorreo("luis@example.com");

        assertEquals(1, result.size());
        assertEquals("Luis", result.get(0).getNombre());
        verify(usuarioRepository, times(1)).findByCorreo("luis@example.com");
    }

    @Test
    public void testFindByNombreCompleto() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Ana");
        usuario.setApellido("González");

        when(usuarioRepository.findByNombreAndApellido("Ana", "González")).thenReturn(Arrays.asList(usuario));

        List<Usuario> result = usuarioService.findByNombreCompleto("Ana", "González");

        assertEquals(1, result.size());
        assertEquals("Ana", result.get(0).getNombre());
        verify(usuarioRepository, times(1)).findByNombreAndApellido("Ana", "González");
    }

    @Test
    public void testSave() {
        Usuario usuario = new Usuario();
        usuario.setNombre("María");
        usuario.setApellido("Rojas");

        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario result = usuarioService.save(usuario);

        assertEquals("María", result.getNombre());
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    public void testDelete() {
        doNothing().when(usuarioRepository).deleteById(1L);

        usuarioService.delete(1L);

        verify(usuarioRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testUpdateUsuario() {
        Usuario existingUsuario = new Usuario();
        existingUsuario.setId(1L);
        existingUsuario.setNombre("Ana");
        existingUsuario.setApellido("González");

        Usuario updatedUsuario = new Usuario();
        updatedUsuario.setId(1L);
        updatedUsuario.setNombre("Ana");
        updatedUsuario.setApellido("Pérez");

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(existingUsuario));
        when(usuarioRepository.save(existingUsuario)).thenReturn(existingUsuario);

        Usuario result = usuarioService.updateUsuario(updatedUsuario);

        assertEquals("Ana", result.getNombre());
        assertEquals("Pérez", result.getApellido());
        verify(usuarioRepository, times(1)).findById(1L);
        verify(usuarioRepository, times(1)).save(existingUsuario);
    }

    @Test
    public void testUpdateUsuarioNotFound() {
        Usuario updatedUsuario = new Usuario();
        updatedUsuario.setId(1L);
        updatedUsuario.setNombre("Ana");
        updatedUsuario.setApellido("Pérez");

        when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            usuarioService.updateUsuario(updatedUsuario);
        });

        assertEquals("Usuario con ID 1 no existe", exception.getMessage());
        verify(usuarioRepository, times(1)).findById(1L);
    }
}
