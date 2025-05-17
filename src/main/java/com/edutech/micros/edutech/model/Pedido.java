package com.edutech.micros.edutech.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pedido")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int precio;

    @ManyToOne
    @JoinColumn(name = "id_usuario_fk", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_curso_fk", nullable = false)
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "id_tipoPedido_fk", nullable = false)
    private Pedido pedido;
}
