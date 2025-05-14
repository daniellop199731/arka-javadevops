package com.bancolombia.arka_javadevops.services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.bancolombia.arka_javadevops.models.Categoria;
import com.bancolombia.arka_javadevops.repositories.CategoriaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public List<Categoria> obtenerCategorias(){
        return (List<Categoria>) categoriaRepository.findAll();
    }

    public List<Categoria> obtenerCategoriasPorNombre(String nombreCategoria){
        return categoriaRepository.findByNombreCategoriaLike("%"+nombreCategoria+"%");
    }

    public Categoria crearNueva(Categoria categoria){
        List<Categoria> categorias = categoriaRepository.findByNombreCategoria(categoria.getNombreCategoria());
        if(categorias.size() > 0){
            return null;
        }
        return categoriaRepository.save(categoria);
    }

    public Categoria actualizarCategoria(int idCategoria, Categoria categoria){
        Optional<Categoria> categoriaEncontrada = categoriaRepository.findById(idCategoria);
        if(categoriaEncontrada.isPresent()){
            categoria.setIdCategoria(idCategoria);
            return categoriaRepository.save(categoria);
        }
        return null;
    }

}
