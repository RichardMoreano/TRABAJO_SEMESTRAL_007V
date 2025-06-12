package com.edutech.micros.edutech.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "contenido")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contenido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150, nullable = false)
    private String titulo;

    @Column(length = 500)
    private String descripcion;

    @Column(length = 50, nullable = false)
    private String tipo;

    @Column(nullable = false)
    private int precio;
}
