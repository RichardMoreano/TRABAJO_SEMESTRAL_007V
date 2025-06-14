package com.edutech.micros.edutech.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

/**
  @Autor Richard Moreano
 */
@Entity
@Table(name = "tipo_Pago")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Entidad que representa un tipo de pago")
public class TipoPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único del tipo de pago", example = "1")
    private long id;

    @Schema(description = "Tipo de pago", example = "Efectivo")
    private String tipo;
}



/*
-- SQL para insertar los tipos de pago en la base
-- de datos en las tabla TipoPago


-- TIPOS DE PAGO
INSERT INTO tipo_Pago (id, tipo) VALUES (1, 'Tarjeta');
INSERT INTO tipo_Pago (id, tipo) VALUES (2, 'Efectivo');
INSERT INTO tipo_Pago (id, tipo) VALUES (3, 'Transferencia');

-- CURSOS (contenido)
INSERT INTO contenido (id, titulo, descripcion, tipo, precio) VALUES
(1, 'Java Básico', 'Curso de introducción a Java', 'Programación', 150000),
(2, 'Spring Boot Pro', 'Desarrollo web con Spring Boot', 'Programación', 300000),
(3, 'Diseño UX/UI', 'Diseño centrado en el usuario', 'Diseño', 180000),
(4, 'SQL para principiantes', 'Bases de datos relacionales', 'Bases de datos', 120000),
(5, 'Docker y Kubernetes', 'Contenedores y orquestación', 'DevOps', 350000);

-- USUARIOS
INSERT INTO usuario (id, run, nombre, apellido, correo) VALUES
(1, '12345678-9', 'Ana', 'González', 'ana@example.com'),
(2, '23456789-0', 'Luis', 'Martínez', 'luis@example.com'),
(3, '34567890-1', 'Carla', 'Duarte', 'carla@example.com'),
(4, '45678901-2', 'Pedro', 'Benítez', 'pedro@example.com'),
(5, '56789012-3', 'María', 'Rojas', 'maria@example.com');

-- PEDIDOS
INSERT INTO pedido (id, fecha, id_tipo_Pago_fk, id_usuario_fk, id_contenido_fk) VALUES
(1, '2024-01-15 10:00:00', 1, 1, 2), -- Ana compra Spring Boot Pro con Tarjeta
(2, '2024-02-10 15:30:00', 2, 2, 1), -- Luis compra Java Básico con Efectivo
(3, '2024-03-05 12:00:00', 3, 3, 3), -- Carla compra UX/UI con Transferencia
(4, '2024-04-20 08:45:00', 1, 4, 5), -- Pedro compra Docker y Kubernetes con Tarjeta
(5, '2024-05-01 11:00:00', 2, 5, 2); -- María compra Spring Boot Pro con Efectivo
*/
