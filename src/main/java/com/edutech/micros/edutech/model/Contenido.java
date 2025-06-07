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

    private String titulo;
    private String descripcion;
    private String tipo;
    private int precio;
}
