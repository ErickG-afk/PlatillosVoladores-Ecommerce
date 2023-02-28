package com.erickgarcia.ecommerce.repository;

import com.erickgarcia.ecommerce.entity.Category;
import com.erickgarcia.ecommerce.entity.Platillo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PlatilloRepository extends CrudRepository<Platillo, Integer> {

    @Query("SELECT P FROM Platillo P WHERE P.recomendado = '1'")
    Iterable<Platillo> listarPlatillosRecomendados();

    @Query("SELECT P FROM Platillo P WHERE P.categoria.id=:idC")
    Iterable<Platillo> listarPlatillosPorCategoria(int idC);

}
