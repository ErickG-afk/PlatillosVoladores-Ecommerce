package com.erickgarcia.ecommerce.service;

import com.erickgarcia.ecommerce.repository.PlatilloRepository;
import com.erickgarcia.ecommerce.utils.GenericResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import static com.erickgarcia.ecommerce.utils.Global.*;

@Service
@Transactional
public class PlatilloService {

    private final PlatilloRepository repository;

    public PlatilloService(PlatilloRepository repository) {
        this.repository = repository;
    }

    public GenericResponse listarPlatillosRecomendados(){
        return new GenericResponse(TIPO_DATA,RPTA_OK,OPERACION_CORRECTA,this.repository.listarPlatillosRecomendados());
    }

    public GenericResponse listarPlatillosCategory(int idC){
        return new GenericResponse(TIPO_DATA,RPTA_OK,OPERACION_CORRECTA,this.repository.listarPlatillosPorCategoria(idC));
    }
}
