package com.erickgarcia.ecommerce.service;

import com.erickgarcia.ecommerce.entity.DetallePedido;
import com.erickgarcia.ecommerce.repository.DetallePedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DetallePedidoService {
    private final DetallePedidoRepository repository;

    public DetallePedidoService(DetallePedidoRepository repository) {
        this.repository = repository;
    }

    public void GuardarDetalles(Iterable<DetallePedido> detallePedidos){
        this.repository.saveAll(detallePedidos);
    }

}
