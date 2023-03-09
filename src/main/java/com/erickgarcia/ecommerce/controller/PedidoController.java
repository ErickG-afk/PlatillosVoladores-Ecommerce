package com.erickgarcia.ecommerce.controller;

import com.erickgarcia.ecommerce.entity.dto.PedidoConDetalleDTO;
import com.erickgarcia.ecommerce.service.PedidoService;
import com.erickgarcia.ecommerce.utils.GenericResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/pedido")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    //Hacer lista de pedidos con sus detalles
    @GetMapping("/misPedidos/{idCliente}")
    public GenericResponse<List<PedidoConDetalleDTO>> devolverMisPedidosConDetalles(@PathVariable int idCliente){
        return this.pedidoService.devolverMisPedidos(idCliente);
    }
}
