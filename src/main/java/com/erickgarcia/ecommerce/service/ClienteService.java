package com.erickgarcia.ecommerce.service;

import com.erickgarcia.ecommerce.entity.Cliente;
import com.erickgarcia.ecommerce.repository.ClienteRepository;
import com.erickgarcia.ecommerce.utils.GenericResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.erickgarcia.ecommerce.utils.Global.*;

@Service
@Transactional
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    //Metodo para guardar y actualiar cliente
    public GenericResponse save(Cliente cliente)
    {
        Optional<Cliente> optionalCliente = this.repository.findById(cliente.getId());
        int presentId = optionalCliente.isPresent() ? optionalCliente.get().getId() : 0;
        if(presentId ==0)
        {
            if(repository.existByDoc(cliente.getNumDoc().trim()) == 1){
                return new GenericResponse(TIPO_RESULT, RPTA_WARNING,"Ya existe un cliente con ese DNI", null);
            }else{
                //Guarda
                cliente.setId(presentId);
                return new GenericResponse(TIPO_DATA,RPTA_OK,"Cliente registrado",this.repository.save(cliente));
            }

        }else{
            //Actualizar datos en caso de que exista el cliente
            if(repository.existByDocForUpdate(cliente.getNumDoc().trim(), cliente.getId()) == 1){
                return new GenericResponse(TIPO_RESULT,RPTA_WARNING,"Ya existe un cliente con ese DNI",null);
            }
            else {
                //Actualizar
                cliente.setId(presentId);
                return new GenericResponse(TIPO_DATA,RPTA_OK,"Cliente actualizado",this.repository.save(cliente));
            }
        }
    }

}
