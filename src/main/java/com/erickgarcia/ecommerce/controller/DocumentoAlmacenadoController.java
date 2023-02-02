package com.erickgarcia.ecommerce.controller;

import com.erickgarcia.ecommerce.entity.DocumentoAlmacenado;
import com.erickgarcia.ecommerce.service.DocumentoAlmacenadoService;
import com.erickgarcia.ecommerce.utils.GenericResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/documento-almacenado")
public class DocumentoAlmacenadoController {
    private final DocumentoAlmacenadoService service;

    public DocumentoAlmacenadoController(DocumentoAlmacenadoService service) {
        this.service = service;
    }


    //Hacer lista de documentos almacenados
    @GetMapping
    public GenericResponse list() {
        return service.list();
    }

    //Acceder a documento por ID
    @GetMapping("/{id}")
    public GenericResponse find(@PathVariable Long id) {
        return null;
    }


    //Descargar un archivo
    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> download(@PathVariable String fileName, HttpServletRequest request) {
        return service.downloadByFileName(fileName, request);
    }

    //Guardar documento
    @PostMapping
    public GenericResponse save(@ModelAttribute DocumentoAlmacenado obj) {
        return service.save(obj);
    }

    public GenericResponse update(Long aLong, DocumentoAlmacenado obj) {
        return null;
    }

    public GenericResponse delete(Long aLong) {
        return null;
    }
}
