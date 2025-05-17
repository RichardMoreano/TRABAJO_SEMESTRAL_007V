package com.edutech.micros.edutech.repository;

import com.edutech.micros.edutech.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findAll();
}
