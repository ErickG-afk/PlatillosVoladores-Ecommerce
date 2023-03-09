package com.erickgarcia.ecommerce.service;

import com.erickgarcia.ecommerce.entity.Pedido;
import com.erickgarcia.ecommerce.entity.dto.PedidoConDetalleDTO;
import com.erickgarcia.ecommerce.repository.DetallePedidoRepository;
import com.erickgarcia.ecommerce.repository.PedidoRepository;
import com.erickgarcia.ecommerce.utils.GenericResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.erickgarcia.ecommerce.utils.Global.*;

@Service
@Transactional
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final DetallePedidoRepository detallePedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository, DetallePedidoRepository detallePedidoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.detallePedidoRepository = detallePedidoRepository;
    }

    public GenericResponse<List<PedidoConDetalleDTO>> devolverMisPedidos(int idCliente){
        final List<PedidoConDetalleDTO> dtos = new ArrayList<>();
        final Iterable<Pedido> pedidos = pedidoRepository.devolverMisPedidos(idCliente);
        pedidos.forEach(pedido -> dtos.add(new PedidoConDetalleDTO(pedido, detallePedidoRepository.findByPedido(pedido.getId()))));

        return new GenericResponse(OPERACION_CORRECTA, RPTA_OK, "Peticion encontrada", dtos);
    }
}
