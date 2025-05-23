package com.edutech.micros.edutech.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
// añade constructures vacios y completos

@Entity
//Especifica el nombre de la tabla en la base de datos a la que se mapeará esta entidad

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
// el unique hace que no se pueda generar en este caso otro run que sea igual y el nullable es columna no puede contener valores nulo
    @Column(length = 100, nullable = false)
    private String nombre;
// length para el largo del dato

    @Column(length = 100, nullable = false)
    private String apellido;

    @Column(unique = true, length = 100, nullable = false)
    private String correo;
// conecta usuario con pedido con la foreign key id de pedido llama todos los atributos

    @OneToMany(mappedBy = "usuario")
    private List<Pedido> pedidos;


}

