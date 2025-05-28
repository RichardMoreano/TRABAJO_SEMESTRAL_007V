package com.edutech.micros.edutech.repository;

import com.edutech.micros.edutech.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**

 @Autor Richard Moreano

 */
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
