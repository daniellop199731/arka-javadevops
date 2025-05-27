package com.bancolombia.arka_javadevops.services;

import java.util.Objects;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.bancolombia.arka_javadevops.DTO.ProductoDTO;
import com.bancolombia.arka_javadevops.mappers.ProductoMapper;
import com.bancolombia.arka_javadevops.models.ExpirableProducto;
import com.bancolombia.arka_javadevops.models.Producto;
import com.bancolombia.arka_javadevops.repositories.ProductoRepository;
import com.bancolombia.arka_javadevops.services.interfaces.ProductoServiceCreate;
import com.bancolombia.arka_javadevops.services.interfaces.ProductoServiceUtils;
import com.bancolombia.arka_javadevops.utils.ResponseObject;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Primary
public class ProductoServiceCreateImp implements ProductoServiceCreate {

    private final ProductoRepository productoRepository;

    private final ProductoServiceUtils productoServiceUtils;

    private final ProductoMapper productoMapper;

    private static ResponseObject rObj;
    
    public ResponseObject crearNuevo(Producto producto){
        rObj = new ResponseObject();
        if(productoServiceUtils.existeProductoPorReferencia(producto.getReferenciaProducto())){
            rObj.setMsj("Ya existe un producto con la referencia ".concat(producto.getReferenciaProducto()));
            rObj.setObj("");
            return rObj;
        }

        rObj.setObj(productoRepository.save(producto));
        rObj.setMsj("Producto guardado con exito");
        rObj.setAsSuccessfully();

        return rObj;
    }

    //PASO 5: Crear nuevo servicio o modificar el existente para en los parametros
    //se tenga la calse extendida
    //SIGUIENTE PASO: Ver ProductoController.java
    public ResponseObject crearNuevoV2(ExpirableProducto producto){
        rObj = new ResponseObject();
        Producto productoToPersist = null;
        if(Objects.isNull(producto.getFechaExpiracion())){
            //productoToPersist = productoMapper.fromDTO((ProductoDTO) producto);
            productoToPersist = (Producto) producto;
        } else {
            productoToPersist = producto;
        }

        ProductoDTO productoToReturn = null;
        Producto resultDB = productoRepository.save(productoToPersist);
        if(resultDB instanceof ExpirableProducto){
            productoToReturn = productoMapper.toDto((ExpirableProducto) resultDB);
        } else {
            productoToReturn = productoMapper.toDto(resultDB);
        }
        rObj.setAsSuccessfully("Producto creado con exito", productoToReturn);
        return rObj;

    }

    public ResponseObject crearNuevoDto(Producto producto){
        rObj = new ResponseObject();
        if(productoServiceUtils.existeProductoPorReferencia(producto.getReferenciaProducto())){
            rObj.setMsj("Ya existe un producto con la referencia ".concat(producto.getReferenciaProducto()));
            rObj.setObj("");
            return rObj;
        }

        productoRepository.save(producto);
        rObj.setObj(productoMapper.toDto(producto));
        rObj.setMsj("Producto guardado con exito");
        rObj.setAsSuccessfully();

        return rObj;
    }    

}
