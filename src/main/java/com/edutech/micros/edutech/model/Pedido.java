package com.edutech.micros.edutech.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

/**
  @Autor Richard Moreano
 */
@Entity
@Table(name = "pedido")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Entidad que representa un pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único del pedido", example = "1")
    private Long id;

    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Schema(description = "Fecha del pedido", example = "2023-06-14T10:00:00Z")
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "id_tipo_Pago_fk", nullable = false)
    @Schema(description = "Tipo de pago asociado al pedido")
    private TipoPago tipoPago;

    @ManyToOne
    @JoinColumn(name = "id_usuario_fk", nullable = false)
    @JsonBackReference
    @Schema(description = "Usuario que realizó el pedido")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_contenido_fk", nullable = false)
    @Schema(description = "Contenido asociado al pedido")
    private Contenido contenido;
}
