package com.edutech.micros.edutech.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.edutech.micros.edutech.controller.UsuarioController;
import com.edutech.micros.edutech.model.Contenido;
import com.edutech.micros.edutech.model.Usuario;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class UsuarioModelAssembler implements RepresentationModelAssembler<Usuario, EntityModel<Usuario>> {
    @Override
        public EntityModel<Usuario> toModel(Usuario usuario) {
            return EntityModel.of(
                    usuario,
                    linkTo(methodOn(UsuarioController.class).findUsuario(usuario.getId())).withSelfRel(),
                    linkTo(methodOn(UsuarioController.class).findAll()).withRel("usuarios")
            );
        }
    }
