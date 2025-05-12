package com.bancolombia.arka_javadevops.mappers;

import org.springframework.stereotype.Component;

import com.bancolombia.arka_javadevops.DTO.ProductoDTO;
import com.bancolombia.arka_javadevops.models.Producto;

@Component //Siempre a los mappers se les debe agregar esta anotacion
public class ProductoMapper {

    //Metodo que recibe obj Producto y los transForma a ProductoDTO
    public ProductoDTO toDto(Producto producto){
        if(producto == null){
            return null;
        }

        String nombreCategoria = producto.getCategoria() != null ? 
            producto.getCategoria().getNombreCategoria() : null;

        return new ProductoDTO(
            producto.getIdProducto()
            , producto.getNombreProducto()
            , producto.getPrecioProducto()
            , nombreCategoria
        );
    }

}
