package com.erickgarcia.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    @JsonFormat(pattern = "dd-MM-yyyy", timezone = " Europe/Brussels")
    private Date fechaCompra;
    @ManyToOne
    private Cliente cliente;
    @Column
    private Double cantidad;
    @Column
    private boolean anularPedido;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public boolean isAnularPedido() {
        return anularPedido;
    }

    public void setAnularPedido(boolean anularPedido) {
        this.anularPedido = anularPedido;
    }
}
