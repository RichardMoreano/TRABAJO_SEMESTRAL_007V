package com.edutech.micros.edutech.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.edutech.micros.edutech.controller.ContenidoController;
import com.edutech.micros.edutech.model.Contenido;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class ContenidoModelAssembler implements RepresentationModelAssembler<Contenido, EntityModel<Contenido>> {

    @Override
    public EntityModel<Contenido> toModel(Contenido contenido) {
        return EntityModel.of(
                contenido,
                linkTo(methodOn(ContenidoController.class).obtenerContenido(contenido.getId())).withSelfRel(),
                linkTo(methodOn(ContenidoController.class).listarContenidos()).withRel("contenidos")
        );
    }
}
