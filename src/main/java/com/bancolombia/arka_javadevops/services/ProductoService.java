package com.bancolombia.arka_javadevops.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bancolombia.arka_javadevops.DTO.ProductoDTO;
import com.bancolombia.arka_javadevops.mappers.ProductoMapper;
import com.bancolombia.arka_javadevops.models.Categoria;
import com.bancolombia.arka_javadevops.models.Producto;
import com.bancolombia.arka_javadevops.repositories.ProductoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;

    private final ProductoMapper productoMapper;

    public List<Producto> obtenerProductos(){
        return (List<Producto>) productoRepository.findAll();
    }

    public Producto obtenerProductoPorId(int idProducto){
        Optional<Producto> producto = productoRepository.findById(idProducto);
        if(producto.isPresent()){
            return producto.get();
        }
        return null;
    }

    public List<Producto> productosNombreDescripcion(String texto){
        return productoRepository.productosNombreDescripcion(texto);
    }

    public List<Producto> productosOrdenadosAsc(){
        return (List<Producto>) productoRepository.productosOrdenadosAsc();
    }    

    public List<Producto> productosPorRangoPrecio(int precioMinimo, int precioMaximo){
        return (List<Producto>) productoRepository.productosPorRangoPrecio(precioMinimo, precioMaximo);
    }     

    public Producto crearNuevo(Producto producto){
        if(this.existeProductoPorReferencia(producto.getReferenciaProducto())){
            return null;
        }
        return productoRepository.save(producto);
    }

    public ProductoDTO crearNuevoDto(Producto producto){
        if(this.existeProductoPorReferencia(producto.getReferenciaProducto())){
            return null;
        }
        return productoMapper.toDto(productoRepository.save(producto));
    }

    public Producto actualizar(int idProducto, Producto producto){
        Optional<Producto> productoEncontrado = productoRepository.findById(idProducto);
        if(productoEncontrado.isPresent()){
            producto.setIdProducto(idProducto);
            if(this.existeProductoPorReferenciaParaActualizar(producto)){
                return null;
            }
            return productoRepository.save(producto);                             
        }      
        return null;
    }

    public boolean eliminar(int idProducto){
        Optional<Producto> producto = productoRepository.findById(idProducto);
        if(producto.isPresent()){
            productoRepository.deleteById(idProducto);
            return true;
        }
        return false;
    }

    public boolean descontarUnidadesStock(int idProducto, Producto producto, int unidades){
        producto.setStockProducto(producto.getStockProducto()-unidades);
        producto = actualizar(idProducto, producto);
        if(producto != null){
            if(producto.getStockProducto() <= producto.getStockMinimoProducto()){
                System.out.println("Crea notificacion de abastecimineto");
            }
            return true;
        }
        return false;
    }

    public List<Producto> productosPorCategoria(int idCategoria){
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(idCategoria);
        return productoRepository.findByCategoria(categoria);

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
