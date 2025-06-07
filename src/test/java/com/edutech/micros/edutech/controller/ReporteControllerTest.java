package com.edutech.micros.edutech.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.edutech.micros.edutech.service.ReporteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebMvcTest(ReporteController.class)
public class ReporteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReporteService reporteService;

    @BeforeEach
    void setUp() {
    }

    @Test
    public void testGetTotalUsuarios() throws Exception {
        // 5 usuarios insertados
        when(reporteService.contarUsuarios()).thenReturn(5L);

        mockMvc.perform(get("/api/reporte/total-usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalUsuarios").value(5));
    }

    @Test
    public void testGetTotalCursos() throws Exception {
        // Segun los cursos insertados: 5 cursos
        when(reporteService.contarCursos()).thenReturn(5L);

        mockMvc.perform(get("/api/reporte/total-cursos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalCursos").value(5));
    }

    @Test
    public void testGetCursoMasCaro() throws Exception {
        // El curso con el precio mas alto es "Docker y Kubernetes"
        when(reporteService.obtenerCursoMasCaro()).thenReturn("Docker y Kubernetes");

        mockMvc.perform(get("/api/reporte/curso-mas-caro"))
                .andExpect(status().isOk())
                .andExpect(content().string("Docker y Kubernetes"));
    }

    @Test
    public void testGetUltimoUsuario() throws Exception {
        // El ultimo usuario registrado es María Rojas
        when(reporteService.obtenerUltimoUsuario()).thenReturn("María Rojas");

        mockMvc.perform(get("/api/reporte/ultimo-usuario"))
                .andExpect(status().isOk())
                .andExpect(content().string("María Rojas"));
    }

    @Test
    public void testGetCursosMasVendidos() throws Exception {
        // Mas vendidos: Spring Boot Pro (2 veces), Java Básico (1 vez) — para simplificar pongo dos cursos más vendidos
        when(reporteService.obtenerCursosMasVendidos()).thenReturn(List.of("Spring Boot Pro", "Java Básico"));

        mockMvc.perform(get("/api/reporte/cursos-mas-vendidos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").value("Spring Boot Pro"))
                .andExpect(jsonPath("$[1]").value("Java Básico"));
    }

    @Test
    public void testGetPagosPorTipo() throws Exception {
        // Pedidos tipos de pago: Tarjeta(2), Efectivo(2), Transferencia(1)
        Map<String, Long> pagosPorTipo = new HashMap<>();
        pagosPorTipo.put("Tarjeta", 2L);
        pagosPorTipo.put("Efectivo", 2L);
        pagosPorTipo.put("Transferencia", 1L);

        when(reporteService.obtenerPagosPorTipo()).thenReturn(pagosPorTipo);

        mockMvc.perform(get("/api/reporte/pagos-por-tipo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Tarjeta").value(2))
                .andExpect(jsonPath("$.Efectivo").value(2))
                .andExpect(jsonPath("$.Transferencia").value(1));
    }
}
