package com.edutech.micros.edutech.service;

import com.edutech.micros.edutech.model.Usuario;
import com.edutech.micros.edutech.repository.UsuarioRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UsuarioService {

    @Autowired

    private UsuarioRepository usuarioRepository;
    // buscar todos
    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }

    // buscar por correo
    public List<Usuario> findByCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }


    // buscar por nombre y apellido
    public List<Usuario> findByNombreCompleto(String nombre, String apellido) {
        return usuarioRepository.findByNombreAndApellido(nombre, apellido);
    }

    // buscar por id
    public Usuario findByIdusuario(long id){
        return usuarioRepository.findById(id);
    }

    // crear usuario
    public Usuario save(Usuario usuario){
        return usuarioRepository.save(usuario);
    }


    // borrar por id
    public void delete(long id){
        usuarioRepository.deleteById(id);
    }

    public Usuario updateUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

}

