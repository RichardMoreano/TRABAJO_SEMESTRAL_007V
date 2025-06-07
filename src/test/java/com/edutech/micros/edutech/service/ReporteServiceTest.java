package com.edutech.micros.edutech.service;

import com.edutech.micros.edutech.model.Contenido;
import com.edutech.micros.edutech.model.Pedido;
import com.edutech.micros.edutech.model.TipoPago;
import com.edutech.micros.edutech.model.Usuario;
import com.edutech.micros.edutech.repository.ContenidoRepository;
import com.edutech.micros.edutech.repository.PedidoRepository;
import com.edutech.micros.edutech.repository.TipoPagoRepository;
import com.edutech.micros.edutech.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ReporteServiceTest {

    @InjectMocks
    private ReporteService reporteService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private ContenidoRepository contenidoRepository;

    @Mock
    private PedidoRepository pedidoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testContarUsuarios() {
        when(usuarioRepository.count()).thenReturn(5L);

        Long totalUsuarios = reporteService.contarUsuarios();

        assertEquals(5L, totalUsuarios);
        verify(usuarioRepository, times(1)).count();
    }

    @Test
    public void testContarCursos() {
        when(contenidoRepository.count()).thenReturn(3L);

        Long totalCursos = reporteService.contarCursos();

        assertEquals(3L, totalCursos);
        verify(contenidoRepository, times(1)).count();
    }

    @Test
    public void testObtenerCursoMasCaro() {
        Contenido cursoCaro = new Contenido();
        cursoCaro.setTitulo("Curso Avanzado");
        cursoCaro.setPrecio(500000);

        when(contenidoRepository.findTopByOrderByPrecioDesc()).thenReturn(Optional.of(cursoCaro));

        String resultado = reporteService.obtenerCursoMasCaro();

        assertEquals("Curso Avanzado - Precio. 500000", resultado);
        verify(contenidoRepository, times(1)).findTopByOrderByPrecioDesc();
    }

    @Test
    public void testObtenerUltimoUsuario() {
        Usuario ultimoUsuario = new Usuario();
        ultimoUsuario.setNombre("María");
        ultimoUsuario.setApellido("Rojas");

        when(usuarioRepository.findTopByOrderByIdDesc()).thenReturn(Optional.of(ultimoUsuario));

        String resultado = reporteService.obtenerUltimoUsuario();

        assertEquals("María Rojas", resultado);
        verify(usuarioRepository, times(1)).findTopByOrderByIdDesc();
    }

    @Test
    public void testObtenerCursosMasVendidos() {
        Contenido curso1 = new Contenido();
        curso1.setTitulo("Java Básico");
        Contenido curso2 = new Contenido();
        curso2.setTitulo("Spring Boot Pro");

        Pedido pedido1 = new Pedido();
        pedido1.setContenido(curso1);
        Pedido pedido2 = new Pedido();
        pedido2.setContenido(curso1);
        Pedido pedido3 = new Pedido();
        pedido3.setContenido(curso2);

        when(pedidoRepository.findAll()).thenReturn(Arrays.asList(pedido1, pedido2, pedido3));

        List<String> cursosMasVendidos = reporteService.obtenerCursosMasVendidos();

        assertEquals(Arrays.asList("Java Básico", "Spring Boot Pro"), cursosMasVendidos);
        verify(pedidoRepository, times(1)).findAll();
    }

    @Test
    public void testObtenerPagosPorTipo() {
        TipoPago tipoTarjeta = new TipoPago();
        tipoTarjeta.setTipo("Tarjeta");
        TipoPago tipoEfectivo = new TipoPago();
        tipoEfectivo.setTipo("Efectivo");

        Pedido pedido1 = new Pedido();
        pedido1.setTipoPago(tipoTarjeta);
        Pedido pedido2 = new Pedido();
        pedido2.setTipoPago(tipoEfectivo);
        Pedido pedido3 = new Pedido();
        pedido3.setTipoPago(tipoTarjeta);

        when(pedidoRepository.findAll()).thenReturn(Arrays.asList(pedido1, pedido2, pedido3));

        Map<String, Long> pagosPorTipo = reporteService.obtenerPagosPorTipo();

        Map<String, Long> expected = new HashMap<>();
        expected.put("Tarjeta", 2L);
        expected.put("Efectivo", 1L);

        assertEquals(expected, pagosPorTipo);
        verify(pedidoRepository, times(1)).findAll();
    }
}
