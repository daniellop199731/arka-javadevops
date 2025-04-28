package com.bancolombia.arka_javadevops.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bancolombia.arka_javadevops.models.Categoria;

@Repository
public interface CategoriaRepository extends CrudRepository<Categoria, Integer> {

    List<Categoria> findByNombreCategoriaLike(String nombreCategoria);

    List<Categoria> findByNombreCategoria(String nombreCategoria);

}
