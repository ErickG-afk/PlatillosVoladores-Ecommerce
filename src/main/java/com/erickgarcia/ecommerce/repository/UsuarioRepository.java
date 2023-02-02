package com.erickgarcia.ecommerce.repository;

import com.erickgarcia.ecommerce.entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {
    @Query("SELECT U FROM Usuario U where U.email =:email AND U.clave=:password")
    Optional<Usuario> login(String email, String password);

}
