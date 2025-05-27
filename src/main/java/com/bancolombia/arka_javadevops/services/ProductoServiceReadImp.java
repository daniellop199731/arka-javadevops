package com.bancolombia.arka_javadevops.services;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.bancolombia.arka_javadevops.models.Categoria;
import com.bancolombia.arka_javadevops.models.Producto;
import com.bancolombia.arka_javadevops.repositories.ProductoRepository;
import com.bancolombia.arka_javadevops.services.interfaces.ProductoServiceRead;
import com.bancolombia.arka_javadevops.utils.ResponseObject;

import lombok.RequiredArgsConstructor;

//PASO 2: Se crea la implementacion de la interfac creada en el paso 1
//Siguiente paso: Ver ProductoController.java
@Service
@RequiredArgsConstructor
@Primary
public class ProductoServiceReadImp implements ProductoServiceRead {

    private final ProductoRepository productoRepository;

    private static ResponseObject rObj;

    public ResponseObject obtenerProductos(){
        rObj = new ResponseObject();
        List<Producto> productos = (List<Producto>) productoRepository.findAll();
        if(productos.isEmpty()){
            rObj.setMsj("En el momento no hay productos para mostrar");
        } else {
            rObj.setMsj("Consulta ejecutada con exito");
            rObj.setAsSuccessfully();
            rObj.setObj(productos);
        }
        return rObj;
    }

    public ResponseObject obtenerProductoPorId(int idProducto){
        rObj = new ResponseObject();
        Optional<Producto> producto = productoRepository.findById(idProducto);
        if(producto.isPresent()){
            rObj.setMsj("Producto encontrado");
            rObj.setAsSuccessfully();
            rObj.setObj(producto.get());
        } else {
            rObj.setMsj("No se encontró el producto con id "
                .concat(idProducto+""));
        }
        return rObj;
    }

    public ResponseObject productosNombreDescripcion(String texto){
        rObj = new ResponseObject();
        List<Producto> productos = productoRepository.productosNombreDescripcion(texto);
        if(productos.isEmpty()){
            rObj.setMsj("No se encontraron productos");
        } else {
            rObj.setMsj("Productos encontrados");
            rObj.setAsSuccessfully();
            rObj.setObj(productos);
        }
        return rObj;
    }

    public ResponseObject productosOrdenadosAsc(){
        rObj = new ResponseObject();
        List<Producto> productos = (List<Producto>) productoRepository.productosOrdenadosAsc();
        if(productos.isEmpty()){
            rObj.setMsj("En el momento no hay productos para mostrar");
        } else {
            rObj.setMsj("Consulta ejecutada con exito");
            rObj.setAsSuccessfully();
            rObj.setObj(productos);
        }
        return rObj;
    }    

    public ResponseObject productosPorRangoPrecio(int precioMinimo, int precioMaximo){
        rObj = new ResponseObject();
        List<Producto> productos = (List<Producto>) productoRepository.productosPorRangoPrecio(precioMinimo, precioMaximo);
        if(productos.isEmpty()){
            rObj.setMsj("En el momento no hay productos para mostrar");
        } else {
            rObj.setMsj("Consulta ejecutada con exito");
            rObj.setAsSuccessfully();
            rObj.setObj(productos);
        }
        return rObj;
    }    

    public ResponseObject productosPorCategoria(int idCategoria){
        rObj = new ResponseObject();
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(idCategoria);
        List<Producto> productos = productoRepository.findByCategoria(categoria);
        rObj.setAsSuccessfully();
        if(productos.isEmpty()){
            rObj.setMsj("No hay productos de esa categoria");
            rObj.setObj(productos);
        }
        rObj.setMsj("Consulta ejecutada con exito");
        rObj.setObj(productos);
        return rObj;

    }    

}
