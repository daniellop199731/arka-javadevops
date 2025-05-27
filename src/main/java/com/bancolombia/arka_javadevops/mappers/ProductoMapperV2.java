package com.bancolombia.arka_javadevops.mappers;

import org.mapstruct.Mapper;

import com.bancolombia.arka_javadevops.DTO.ExpirableProductoDTO;
import com.bancolombia.arka_javadevops.DTO.ProductoDTO;
import com.bancolombia.arka_javadevops.models.ExpirableProducto;
import com.bancolombia.arka_javadevops.models.Producto;

//PASO 5: Crear el mapper correspondiente manual o automatico;
//SIGUIENTE PASO: Ver ProductoService.java creaNuevoV2

@Mapper(componentModel = "spring")
public interface ProductoMapperV2 {

    ExpirableProducto fromDTO(ExpirableProductoDTO expirableProductoDTO);

    Producto fromDTO(ProductoDTO productoDTO);

    ExpirableProductoDTO toDTO(ExpirableProducto expirableProducto);

    ProductoDTO toDTO(Producto producto);

}
