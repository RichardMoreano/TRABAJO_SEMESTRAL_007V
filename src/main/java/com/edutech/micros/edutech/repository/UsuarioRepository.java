package com.edutech.micros.edutech.repository;

import com.edutech.micros.edutech.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    List<Usuario> listaUsuario = new ArrayList<>();
    List<Usuario> findAll();
    Optional<Usuario> findTopByOrderByIdDesc();

    List<Usuario> findByCorreo(String correo);

    List<Usuario> findByNombreAndApellido(String nombre, String apellido);



    Usuario save(Usuario usuario);

    Usuario deleteById(long id);


    default Usuario updateUsuario(Usuario usuario) {
        for (int i = 0; i < listaUsuario.size(); i++) {
            if (listaUsuario.get(i).getId().equals(usuario.getId())) {
                listaUsuario.set(i, usuario);
                return usuario;
            }
        }
        throw new RuntimeException("Usuario no encontrado");
    }



}
