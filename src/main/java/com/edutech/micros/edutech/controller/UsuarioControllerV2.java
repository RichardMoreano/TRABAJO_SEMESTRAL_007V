package com.edutech.micros.edutech.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import com.edutech.micros.edutech.assemblers.UsuarioModelAssembler;
import com.edutech.micros.edutech.model.Usuario;
import com.edutech.micros.edutech.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v2/usuarios")
@Tag(name = "UsuariosV2", description = "operaciones relacionadas con la gestion de usuarios HATEOS")
public class UsuarioControllerV2 {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioModelAssembler assembler;

    @GetMapping
    @Operation(summary = "Obtener todos los usuarios", description = "Obtiene una lista con los usuarios")
    public ResponseEntity<CollectionModel<EntityModel<Usuario>>> findAll() {
        List<Usuario> usuarios = usuarioService.findAll();

        List<EntityModel<Usuario>> usuariosModel = usuarios.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        CollectionModel<EntityModel<Usuario>> collectionModel =
                CollectionModel.of(usuariosModel,
                        linkTo(methodOn(UsuarioController.class).findAll()).withSelfRel());

        return ResponseEntity.ok(collectionModel);
    }

    @PostMapping
    @Operation(summary = "Guardar un usuario", description = "Guarda un usuario completamente nuevo")
    public ResponseEntity<EntityModel<Usuario>> postUsuario(@RequestBody Usuario usuario, UriComponentsBuilder uriBuilder) {
        Usuario creado = usuarioService.save(usuario);
        EntityModel<Usuario> usuarioModel = assembler.toModel(creado);

        return ResponseEntity
                .created(linkTo(methodOn(UsuarioController.class).findUsuario(creado.getId())).toUri())
                .body(usuarioModel);
    }
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un usuario existente")
    public ResponseEntity<?> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuarioActualizado) {
        Usuario existente = usuarioService.findByIdusuario(id);

        if (existente == null) {
            return ResponseEntity.status(404).body("Usuario con ID " + id + " no encontrado.");
        }

        // Actualiza campos necesarios
        existente.setNombre(usuarioActualizado.getNombre());
        existente.setApellido(usuarioActualizado.getApellido());
        existente.setCorreo(usuarioActualizado.getCorreo());

        Usuario actualizado = usuarioService.updateUsuario(existente);
        EntityModel<Usuario> model = assembler.toModel(actualizado);

        return ResponseEntity.ok(model);
    }

    @GetMapping("/nombre/{nombre}/{apellido}")
    @Operation(summary = "Buscar por el nombre completo")
    public ResponseEntity<?> findNombreCompleto(@PathVariable String nombre, @PathVariable String apellido) {
        List<Usuario> usuarios = usuarioService.findByNombreCompleto(nombre, apellido);

        if (usuarios.isEmpty()) {
            return ResponseEntity.status(404).body("No se encontraron usuarios con ese nombre completo.");
        }

        List<EntityModel<Usuario>> usuariosModel = usuarios.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(usuariosModel));
    }

    @GetMapping("/correo/{correo}")
    @Operation(summary = "Buscar usuarios por correo")
    public ResponseEntity<?> findCorreo(@PathVariable String correo) {
        List<Usuario> usuarios = usuarioService.findByCorreo(correo);

        if (usuarios.isEmpty()) {
            return ResponseEntity.status(404).body("No se encontraron usuarios con el correo: " + correo);
        }

        List<EntityModel<Usuario>> usuariosModel = usuarios.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(usuariosModel));
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un usuario por ID", description = "Busca por el ID del usuario en la base de datos",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuario encontrado",
                            content = @Content(examples = @ExampleObject(value = "{\"id\": 1, \"nombre\": \"Soyla\", \"apellido\": \"Cerda\"}"))),
                    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
            })
    public ResponseEntity<?> deleteUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioService.findByIdusuario(id);

        if (usuario == null) {
            return ResponseEntity.status(404).body("Usuario con ID " + id + " no encontrado.");
        }

        usuarioService.deleteUsuario(id);

        return ResponseEntity.status(500).body("Usuario eliminado exitosamente");
    }


}