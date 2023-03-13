package com.erickgarcia.ecommerce.controller;

import com.erickgarcia.ecommerce.service.CategoryService;
import com.erickgarcia.ecommerce.utils.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/category")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public GenericResponse listarCategoriasActivas(){
        return this.categoryService.listarCategoriasActivas();
    }
}
