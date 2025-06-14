package com.edutech.micros.edutech.service;

import com.edutech.micros.edutech.model.Usuario;
import com.edutech.micros.edutech.repository.UsuarioRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**

 @Autor dominique Cofre

 */
@Service
@Transactional
public class UsuarioService {

    @Autowired

    private UsuarioRepository usuarioRepository;
    // buscar todos
    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }

    // buscar por id
    public Usuario findByIdusuario(long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    /*public Usuario findByIdusuario(long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }*/

    // buscar por correo
    public List<Usuario> findByCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }


    // buscar por nombre y apellido
    public List<Usuario> findByNombreCompleto(String nombre, String apellido) {
        return usuarioRepository.findByNombreAndApellido(nombre, apellido);
    }



    // crear usuario
    public Usuario save(Usuario usuario){
        return usuarioRepository.save(usuario);
    }


    // borrar por id
    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }



    public Usuario updateUsuario(Usuario usuario) {
        Usuario usuario1 = usuarioRepository.findById(usuario.getId())
                .orElseThrow(() -> new RuntimeException("Usuario con ID " + usuario.getId() + " no existe"));

        usuario1.setNombre(usuario.getNombre());
        usuario1.setApellido(usuario.getApellido());
        usuario1.setCorreo(usuario.getCorreo());
        usuario1.setRun(usuario.getRun());

        return usuarioRepository.save(usuario1);
    }

}

