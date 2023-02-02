package com.erickgarcia.ecommerce.service;

import com.erickgarcia.ecommerce.config.FileStorageProperties;
import com.erickgarcia.ecommerce.exception.FileStorageException;
import com.erickgarcia.ecommerce.exception.MyFileNotFoundException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageService {
    private FileStorageProperties fileStorageProperties;

    public FileStorageService(FileStorageProperties fileStorageProperties) {
        this.fileStorageProperties = fileStorageProperties;
    }

    //Funcion para almacenar el archivo
    public String storeFile(MultipartFile file, String fileName) {
        String originalName = StringUtils.cleanPath(file.getOriginalFilename());
        String extension = originalName.substring(originalName.lastIndexOf("."));

        if (fileName.equals("")) {
            fileName = UUID.randomUUID().toString();
        }

        Path fileStorageLocation = getFileStorageLocation(getFolderName(originalName));
        Path targetLocation = fileStorageLocation.resolve(fileName + extension);
        try {
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;

        } catch (IOException e) {
            throw new FileStorageException("No se pudo almacenar el archivo", e);
        }
    }


    //Funcion para recoger el nombre del archivo
    private String getFolderName(String completeFileName) {
        String extension = completeFileName.substring(completeFileName.lastIndexOf("."));
        return extension.replace(".", "").toUpperCase();
    }

    //Funcion para crear el nombnre del cirectorio con el nombre de terminacion del archivo
    private Path getFileStorageLocation(String folderName) {
        Path fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir() + "/" + folderName).toAbsolutePath().normalize();
        try {
            Files.createDirectories(fileStorageLocation);
            return fileStorageLocation;
        } catch (IOException e) {
            throw new FileStorageException("No se pudo crear el directorio", e);
        }
    }


    //Cargar el archivo
    public Resource loadResource(String completeFileName) {
        Path fileStorageLocation = getFileStorageLocation(getFolderName(completeFileName));
        Path path = fileStorageLocation.resolve(completeFileName).normalize();
        try {
            Resource resource = new UrlResource(path.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("Archivo no encontrado: " + completeFileName);
            }

        } catch (MalformedURLException e) {
            throw new MyFileNotFoundException("Ha ocurrido un error al intentar acceder al archivo: " + completeFileName, e);
        }
    }
}
















