package com.erickgarcia.ecommerce.controller;


import com.erickgarcia.ecommerce.entity.Usuario;
import com.erickgarcia.ecommerce.service.UsuarioService;
import com.erickgarcia.ecommerce.utils.GenericResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
@PostMapping("/login")
    public GenericResponse<Usuario> login(HttpServletRequest request){
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        return this.usuarioService.login(email, password);
    }

    @PostMapping
    public GenericResponse save(@RequestBody Usuario usuario)
    {
        return this.usuarioService.guardarUsuario(usuario);
    }

    @PutMapping("/{id}")
    public GenericResponse update(@PathVariable int id ,@RequestBody Usuario usuario){
        return this.usuarioService.guardarUsuario(usuario);
    }

}
