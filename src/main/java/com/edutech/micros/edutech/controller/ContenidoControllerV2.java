package com.edutech.micros.edutech.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import com.edutech.micros.edutech.assemblers.ContenidoModelAssembler;
import com.edutech.micros.edutech.model.Contenido;
import com.edutech.micros.edutech.service.ContenidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v2/contenido")
public class ContenidoControllerV2 {

    @Autowired
    private ContenidoService contenidoService;

    @Autowired
    private ContenidoModelAssembler assembler;

    // GET /api/v2/contenido — lista todos los contenidos
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Contenido>>> listarContenidos() {
        List<Contenido> contenidos = contenidoService.findAll();

        if (contenidos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<EntityModel<Contenido>> modelos = contenidos.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                CollectionModel.of(modelos,
                        linkTo(methodOn(ContenidoControllerV2.class).listarContenidos()).withSelfRel())
        );
    }

    // GET /api/v2/contenido/{id} — contenido con enlaces
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Contenido>> obtenerContenido(@PathVariable Long id) {
        Contenido contenido = contenidoService.findById(id);
        if (contenido == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(assembler.toModel(contenido));
    }

    // POST /api/v2/contenido — crea nuevo y retorna con Location + enlaces
    @PostMapping
    public ResponseEntity<EntityModel<Contenido>> crearContenido(@RequestBody Contenido contenido,
                                                                 UriComponentsBuilder uriBuilder) {
        Contenido contenidoNuevo = contenidoService.save(contenido);

        return ResponseEntity.created(
                        uriBuilder.path("/api/v2/contenido/{id}")
                                .buildAndExpand(contenidoNuevo.getId()).toUri())
                .body(assembler.toModel(contenidoNuevo));
    }

    // PUT /api/v2/contenido/{id} — actualiza si existe
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Contenido>> actualizarContenido(@PathVariable Long id,
                                                                      @RequestBody Contenido contenidoNuevo) {
        Contenido contenidoExistente = contenidoService.findById(id);
        if (contenidoExistente == null) {
            return ResponseEntity.notFound().build();
        }

        contenidoExistente.setTitulo(contenidoNuevo.getTitulo());
        contenidoExistente.setDescripcion(contenidoNuevo.getDescripcion());
        contenidoExistente.setTipo(contenidoNuevo.getTipo());
        contenidoExistente.setPrecio(contenidoNuevo.getPrecio());

        Contenido actualizado = contenidoService.save(contenidoExistente);
        return ResponseEntity.ok(assembler.toModel(actualizado));
    }

    // DELETE /api/v2/contenido/{id} — elimina si existe
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

