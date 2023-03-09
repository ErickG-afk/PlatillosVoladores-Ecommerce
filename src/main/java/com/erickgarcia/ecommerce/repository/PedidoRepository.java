package com.erickgarcia.ecommerce.repository;

import com.erickgarcia.ecommerce.entity.Pedido;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PedidoRepository extends CrudRepository<Pedido, Integer> {

    @Query("SELECT P FROM Pedido P WHERE P.cliente.id=:idCliente")
    Iterable<Pedido> devolverMisPedidos(int idCliente);
}
