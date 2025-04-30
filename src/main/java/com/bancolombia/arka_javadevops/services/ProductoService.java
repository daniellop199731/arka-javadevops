package com.bancolombia.arka_javadevops.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bancolombia.arka_javadevops.models.Producto;
import com.bancolombia.arka_javadevops.repositories.ProductoRepository;
import com.bancolombia.arka_javadevops.utils.ResponseObject;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    private static ResponseObject rObj;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

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
            rObj.setObj(producto);
        } else {
            rObj.setMsj("No se encontró el producto");
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

    public ResponseObject crearNuevo(Producto producto){
        rObj = new ResponseObject();
        boolean continueToSave = true;
        if(this.existeProductoPorReferencia(producto.getReferenciaProducto())){
            rObj.setMsj("Ya existe un producto con la referencia ".concat(producto.getReferenciaProducto()));
            continueToSave = false;
        }

        if(continueToSave){
            rObj.setObj(productoRepository.save(producto));
            rObj.setMsj("Producto guardado con exito");
            rObj.setAsSuccessfully();
        }

        return rObj;
    }

    public ResponseObject actualizar(Producto producto){
        rObj = new ResponseObject();
        boolean continueToSave = true;
        if(this.existeProductoPorReferenciaParaActualizar(producto)){
            rObj.setMsj("Ya existe un producto con la referencia ".concat(producto.getReferenciaProducto()));
            continueToSave = false;
        }

        if(continueToSave){
            Optional<Producto> productoEncontrado = productoRepository.findById(producto.getIdProducto());
            if(productoEncontrado.isPresent()){
                rObj.setObj(productoRepository.save(producto));
                rObj.setMsj("Producto actualizado con exito");
                rObj.setAsSuccessfully();                              
            } else {
                rObj.setMsj("El producto a actualizar no existe");
            }
            productoEncontrado = null;
        }        

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
