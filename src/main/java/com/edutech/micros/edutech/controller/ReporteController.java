package com.edutech.micros.edutech.controller;

import com.edutech.micros.edutech.service.ReporteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


/**

 @Autor Richard Moreano

 */
@RestController
@RequestMapping("/api/reporte")
public class ReporteController {

    // Inyección del servicio ReporteService
    @Autowired
    private ReporteService reporteService;

    // Retorna cantidad de usuarios


    @GetMapping("/total-usuarios")
    @Operation(summary = "Contar total de usuarios", description = "Cuenta el total de usuarios registrados en la base de datos",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Operación exitosa",
                            content = @Content(examples = @ExampleObject(value = "100"))),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            })
    public ResponseEntity<Map<String, Long>> getTotalUsuarios() {
        Long total = reporteService.contarUsuarios();
        return ResponseEntity.ok(Map.of("totalUsuarios", total));
    }

    // Retorna cantidad de cursos
    @GetMapping("/total-cursos")
    @Operation(summary = "Contar total de cursos", description = "Cuenta el total de cursos disponibles en la base de datos",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Operación exitosa",
                            content = @Content(examples = @ExampleObject(value = "50"))),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            })
    public ResponseEntity<Map<String, Long>> getTotalCursos() {
        Long total = reporteService.contarCursos();
        return ResponseEntity.ok(Map.of("totalCursos", total));
    }

    // Retorna el curso con precio más alto
    @GetMapping("/curso-mas-caro")
    @Operation(summary = "Obtener curso más caro", description = "Busca el curso con el precio más alto",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Operación exitosa",
                            content = @Content(examples = @ExampleObject(value = "Java Básico - Precio: 20000"))),
                    @ApiResponse(responseCode = "404", description = "No hay cursos disponibles")
            })
    public String getCursoMasCaro() {
        return reporteService.obtenerCursoMasCaro();
    }

    // Retorna el nombre del último usuario registrado
    @GetMapping("/ultimo-usuario")
    @Operation(summary = "Obtener último usuario registrado", description = "Obtiene el último usuario registrado en la base de datos",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Operación exitosa",
                            content = @Content(examples = @ExampleObject(value = "Benito Camelo"))),
                    @ApiResponse(responseCode = "404", description = "No hay usuarios registrados")
            })
    public String getUltimoUsuario() {
        return reporteService.obtenerUltimoUsuario();
    }

    // Retorna lista de cursos más vendidos
    @GetMapping("/cursos-mas-vendidos")
    @Operation(summary = "Obtener cursos más vendidos", description = "Devuelve una lista de los 5 cursos más vendidos",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Operación exitosa",
                            content = @Content(examples = @ExampleObject(value = "[\"Java Básico\", \"Spring Boot Pro\", \"Diseño UX/UI\"]"))),
                    @ApiResponse(responseCode = "404", description = "No hay pedidos registrados")
            })
    public List<String> getCursosMasVendidos() {
        return reporteService.obtenerCursosMasVendidos();
    }

    // Retorna cantidad de pagos por tipos
    @GetMapping("/pagos-por-tipo")
    @Operation(summary = "Obtener pagos por tipo", description = "Agrupa los pagos por tipo y cuenta la cantidad",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Operación exitosa",
                            content = @Content(examples = @ExampleObject(value = "{\"Efectivo\": 10, \"Tarjeta\": 5, \"Transferencia\": 11}"))),
                    @ApiResponse(responseCode = "404", description = "No hay pedidos registrados")
            })
    public Map<String, Long> getPagosPorTipo() {
        return reporteService.obtenerPagosPorTipo();
    }
}