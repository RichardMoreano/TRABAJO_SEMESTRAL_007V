package com.edutech.micros.edutech.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.edutech.micros.edutech.model.Contenido;
import com.edutech.micros.edutech.service.ContenidoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

@WebMvcTest(ContenidoController.class) // Indica que se está probando el controlador de Contenido
public class ContenidoControllerTest {

    @Autowired
    private MockMvc mockMvc; // Simula peticiones HTTP para pruebas

    @MockBean
    private ContenidoService contenidoService; // Mock del servicio de Contenido

    @Autowired
    private ObjectMapper objectMapper; // Convierte objetos Java a JSON y viceversa

    private Contenido contenido;

    @BeforeEach
    void setUp() {
        // Configura un objeto Contenido de ejemplo antes de cada prueba
        contenido = new Contenido();
        contenido.setId(1L);
        contenido.setTitulo("Curso Java Básico");
        contenido.setDescripcion("Introducción a Java desde cero");
        contenido.setTipo("Video");
        contenido.setPrecio(5000);
    }

    @Test
    public void testGetAllContenidos() throws Exception {
        // Mock: cuando se llama a findAll(), retorna una lista con un Contenido
        when(contenidoService.findAll()).thenReturn(List.of(contenido));

        // Realiza petición GET y verifica respuesta
        mockMvc.perform(get("/api/contenido"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].titulo").value("Curso Java Básico"));
    }

    @Test
    public void testGetContenidoById() throws Exception {
        when(contenidoService.findById(1L)).thenReturn(contenido);

        mockMvc.perform(get("/api/contenido/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.titulo").value("Curso Java Básico"));
    }

    @Test
    public void testCreateContenido() throws Exception {
        when(contenidoService.save(any(Contenido.class))).thenReturn(contenido);

        mockMvc.perform(post("/api/contenido")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contenido)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.titulo").value("Curso Java Básico"));
    }

    @Test
    public void testUpdateContenido() throws Exception {
        when(contenidoService.save(any(Contenido.class))).thenReturn(contenido);
        when(contenidoService.findById(1L)).thenReturn(contenido);

        mockMvc.perform(put("/api/contenido/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contenido)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.titulo").value("Curso Java Básico"));
    }

    @Test
    public void testDeleteContenido() throws Exception {
        doNothing().when(contenidoService).deleteById(1L);
        when(contenidoService.findById(1L)).thenReturn(contenido);

        mockMvc.perform(delete("/api/contenido/1"))
                .andExpect(status().isNoContent());

        verify(contenidoService, times(1)).deleteById(1L);
    }
}

