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
        else  return new GenericResponse<Usuario>(TIPO_AUTH, RPTA_WARNING,"Username and password incorrect", new Usuario());

    }

    //Metodo para guardar credenciales del usuario

    public GenericResponse guardarUsuario(Usuario usuario)
    {
        Optional<Usuario> optionalUsuario = this.repository.findById(usuario.getId());
        int idf = optionalUsuario.isPresent() ? optionalUsuario.get().getId() : 0;
        if(idf ==0)
            return new GenericResponse(TIPO_DATA,RPTA_OK,"Usuario registrado con exito",this.repository.save(usuario));
        else
            return new GenericResponse(TIPO_DATA,RPTA_OK,"Datos del usuario actualizados",this.repository.save(usuario));
    }

}
