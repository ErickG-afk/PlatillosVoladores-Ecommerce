package com.erickgarcia.ecommerce.service;

import com.erickgarcia.ecommerce.entity.DocumentoAlmacenado;
import com.erickgarcia.ecommerce.repository.DocumentoAlmacenadoRepository;
import com.erickgarcia.ecommerce.utils.GenericResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.HashMap;

import static com.erickgarcia.ecommerce.utils.Global.*;


@Service
@Transactional
public class DocumentoAlmacenadoService {
    private DocumentoAlmacenadoRepository repo;
    private FileStorageService storageService;

    public DocumentoAlmacenadoService(DocumentoAlmacenadoRepository repo, FileStorageService storageService) {
        this.repo = repo;
        this.storageService = storageService;
    }


    //Crear lista con las imagenes
    public GenericResponse<Iterable<DocumentoAlmacenado>> list() {
        return new GenericResponse<Iterable<DocumentoAlmacenado>>(TIPO_RESULT, RPTA_OK, OPERACION_CORRECTA, repo.list());
    }

//Encontrar las imagenes
    public GenericResponse find(Long aLong) {
        return null;
    }



    //Metodo para guardar un archivo
    public GenericResponse save(DocumentoAlmacenado obj) {
        String fileName = (repo.findById(obj.getId())).orElse(new DocumentoAlmacenado()).getFileName();

        String originalFilename = obj.getFile().getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));

        fileName = storageService.storeFile(obj.getFile(), fileName);

        obj.setFileName(fileName);
        obj.setExtension(extension);

        return new GenericResponse(TIPO_DATA, RPTA_OK,OPERACION_CORRECTA,repo.save(obj));
    }


    //Descargar un archivo
    public ResponseEntity<Resource> download(String completefileName, HttpServletRequest request) {
        Resource resource = storageService.loadResource(completefileName);
        String contentType = null;

        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    public ResponseEntity<Resource> downloadByFileName(String fileName, HttpServletRequest request) {
        DocumentoAlmacenado doc = repo.findByFileName(fileName).orElse(new DocumentoAlmacenado());
        return download(doc.getCompleteFileName(), request);
    }


    public GenericResponse delete(Long aLong) {
        return null;
    }

    public HashMap<String, Object> validate(DocumentoAlmacenado obj) {
        return null;
    }
}
