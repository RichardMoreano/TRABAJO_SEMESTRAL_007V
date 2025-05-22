package com.edutech.micros.edutech.repository;

import com.edutech.micros.edutech.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findAll();

    List<Usuario> findByNombreCompleto(String nombre, String apellido);

    Usuario findByCorreo(String correo);

    Usuario findByRut(int run);
}
