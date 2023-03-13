package com.erickgarcia.ecommerce.service;

import com.erickgarcia.ecommerce.entity.DetallePedido;
import com.erickgarcia.ecommerce.entity.Pedido;
import com.erickgarcia.ecommerce.entity.dto.GenerarPedidoDTO;
import com.erickgarcia.ecommerce.entity.dto.PedidoConDetalleDTO;
import com.erickgarcia.ecommerce.repository.DetallePedidoRepository;
import com.erickgarcia.ecommerce.repository.PedidoRepository;
import com.erickgarcia.ecommerce.repository.PlatilloRepository;
import com.erickgarcia.ecommerce.utils.GenericResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.erickgarcia.ecommerce.utils.Global.*;

@Service
@Transactional
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final DetallePedidoRepository detallePedidoRepository;
    private final DetallePedidoService detallePedidoService;
    private final PlatilloRepository platilloRepository;

    public PedidoService(PedidoRepository pedidoRepository, DetallePedidoRepository detallePedidoRepository, DetallePedidoService detallePedidoService, PlatilloRepository platilloRepository) {
        this.pedidoRepository = pedidoRepository;
        this.detallePedidoRepository = detallePedidoRepository;
        this.detallePedidoService = detallePedidoService;
        this.platilloRepository = platilloRepository;
    }


    //Method to return all the orders with the order details
    public GenericResponse<List<PedidoConDetalleDTO>> devolverMisPedidos(int idCliente){
        final List<PedidoConDetalleDTO> dtos = new ArrayList<>();
        final Iterable<Pedido> pedidos = pedidoRepository.devolverMisPedidos(idCliente);
        pedidos.forEach(pedido -> dtos.add(new PedidoConDetalleDTO(pedido, detallePedidoRepository.findByPedido(pedido.getId()))));

        return new GenericResponse(OPERACION_CORRECTA, RPTA_OK, "Peticion encontrada", dtos);
    }

    //Method to save the order
    public GenericResponse guardarPedido(GenerarPedidoDTO dto){
        Date date  =new Date();
        dto.getPedido().setFechaCompra(new java.sql.Date(date.getTime()));
        dto.getPedido().setAnularPedido(false);
        dto.getPedido().setCantidad(dto.getPedido().getCantidad());
        dto.getPedido().setCliente(dto.getCliente());
        this.pedidoRepository.save(dto.getPedido());
        for (DetallePedido dp: dto.getDetallePedido()) {
            dp.setPedido(dto.getPedido());
            this.platilloRepository.actualizarStock(dp.getCantidad(),dp.getPlatillo().getId());
        }
        this.detallePedidoService.GuardarDetalles(dto.getDetallePedido());
        return new GenericResponse(TIPO_DATA, RPTA_OK, OPERACION_CORRECTA, dto);
    }

    //Method to cancel order
    public GenericResponse anularPedido(int id)
    {
        Pedido p = this.pedidoRepository.findById(id).orElse(new Pedido());
        if(p.getId() != 0){
            p.setAnularPedido(true);
            this.pedidoRepository.save(p);
            return new GenericResponse(TIPO_DATA,RPTA_OK,OPERACION_CORRECTA, p);
        }else{
            return new GenericResponse(TIPO_DATA,RPTA_OK,OPERACION_ERRONEA,"El pedido que desea anular no existe");
        }
    }
}
