package com.edutech.micros.edutech.controller;

import com.edutech.micros.edutech.model.Usuario;
import com.edutech.micros.edutech.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        // No es necesario configurar nada adicional
    }

    @Test
    public void testFindAll() throws Exception {
        Usuario usuario1 = new Usuario();
        usuario1.setId(1L);
        usuario1.setNombre("Ana");
        usuario1.setApellido("González");

        Usuario usuario2 = new Usuario();
        usuario2.setId(2L);
        usuario2.setNombre("Luis");
        usuario2.setApellido("Martínez");

        List<Usuario> usuarios = Arrays.asList(usuario1, usuario2);

        when(usuarioService.findAll()).thenReturn(usuarios);

        mockMvc.perform(get("/api/usuarios"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].nombre").value("Ana"))
                .andExpect(jsonPath("$[1].nombre").value("Luis"));

        verify(usuarioService, times(1)).findAll();
    }

    @Test
    public void testPostUsuario() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNombre("María");
        usuario.setApellido("Rojas");

        when(usuarioService.save(any(Usuario.class))).thenReturn(usuario);

        mockMvc.perform(post("/api/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"María\", \"apellido\":\"Rojas\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("María"));

        verify(usuarioService, times(1)).save(any(Usuario.class));
    }

    @Test
    public void testFindUsuario() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Ana");
        usuario.setApellido("González");

        when(usuarioService.findByIdusuario(1L)).thenReturn(usuario);

        mockMvc.perform(get("/api/usuarios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Ana"));

        verify(usuarioService, times(1)).findByIdusuario(1L);
    }

    @Test
    public void testFindUsuarioNotFound() throws Exception {
        when(usuarioService.findByIdusuario(1L)).thenReturn(null);

        mockMvc.perform(get("/api/usuarios/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Usuario con ID 1 no existe."));

        verify(usuarioService, times(1)).findByIdusuario(1L);
    }

    @Test
    public void testFindCorreo() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Luis");
        usuario.setApellido("Martínez");

        when(usuarioService.findByCorreo("luis@example.com")).thenReturn(Arrays.asList(usuario));

        mockMvc.perform(get("/api/usuarios/correo/luis@example.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Luis"));

        verify(usuarioService, times(1)).findByCorreo("luis@example.com");
    }

    @Test
    public void testFindCorreoNotFound() throws Exception {
        when(usuarioService.findByCorreo("noexiste@example.com")).thenReturn(Arrays.asList());

        mockMvc.perform(get("/api/usuarios/correo/noexiste@example.com"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("No se encontraron usuarios con el correo: noexiste@example.com"));

        verify(usuarioService, times(1)).findByCorreo("noexiste@example.com");
    }

    @Test
    public void testDeleteUsuario() throws Exception {
        doNothing().when(usuarioService).deleteUsuario(1L);

        mockMvc.perform(delete("/api/usuarios/1"))
                .andExpect(status().isOk());

        verify(usuarioService, times(1)).deleteUsuario(1L);
    }

    @Test
    public void testUpdateUsuario() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Ana");
        usuario.setApellido("González");

        when(usuarioService.updateUsuario(any(Usuario.class))).thenReturn(usuario);

        mockMvc.perform(put("/api/usuarios/actua")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1, \"nombre\":\"Ana\", \"apellido\":\"González\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Ana"));

        verify(usuarioService, times(1)).updateUsuario(any(Usuario.class));
    }
}
