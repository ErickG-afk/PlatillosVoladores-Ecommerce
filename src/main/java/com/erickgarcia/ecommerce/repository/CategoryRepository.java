package com.erickgarcia.ecommerce.repository;

import com.erickgarcia.ecommerce.entity.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface CategoryRepository extends CrudRepository<Category, Integer> {

    @Query("SELECT C FROM Category C WHERE C.vigencia = '1'")
       Iterable<Category> listarCategoriasActivas();

}
