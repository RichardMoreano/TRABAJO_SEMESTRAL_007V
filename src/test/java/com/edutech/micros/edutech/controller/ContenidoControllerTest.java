package com.edutech.micros.edutech.controller;

import com.edutech.micros.edutech.model.Contenido;
import com.edutech.micros.edutech.service.ContenidoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ContenidoController.class)
public class ContenidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContenidoService contenidoService;

    private Contenido contenido;

    @BeforeEach
    void setUp() {
        contenido = new Contenido();
        contenido.setId(1L);
        contenido.setTitulo("Curso Java Básico");
        contenido.setDescripcion("Introducción a Java desde cero");
        contenido.setTipo("Video");
        contenido.setPrecio(5000);
    }

    @Test
    public void testGetAllContenidos() throws Exception {
        when(contenidoService.findAll()).thenReturn(List.of(contenido));

        mockMvc.perform(get("/api/contenido"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].titulo").value("Curso Java Básico"));

        verify(contenidoService, times(1)).findAll();
    }

    @Test
    public void testGetContenidoById() throws Exception {
        when(contenidoService.findById(1L)).thenReturn(contenido);

        mockMvc.perform(get("/api/contenido/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.titulo").value("Curso Java Básico"));

        verify(contenidoService, times(1)).findById(1L);
    }

    @Test
    public void testGetContenidoByIdNotFound() throws Exception {
        when(contenidoService.findById(1L)).thenReturn(null);

        mockMvc.perform(get("/api/contenido/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string(""));  // Aquí esperamos cuerpo vacío

        verify(contenidoService, times(1)).findById(1L);
    }

    @Test
    public void testCreateContenido() throws Exception {
        when(contenidoService.save(any(Contenido.class))).thenReturn(contenido);

        mockMvc.perform(post("/api/contenido")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"titulo\":\"Curso Java Básico\", \"descripcion\":\"Introducción a Java desde cero\", \"tipo\":\"Video\", \"precio\":5000}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.titulo").value("Curso Java Básico"));

        verify(contenidoService, times(1)).save(any(Contenido.class));
    }

    @Test
    public void testUpdateContenido() throws Exception {
        when(contenidoService.findById(1L)).thenReturn(contenido);
        when(contenidoService.save(any(Contenido.class))).thenReturn(contenido);

        mockMvc.perform(put("/api/contenido/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1, \"titulo\":\"Curso Java Básico\", \"descripcion\":\"Introducción a Java desde cero\", \"tipo\":\"Video\", \"precio\":5000}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Curso Java Básico"));

        verify(contenidoService, times(1)).findById(1L);
        verify(contenidoService, times(1)).save(any(Contenido.class));
    }

    @Test
    public void testDeleteContenido() throws Exception {
        when(contenidoService.findById(1L)).thenReturn(contenido);
        doNothing().when(contenidoService).deleteById(1L);

        mockMvc.perform(delete("/api/contenido/1"))
                .andExpect(status().isNoContent());  // 204 No Content

        verify(contenidoService, times(1)).findById(1L);
        verify(contenidoService, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteContenidoNotFound() throws Exception {
        when(contenidoService.findById(1L)).thenReturn(null);

        mockMvc.perform(delete("/api/contenido/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string(""));  // cuerpo vacío esperado

        verify(contenidoService, times(1)).findById(1L);
        verify(contenidoService, times(0)).deleteById(anyLong());
    }
}

