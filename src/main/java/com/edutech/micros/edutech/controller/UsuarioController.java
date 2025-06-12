package com.edutech.micros.edutech.controller;

import com.edutech.micros.edutech.model.Usuario;
import com.edutech.micros.edutech.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Collections;
import java.util.List;


/**

 @Autor dominique Cofre

 */

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

//se utiliza para hacer inyección de dependencias
 @Autowired
    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> findAll() {
        return usuarioService.findAll();
    }

    @PostMapping
    public Usuario postUsuario(@RequestBody Usuario usuario) {
        return usuarioService.save(usuario);
    }

    /*@GetMapping("/{id}")
    public Usuario findUsuario(@PathVariable Long id) {
        return usuarioService.findByIdusuario(id);
    }*/

    @GetMapping("/{id}")
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
    public List<Usuario> findNombreCompleto(@PathVariable String nombre, @PathVariable String apellido) {
        return usuarioService.findByNombreCompleto(nombre, apellido);
    }

    @DeleteMapping("{id}")
    public void deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
    }

    @PutMapping("/actua")
    public Usuario updateUsuario(@RequestBody Usuario usuario) {
        return usuarioService.updateUsuario(usuario); // Usa save() para crear o actualizar
    }

}