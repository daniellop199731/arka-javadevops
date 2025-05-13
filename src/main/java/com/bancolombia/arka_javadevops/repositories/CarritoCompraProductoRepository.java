package com.bancolombia.arka_javadevops.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bancolombia.arka_javadevops.models.CarritoCompra;
import com.bancolombia.arka_javadevops.models.CarritoCompraProducto;

public interface CarritoCompraProductoRepository extends CrudRepository<CarritoCompraProducto, Integer> {

    List<CarritoCompraProducto> findByCarritoCompra(CarritoCompra carritoCompra);

}
