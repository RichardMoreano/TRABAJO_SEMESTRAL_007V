package com.edutech.micros.edutech.controller;

import com.edutech.micros.edutech.model.Usuario;
import com.edutech.micros.edutech.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.Collections;
import java.util.List;
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

//se utiliza para hacer inyección de dependencias
 @Autowired
    private final UsuarioService usuarioService;

    @GetMapping("/")
    public List<Usuario> findAll() {
        return usuarioService.findAll();
    }
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    /*POST: Se utiliza para enviar datos al servidor y crear un nuevo recurso*/
    @PostMapping
    public Usuario postUsuario(@RequestBody Usuario usuario) {
        return usuarioService.save(usuario);
    }
    /*GET: Se utiliza para recuperar un recurso del servidor. Es un métod0 de solo lectura*/
    @GetMapping("/{id}")
    public Usuario findUsuario(@PathVariable Long id) {
        return usuarioService.findByIdusuario(id);
    }

    @GetMapping("/correo/{correo}")
    public List<Usuario> findCorreo(@PathVariable String correo) {
        return usuarioService.findByCorreo(correo);
    }

    @GetMapping("/nombre")
    public List<Usuario> findNombreCompleto(@PathVariable String nombre, @PathVariable String apellido) {
        return usuarioService.findByNombreCompleto(nombre, apellido);
    }

    /*DELETE: Se utiliza para eliminar un recurso específico en el servidor.*/
    @DeleteMapping("{id}")
    public void deleteUsuario(@PathVariable Long id) {
        usuarioService.delete(id);
    }

    @PutMapping("/actua")
    public Usuario updateUsuario(@RequestBody Usuario usuario) {
        return usuarioService.updateUsuario(usuario);
    }
}
