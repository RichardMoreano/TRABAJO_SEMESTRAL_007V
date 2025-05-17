package com.edutech.micros.edutech.controller;

import com.edutech.micros.edutech.model.Contenido;
import com.edutech.micros.edutech.repository.ContenidoRepository;
import com.edutech.micros.edutech.service.ContenidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contenido")
public class ContenidoController {

    @Autowired
    private ContenidoRepository contenidoRepository;

    @Autowired
    private ContenidoService contenidoService;

    @GetMapping
    public ResponseEntity<List<Contenido>> listarContenidos() {
        List<Contenido> contenidos = contenidoRepository.findAll();
        if (contenidos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(contenidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contenido> obtenerContenido(@PathVariable Long id) {
        Contenido contenido = contenidoRepository.findById(id).orElse(null);
        if (contenido == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(contenido);
    }

    @PostMapping
    public ResponseEntity<Contenido> crearContenido(@RequestBody Contenido contenido) {
        Contenido contenidoNuevo = contenidoRepository.save(contenido);
        return ResponseEntity.ok(contenidoNuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contenido> actualizarContenido(@PathVariable Long id, @RequestBody Contenido contenidoNuevo) {
        try {
            Contenido contenidoExistente = contenidoService.findByd(id);
            contenidoExistente.setTitulo(contenidoNuevo.getTitulo());
            contenidoExistente.setDescripcion(contenidoNuevo.getDescripcion());
            contenidoExistente.setTipo(contenidoNuevo.getTipo());

           contenidoService.save( contenidoExistente);
           return ResponseEntity.ok(contenidoExistente);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarContenido(@PathVariable Long id) {
        contenidoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
