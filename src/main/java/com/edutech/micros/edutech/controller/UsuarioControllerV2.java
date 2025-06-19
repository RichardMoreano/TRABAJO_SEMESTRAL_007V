package com.edutech.micros.edutech.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import com.edutech.micros.edutech.assemblers.UsuarioModelAssembler;
import com.edutech.micros.edutech.model.Usuario;
import com.edutech.micros.edutech.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v2/Usuario")
public class UsuarioControllerV2 {
}
