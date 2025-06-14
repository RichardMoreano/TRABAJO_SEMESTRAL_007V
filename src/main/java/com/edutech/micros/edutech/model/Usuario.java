package com.edutech.micros.edutech.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**

 @Autor dominique Cofre

 */


@Entity
@Table(name = "usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 13, nullable = false)
    private String run;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(length = 100, nullable = false)
    private String apellido;

    @Column(unique = true, length = 100, nullable = false)
    private String correo;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Pedido> pedidos;

    /*@Schema(description = "Entidad que representa un usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único del usuario", example = "1")
    private Long id;

    @Column(unique = true, length = 13, nullable = false)
    @Schema(description = "RUN del usuario", example = "12345678-9")
    private String run;

    @Column(length = 100, nullable = false)
    @Schema(description = "Nombre del usuario", example = "Benito")
    private String nombre;

    @Column(length = 100, nullable = false)
    @Schema(description = "Apellido del usuario", example = "Camelo")
    private String apellido;

    @Column(unique = true, length = 100, nullable = false)
    @Schema(description = "Correo electrónico del usuario", example = "benito.camelo@example.com")
    private String correo;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonManagedReference
    @ArraySchema(schema = @Schema(description = "Lista de pedidos del usuario"))
    private List<Pedido> pedidos;
}*/

}
