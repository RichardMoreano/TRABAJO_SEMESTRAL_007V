package com.edutech.micros.edutech.service;

import com.edutech.micros.edutech.model.Usuario;
import com.edutech.micros.edutech.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }

    public Usuario findById(long id){
        return usuarioRepository.findById(id).get();
    }

    public Usuario save(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public void delete(long id){
        usuarioRepository.deleteById(id);
    }
}

