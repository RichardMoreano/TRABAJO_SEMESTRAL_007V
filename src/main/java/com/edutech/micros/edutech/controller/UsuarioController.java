package com.edutech.micros.edutech.controller;

import com.edutech.micros.edutech.model.Usuario;
import com.edutech.micros.edutech.service.UsuarioService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Collections;
import java.util.List;


/**

 @Autor dominique Cofre

 */

@RestController
@RequestMapping("/api/usuarios")

@Tag(name = "Usuarios", description = "operaciones relacionadas con la gestion de usuarios")

public class UsuarioController {

//se utiliza para hacer inyección de dependencias
 @Autowired
    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Operation(summary = "Obtener todos los usuarios", description = "Obtiene una lista con los usuarios",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Operación exitosa: lista de usuarios obtenida",
                            content = @Content(examples = @ExampleObject(value = "[{\"id\": 1, \"nombre\": \"Benito\", \"apellido\": \"Camelo\"}]"))),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            })
    @GetMapping
    public List<Usuario> findAll() {
        return usuarioService.findAll();
    }





    @PostMapping
    @Operation(summary = "Guardar un usuario", description = "Guarda un usuario completamente nuevo",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente",
                            content = @Content(examples = @ExampleObject(value = "{\"id\": 1, \"nombre\": \"Elver\", \"apellido\": \"Pérez}"))),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos")
            })
    public Usuario postUsuario(@RequestBody Usuario usuario) {
        return usuarioService.save(usuario);
    }

    /*@GetMapping("/{id}")
    public Usuario findUsuario(@PathVariable Long id) {
        return usuarioService.findByIdusuario(id);
    }*/




    @GetMapping("/{id}")
    @Operation(summary = "buscar por id usuario", description = "busca por el id del usuario en la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @Parameter(description = "ID del usuario", required = true)

    public ResponseEntity<?> findUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioService.findByIdusuario(id);
        if (usuario == null) {
            return ResponseEntity
                    .status(404)
                    .body("Usuario con ID " + id + " no existe.");
        }
        return ResponseEntity.ok(usuario);
    }

    /*@GetMapping("/correo/{correo}")
    public List<Usuario> findCorreo(@PathVariable String correo) {
        return usuarioService.findByCorreo(correo);
    }*/




    @GetMapping("/correo/{correo}")
    @Operation(summary = "Buscar por correo", description = "Busca por el correo del usuario en la base de datos",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuarios encontrados",
                            content = @Content(examples = @ExampleObject(value = "[{\"id\": 1, \"nombre\": \"Stefye\", \"apellido\": \"Rudo\"}]"))),
                    @ApiResponse(responseCode = "404", description = "No se encontraron usuarios con el correo especificado")
            })
    @Parameter(description = "Correo electrónico del usuario", required = true)

    public ResponseEntity<?> findCorreo(@PathVariable String correo) {
        List<Usuario> usuario = usuarioService.findByCorreo(correo);
        if (usuario.isEmpty()) {
            return ResponseEntity
                    .status(404)
                    .body("No se encontraron usuarios con el correo: " + correo);
        }

        return ResponseEntity.ok(usuario);
    }




    @GetMapping("/nombre/{nombre}/{apellido}")
    @Operation(summary = "Buscar por el nombre completo", description = "Busca por el nombre completo del usuario en la base de datos",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuarios encontrados por nombre y apellido",
                            content = @Content(examples = @ExampleObject(value = "[{\"id\": 1, \"nombre\": \"Joelca\", \"apellido\": \"Galindo\"}]"))),
                    @ApiResponse(responseCode = "404", description = "No se encontraron usuarios con ese nombre completo")
            })
    @Parameter(description = "Nombre del usuario", required = true)
    @Parameter(description = "Apellido del usuario", required = true)

    public List<Usuario> findNombreCompleto(@PathVariable String nombre, @PathVariable String apellido) {
        return usuarioService.findByNombreCompleto(nombre, apellido);
    }



    @DeleteMapping("{id}")
    @Operation(summary = "Buscar por ID", description = "Busca por el ID del usuario en la base de datos",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuario eliminado con exito",
                            content = @Content(examples = @ExampleObject(value = "{\"id\": 1, \"nombre\": \"Soyla\", \"apellido\": \"Cerda\"}"))),
                    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
            })

    public void deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
    }




    @PutMapping("/actua")
    @Operation(summary = "Actualizar los datos", description = "Actualiza los datos del usuario menos el ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuario actualizado correctamente",
                            content = @Content(examples = @ExampleObject(value = "{\"id\": 1, \"nombre\": \"Domique\", \"apellido\": \"Cofre\"}"))),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos"),
                    @ApiResponse(responseCode = "404", description = "Usuario no encontrado para actualizar")
            })
    public Usuario updateUsuario(@RequestBody Usuario usuario) {
        return usuarioService.updateUsuario(usuario); // Usa save() para crear o actualizar
    }

}