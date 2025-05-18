package com.edutech.micros.edutech.service;

import com.edutech.micros.edutech.model.Contenido;
import com.edutech.micros.edutech.repository.ContenidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ContenidoService {

    @Autowired
    private ContenidoRepository contenidoRepository;

    public List<Contenido> findAll() {
        return contenidoRepository.findAll();
    }

    public Contenido findById(Long id) {
        return contenidoRepository.findById(id).orElse(null);
    }

    public Contenido save(Contenido contenido) {
        return contenidoRepository.save(contenido);
    }

    public void deleteById(Long id) {
        contenidoRepository.deleteById(id);
    }
}
