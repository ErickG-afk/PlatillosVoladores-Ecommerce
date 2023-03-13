package com.erickgarcia.ecommerce.service;

import com.erickgarcia.ecommerce.repository.CategoryRepository;
import com.erickgarcia.ecommerce.utils.GenericResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import static com.erickgarcia.ecommerce.utils.Global.*;

@Service
@Transactional
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public GenericResponse listarCategoriasActivas(){
        try {
            return new GenericResponse(TIPO_DATA, RPTA_OK, OPERACION_CORRECTA,
                    this.categoryRepository.listarCategoriasActivas());
        }catch (Exception e){
            System.out.println(e.getMessage().toString());
        }
        return new GenericResponse();
    }
}
