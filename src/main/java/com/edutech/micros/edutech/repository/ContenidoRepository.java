package com.edutech.micros.edutech.repository;

import com.edutech.micros.edutech.model.Contenido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContenidoRepository extends JpaRepository<Contenido, Long> {
}
