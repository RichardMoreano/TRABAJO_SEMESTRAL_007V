package com.edutech.micros.edutech.controller;

import com.edutech.micros.edutech.model.Usuario;
import com.edutech.micros.edutech.service.UsuarioService;
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

    @GetMapping
   @Operation(summary ="obtener todos los usuarios",description = "obtiene una lista con los usuarios")
    public List<Usuario> findAll() {
        return usuarioService.findAll();
    }

    @PostMapping
    @Operation(summary = "guarda una usuario ", description = "guarda una usuario completamente nuevo")
    public Usuario postUsuario(@RequestBody Usuario usuario) {
        return usuarioService.save(usuario);
    }

    /*@GetMapping("/{id}")
    public Usuario findUsuario(@PathVariable Long id) {
        return usuarioService.findByIdusuario(id);
    }*/

    @GetMapping("/{id}")
    @Operation(summary = "buscar por id", description = "busca por el id del usuario en la base de datos")
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
    @Operation(summary = "busca por correo", description = "busca por el correo del usuario en la base de datos")
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
    @Operation(summary = "buscar por el nombre completo", description = "busca por el nombre completo del usuario en la base de datos")
    public List<Usuario> findNombreCompleto(@PathVariable String nombre, @PathVariable String apellido) {
        return usuarioService.findByNombreCompleto(nombre, apellido);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "eliminar por id", description = "elimina toda la informacion por el id del usuario")
    public void deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
    }

    @PutMapping("/actua")
    @Operation(summary = "actualizar los datos", description = "actualiza los datos del usuario menos el id ")
    public Usuario updateUsuario(@RequestBody Usuario usuario) {
        return usuarioService.updateUsuario(usuario); // Usa save() para crear o actualizar
    }

}