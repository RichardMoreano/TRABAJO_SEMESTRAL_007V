package com.edutech.micros.edutech.controller;

import com.edutech.micros.edutech.model.Contenido;
import com.edutech.micros.edutech.service.ContenidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/contenido")
public class ContenidoController {

    @Autowired
    private ContenidoService contenidoService;

    @GetMapping
    public ResponseEntity<List<Contenido>> listarContenidos() {
        List<Contenido> contenidos = contenidoService.findAll();
        if (contenidos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(contenidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contenido> obtenerContenido(@PathVariable Long id) {
        Contenido contenido = contenidoService.findById(id);
        if (contenido == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(contenido);
    }

    @PostMapping
    public ResponseEntity<Contenido> crearContenido(@RequestBody Contenido contenido, UriComponentsBuilder uriBuilder) {
        Contenido contenidoNuevo = contenidoService.save(contenido);
        return ResponseEntity
                .created(uriBuilder.path("/api/contenido/{id}")
                        .buildAndExpand(contenidoNuevo.getId()).toUri())
                .body(contenidoNuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contenido> actualizarContenido(@PathVariable Long id, @RequestBody Contenido contenidoNuevo) {
        Contenido contenidoExistente = contenidoService.findById(id);
        if (contenidoExistente == null) {
            return ResponseEntity.notFound().build();
        }

        contenidoExistente.setTitulo(contenidoNuevo.getTitulo());
        contenidoExistente.setDescripcion(contenidoNuevo.getDescripcion());
        contenidoExistente.setTipo(contenidoNuevo.getTipo());

        contenidoService.save(contenidoExistente);
        return ResponseEntity.ok(contenidoExistente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarContenido(@PathVariable Long id) {
        Contenido contenidoExistente = contenidoService.findById(id);
        if (contenidoExistente == null) {
            return ResponseEntity.notFound().build();
        }

        contenidoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
