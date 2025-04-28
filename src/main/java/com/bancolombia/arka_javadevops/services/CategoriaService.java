package com.bancolombia.arka_javadevops.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bancolombia.arka_javadevops.models.Categoria;
import com.bancolombia.arka_javadevops.repositories.CategoriaRepository;
import com.bancolombia.arka_javadevops.utils.ResponseObject;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    private static ResponseObject rObj;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public ResponseObject obtenerCategorias(){
        rObj = new ResponseObject();
        rObj.setMsj("Consulta ejecutada con exito");
        rObj.setAsSuccessfully();
        rObj.setObj(categoriaRepository.findAll());
        return rObj;
    }

    public ResponseObject obtenerCategoriasPorNombre(String nombreCategoria){
        rObj = new ResponseObject();
        List<Categoria> categorias = categoriaRepository.findByNombreCategoriaLike("%"+nombreCategoria+"%");
        if(categorias.size() > 0){
            rObj.setMsj("Categorias encontradas");
            rObj.setAsSuccessfully();
            rObj.setObj(categorias);
        } else {
            rObj.setMsj("No se encontraron categorias");
            rObj.setAsSuccessfully();
        }
        return rObj;
    }

    public ResponseObject crearNueva(Categoria categoria){
        rObj = new ResponseObject();
        List<Categoria> categorias = categoriaRepository.findByNombreCategoria(categoria.getNombreCategoria());
        if(categorias.size() > 0){
            rObj.setMsj("La categoria ".concat(categoria.getNombreCategoria())
                .concat(" ya existe"));
        } else {
            rObj.setObj(categoriaRepository.save(categoria));
            rObj.setMsj("Categoria creada con exito");
            rObj.setAsSuccessfully();
        }
        return rObj;
    }

    public ResponseObject actualizarCategoria(Categoria categoria){
        rObj = new ResponseObject();
        Optional<Categoria> categoriaEncontrada = categoriaRepository.findById(categoria.getIdCategoria());
        if(categoriaEncontrada.isPresent()){
            rObj.setObj(categoriaRepository.save(categoria));
            rObj.setMsj("Categoria actualizada con exito");
            rObj.setAsSuccessfully();
        } else {
            rObj.setMsj("La categoria a actualizar no existe");
        }
        return rObj;
    }

}
