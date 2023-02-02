package com.erickgarcia.ecommerce.service;

import com.erickgarcia.ecommerce.entity.Usuario;
import com.erickgarcia.ecommerce.repository.UsuarioRepository;
import com.erickgarcia.ecommerce.utils.GenericResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.erickgarcia.ecommerce.utils.Global.*;

@Service
@Transactional
public class UsuarioService {
    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    //Metodo para iniciar sesion
    public GenericResponse<Usuario> login(String email, String password)
    {
        Optional<Usuario> optU = this.repository.login(email, password);

        if(optU.isPresent()){
            return new GenericResponse<Usuario>(TIPO_AUTH, RPTA_OK,"Log in successful", optU.get());
        }
        else  return new GenericResponse<Usuario>(TIPO_AUTH, RPTA_WARNING,"Username and password combination incorrect", new Usuario());

    }
}
