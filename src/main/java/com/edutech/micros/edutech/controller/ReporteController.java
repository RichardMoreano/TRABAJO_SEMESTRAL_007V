package com.edutech.micros.edutech.controller;

import com.edutech.micros.edutech.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reporte")
public class ReporteController {

    // Inyección del servicio ReporteService
    @Autowired
    private ReporteService reporteService;

    // Retorna cantidad de usuarios


    @GetMapping("/total-usuarios")
    public ResponseEntity<Map<String, Long>> getTotalUsuarios() {
        Long total = reporteService.contarUsuarios();
        return ResponseEntity.ok(Map.of("totalUsuarios", total));
    }

    // Retorna cantidad de cursos
    @GetMapping("/total-cursos")
    public ResponseEntity<Map<String, Long>> getTotalCursos() {
        Long total = reporteService.contarCursos();
        return ResponseEntity.ok(Map.of("totalCursos", total));
    }

    // Retorna el curso con precio más alto
    @GetMapping("/curso-mas-caro")
    public String getCursoMasCaro() {
        return reporteService.obtenerCursoMasCaro();
    }

    // Retorna el nombre del último usuario registrado
    @GetMapping("/ultimo-usuario")
    public String getUltimoUsuario() {
        return reporteService.obtenerUltimoUsuario();
    }

    // Retorna lista de cursos más vendidos
    @GetMapping("/cursos-mas-vendidos")
    public List<String> getCursosMasVendidos() {
        return reporteService.obtenerCursosMasVendidos();
    }

    // Retorna cantidad de pagos por tipos
    @GetMapping("/pagos-por-tipo")
    public Map<String, Long> getPagosPorTipo() {
        return reporteService.obtenerPagosPorTipo();
    }
}