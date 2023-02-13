package com.erickgarcia.ecommerce.controller;

import com.erickgarcia.ecommerce.entity.Cliente;
import com.erickgarcia.ecommerce.service.ClienteService;
import com.erickgarcia.ecommerce.utils.GenericResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/cliente")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @PostMapping
    public GenericResponse save(@Validated @RequestBody Cliente cliente){
        return this.service.save(cliente);
    }

    @PutMapping("/{id}")
    public GenericResponse update(@PathVariable int id, @Validated @RequestBody Cliente cliente){
        cliente.setId(id);
        return this.service.save(cliente);
    }

}
