package com.bancolombia.arka_javadevops.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bancolombia.arka_javadevops.models.CarritoCompra;
import com.bancolombia.arka_javadevops.models.CarritoCompraProducto;
import com.bancolombia.arka_javadevops.models.Producto;

public interface CarritoCompraProductoRepository extends CrudRepository<CarritoCompraProducto, Integer> {

    List<CarritoCompraProducto> findByCarritoCompra(CarritoCompra carritoCompra);
    
    List<CarritoCompraProducto> findByProductoCarritoCompra(Producto productoCarritoCompra);

}
