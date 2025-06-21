package com.edutech.micros.edutech.service;

import com.edutech.micros.edutech.model.Contenido;
import com.edutech.micros.edutech.model.Pedido;
import com.edutech.micros.edutech.model.Usuario;
import com.edutech.micros.edutech.repository.ContenidoRepository;
import com.edutech.micros.edutech.repository.PedidoRepository;
import com.edutech.micros.edutech.repository.TipoPagoRepository;
import com.edutech.micros.edutech.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**

 @Autor Richard Moreano

 */
@Service
public class ReporteService {

    // Repositorio de usuarios
    @Autowired
    private UsuarioRepository usuarioRepository;

    // Repositorio de cursos
    @Autowired
    private ContenidoRepository contenidoRepository;

    // Repositorio de pedidos
    @Autowired
    private PedidoRepository pedidoRepository;

    // Repositorio de tipos de pago
    @Autowired
    private TipoPagoRepository tipoPagoRepository;

    // Cuenta total de usuarios
    public Long contarUsuarios() {
        return usuarioRepository.count();
    }

    // Cuenta total de cursos
    public Long contarCursos() {
        return contenidoRepository.count();
    }

    // Busca el curso con mayor precio
    public String obtenerCursoMasCaro() {
        return contenidoRepository.findTopByOrderByPrecioDesc()
                .map(c -> c.getTitulo() + " - Precio. " + c.getPrecio())
                .orElse("No hay cursos disponibles");
    }

    // Obtiene el último usuario registrado (por ID más alto)

    public Usuario obtenerUltimoUsuario() {
        return usuarioRepository.findTopByOrderByIdDesc()
                .orElse(null); // El controlador manejará si es null
    }

    /*public String obtenerUltimoUsuario() {
        return usuarioRepository.findTopByOrderByIdDesc()

                .map(u -> u.getNombre() + " " + u.getApellido())
                .orElse("No hay usuarios");
    }*/

    // Agrupa por curso y cuenta cuántas veces fue vendido
    public List<Contenido> obtenerCursosMasVendidos() {
        List<Pedido> pedidos = pedidoRepository.findAll();

        return pedidos.stream()
                .collect(Collectors.groupingBy(Pedido::getContenido, Collectors.counting()))
                .entrySet().stream()
                .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
                .limit(5)
                .map(Map.Entry::getKey) // ahora devuelve el Contenido
                .collect(Collectors.toList());
    }


    /*public List<String> obtenerCursosMasVendidos() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        return pedidos.stream()
                .collect(Collectors.groupingBy(p -> p.getContenido().getTitulo(), Collectors.counting()))
                .entrySet().stream()
                .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
                .limit(5) // Devuelve los 5 cursos más vendidos
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }*/

    // Agrupa por tipo de pago y cuenta cantidad
    public Map<String, Long> obtenerPagosPorTipo() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        return pedidos.stream()
                .collect(Collectors.groupingBy(p -> p.getTipoPago().getTipo(), Collectors.counting()));
    }
}