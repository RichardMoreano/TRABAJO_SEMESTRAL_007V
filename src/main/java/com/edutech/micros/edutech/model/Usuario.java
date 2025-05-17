package com.edutech.micros.edutech.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
// se genera un id unico para cada usuario
    @Column(unique = true, length = 13, nullable = false)
    private String run;
// el unique hace que no se pueda generar en este caso otro run y el nullable es para que de nulo si ya esta un dato
    @Column(length = 100, nullable = false)
    private String nombre;
// length para el largo del dato

    @Column(length = 100, nullable = false)
    private String apellido;

    @Column(unique = true, length = 100, nullable = false)
    private String correo;
// conecta usuario con pedido con la foreign key id de pedido llama todos los atributos

    @ManyToOne
    @JoinColumn(name = "id_pedido_fk", nullable = false)
    private Pedido pedido;

}

