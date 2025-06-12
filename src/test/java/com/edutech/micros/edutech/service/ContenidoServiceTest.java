package com.edutech.micros.edutech.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.edutech.micros.edutech.model.Contenido;
import com.edutech.micros.edutech.repository.ContenidoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
public class ContenidoServiceTest {

    // Inyecta el servicio de Contenido para ser probado.
    @Autowired
    private ContenidoService contenidoService;

    // Crea un mock del repositorio de Contenido para simular su comportamiento.
    @MockBean
    private ContenidoRepository contenidoRepository;

    @Test
    public void testFindAll() {
        // Define el comportamiento del mock: al llamar a findAll() devuelve una lista con un Contenido.
        when(contenidoRepository.findAll()).thenReturn(List.of(crearContenido()));

        // Llama al método findAll() del servicio.
        List<Contenido> contenidos = contenidoService.findAll();

        // Verifica que la lista no sea nula y contenga exactamente un contenido.
        assertNotNull(contenidos);
        assertEquals(1, contenidos.size());
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Contenido contenido = crearContenido();

        // Define el comportamiento del mock: cuando se llame a findById() con 'id', devuelve el contenido.
        when(contenidoRepository.findById(id)).thenReturn(Optional.of(contenido));

        // Llama al método findById() del servicio.
        Contenido found = contenidoService.findById(id);

        // Verifica que el contenido devuelto no sea nulo y su ID coincida con el esperado.
        assertNotNull(found);
        assertEquals(id, found.getId());
    }

    @Test
    public void testSave() {
        Contenido contenido = crearContenido();

        // Define el comportamiento del mock: al llamar a save(), devuelve el mismo contenido.
        when(contenidoRepository.save(contenido)).thenReturn(contenido);

        // Llama al método save() del servicio.
        Contenido saved = contenidoService.save(contenido);

        // Verifica que el contenido guardado no sea nulo y su título coincida con el esperado.
        assertNotNull(saved);
        assertEquals(saved.getId(), contenido.getId());
    }

    @Test
    public void testDeleteById() {
        Long id = 1L;

        // Define el comportamiento del mock: al llamar a deleteById(), no hace nada.
        doNothing().when(contenidoRepository).deleteById(id);

        // Llama al método deleteById() del servicio.
        contenidoService.deleteById(id);

        // Verifica que deleteById() se haya llamado exactamente una vez con ese id.
        verify(contenidoRepository, times(1)).deleteById(id);
    }
    private Contenido crearContenido() {
        return new Contenido(1L, "Videe Java", "Curso Java básico", "Video", 5000);
    }
}

