package com.bancolombia.arka_javadevops.services;

import org.springframework.context.annotation.Primary;

import com.bancolombia.arka_javadevops.models.Producto;
import com.bancolombia.arka_javadevops.repositories.ProductoRepository;
import com.bancolombia.arka_javadevops.services.interfaces.ProductoServiceUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Primary
public class ProductoServiceUtilsImp implements ProductoServiceUtils {

    private final ProductoRepository productoRepository;

    public boolean existeProductoPorReferencia(String referenciaProducto){
        if(referenciaProducto != null){
            Producto productoPorReferencia = productoRepository.findByReferenciaProducto(referenciaProducto);
            if(productoPorReferencia != null){
                productoPorReferencia = null;
                return true;
            }
        }
        return false;
    }

    public boolean existeProductoPorReferenciaParaActualizar(Producto productoActualizar){
        if(productoActualizar.getReferenciaProducto() != null){
            Producto productoPorReferencia = productoRepository.findByReferenciaProducto(productoActualizar.getReferenciaProducto());
            if(productoPorReferencia != null && 
                ((productoPorReferencia != null ? productoPorReferencia.getIdProducto():0)
                    !=
                    (productoActualizar.getIdProducto())) ){
                productoPorReferencia = null;
                return true;
            }
        }
        return false;
    }    

}
