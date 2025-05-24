package com.edutech.micros.edutech.repository;

import com.edutech.micros.edutech.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findAll();
    Optional<Usuario> findTopByOrderByIdDesc();


    List<Usuario> findByCorreo(String correo);


    List<Usuario> findByNombreAndApellido(String nombre, String apellido);

    Usuario findById(long id);

    Usuario save(Usuario usuario);

    Usuario deleteById(long id);

    Usuario updateByUsuario(Usuario usuario);

}
