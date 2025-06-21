package com.edutech.micros.edutech.controller;

import com.edutech.micros.edutech.assemblers.UsuarioModelAssembler;
import com.edutech.micros.edutech.assemblers.ContenidoModelAssembler;
import com.edutech.micros.edutech.model.Usuario;
import com.edutech.micros.edutech.model.Contenido;
import com.edutech.micros.edutech.service.ReporteService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/reporte")
public class ReporteControllerV2 {

    @Autowired
    private ReporteService reporteService;

    @Autowired
    private UsuarioModelAssembler usuarioAssembler;

    @Autowired
    private ContenidoModelAssembler contenidoAssembler;

    @GetMapping("/total-usuarios")
    @Operation(summary = "Contar total de usuarios")
    public ResponseEntity<EntityModel<Map<String, Long>>> getTotalUsuarios() {
        Long total = reporteService.contarUsuarios();
        Map<String, Long> result = Map.of("totalUsuarios", total);

        EntityModel<Map<String, Long>> model = EntityModel.of(result,
                linkTo(methodOn(ReporteControllerV2.class).getTotalUsuarios()).withSelfRel());

        return ResponseEntity.ok(model);
    }

    @GetMapping("/total-cursos")
    @Operation(summary = "Contar total de cursos")
    public ResponseEntity<EntityModel<Map<String, Long>>> getTotalCursos() {
        Long total = reporteService.contarCursos();
        Map<String, Long> result = Map.of("totalCursos", total);

        EntityModel<Map<String, Long>> model = EntityModel.of(result,
                linkTo(methodOn(ReporteControllerV2.class).getTotalCursos()).withSelfRel());

        return ResponseEntity.ok(model);
    }

    @GetMapping("/curso-mas-caro")
    @Operation(summary = "Obtener curso más caro")
    public ResponseEntity<EntityModel<String>> getCursoMasCaro() {
        String curso = reporteService.obtenerCursoMasCaro();

        EntityModel<String> model = EntityModel.of(curso,
                linkTo(methodOn(ReporteControllerV2.class).getCursoMasCaro()).withSelfRel());

        return ResponseEntity.ok(model);
    }

    @GetMapping("/ultimo-usuario")
    @Operation(summary = "Obtener último usuario registrado")
    public ResponseEntity<EntityModel<Usuario>> getUltimoUsuario() {
        Usuario usuario = reporteService.obtenerUltimoUsuario();

        EntityModel<Usuario> model = usuarioAssembler.toModel(usuario);
        model.add(linkTo(methodOn(ReporteControllerV2.class).getUltimoUsuario()).withSelfRel());

        return ResponseEntity.ok(model);
    }

    @GetMapping("/cursos-mas-vendidos")
    @Operation(summary = "Obtener cursos más vendidos")
    public ResponseEntity<CollectionModel<EntityModel<Contenido>>> getCursosMasVendidos() {
        List<Contenido> cursos = reporteService.obtenerCursosMasVendidos();

        List<EntityModel<Contenido>> cursosModel = cursos.stream()
                .map(contenidoAssembler::toModel)
                .collect(Collectors.toList());

        CollectionModel<EntityModel<Contenido>> collectionModel = CollectionModel.of(cursosModel,
                linkTo(methodOn(ReporteControllerV2.class).getCursosMasVendidos()).withSelfRel());

        return ResponseEntity.ok(collectionModel);
    }

    @GetMapping("/pagos-por-tipo")
    @Operation(summary = "Obtener pagos por tipo")
    public ResponseEntity<EntityModel<Map<String, Long>>> getPagosPorTipo() {
        Map<String, Long> pagos = reporteService.obtenerPagosPorTipo();

        EntityModel<Map<String, Long>> model = EntityModel.of(pagos,
                linkTo(methodOn(ReporteControllerV2.class).getPagosPorTipo()).withSelfRel());

        return ResponseEntity.ok(model);
    }
}
