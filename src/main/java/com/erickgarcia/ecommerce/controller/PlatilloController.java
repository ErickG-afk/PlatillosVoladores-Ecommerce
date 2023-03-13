package com.erickgarcia.ecommerce.controller;

import com.erickgarcia.ecommerce.service.PlatilloService;
import com.erickgarcia.ecommerce.utils.GenericResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/platillo")
public class PlatilloController {

    private final PlatilloService service;

    public PlatilloController(PlatilloService service) {
        this.service = service;
    }

    @GetMapping
    public GenericResponse listarPlatillosRecomendados(){
        return this.service.listarPlatillosRecomendados();
    }

    @GetMapping("/{idC}")
    public GenericResponse listarPlatillosCategory(@PathVariable int idC){
        return this.service.listarPlatillosCategory(idC);
    }
}
