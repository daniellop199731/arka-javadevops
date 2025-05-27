package com.bancolombia.arka_javadevops.services;

import java.util.Optional;

import org.springframework.context.annotation.Primary;

import com.bancolombia.arka_javadevops.models.Producto;
import com.bancolombia.arka_javadevops.repositories.ProductoRepository;
import com.bancolombia.arka_javadevops.services.interfaces.ProductoServiceMod;
import com.bancolombia.arka_javadevops.services.interfaces.ProductoServiceUtils;
import com.bancolombia.arka_javadevops.utils.ResponseObject;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Primary
public class ProductoServiceModImp implements ProductoServiceMod {

    private final ProductoRepository productoRepository;
    private final ProductoServiceUtils productoServiceUtils;

    private static ResponseObject rObj;    

    public ResponseObject actualizar(int idProducto, Producto producto){
        rObj = new ResponseObject();
        Optional<Producto> productoEncontrado = productoRepository.findById(idProducto);
        if(productoEncontrado.isPresent()){
            producto.setIdProducto(idProducto);
            if(productoServiceUtils.existeProductoPorReferenciaParaActualizar(producto)){
                rObj.setMsj("Ya existe un producto con la referencia ".concat(producto.getReferenciaProducto()));
                rObj.setObj("");
                return rObj;
            }                

            rObj.setAsSuccessfully(); 
            rObj.setMsj("Producto actualizado con exito");
            rObj.setObj(productoRepository.save(producto));
            return rObj;                             
        }      

        rObj.setMsj("El producto a actualizar no existe");
        return rObj;
    }

    public ResponseObject eliminar(int idProducto){
        rObj = new ResponseObject();
        Optional<Producto> producto = productoRepository.findById(idProducto);
        if(producto.isPresent()){
            productoRepository.deleteById(idProducto);
            rObj.setMsj("El Producto "
                .concat(producto.get().getNombreProducto())
                .concat("-")
                .concat(producto.get().getIdProducto()+"")
                .concat(" fue eliminado exitosamente"));
            rObj.setAsSuccessfully();
            rObj.setObj(producto);            
        } else {
            rObj.setMsj("No se encontró el producto para ser eliminado");
        }

        return rObj;
    }

    public ResponseObject descontarUnidadesStock(int idProducto, Producto producto, int unidades){
        rObj = new ResponseObject();
        producto.setStockProducto(producto.getStockProducto()-unidades);
        rObj = actualizar(idProducto, producto);
        if(rObj.getSuccessfully()){
            producto = (Producto) rObj.getObj();
            if(producto.getStockProducto() <= producto.getStockMinimoProducto()){
                System.out.println("Crea notificacion de abastecimineto");
            }
        }
        return rObj;
    }    

}
