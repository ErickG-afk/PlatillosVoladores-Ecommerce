package com.erickgarcia.ecommerce.entity.dto;

import com.erickgarcia.ecommerce.entity.DetallePedido;
import com.erickgarcia.ecommerce.entity.Pedido;

import java.util.ArrayList;

public class PedidoConDetalleDTO {
    private Pedido pedido;
    private Iterable<DetallePedido> detallePedidos;

    public PedidoConDetalleDTO() {
        this.pedido = new Pedido();
        this.detallePedidos = new ArrayList<>();
    }

    public PedidoConDetalleDTO(Pedido pedido, Iterable<DetallePedido> detallePedido) {
        this.pedido = pedido;
        this.detallePedidos = detallePedido;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Iterable<DetallePedido> getDetallePedidos() {
        return detallePedidos;
    }

    public void setDetallePedidos(Iterable<DetallePedido> detallePedidos) {
        this.detallePedidos = detallePedidos;
    }
}
