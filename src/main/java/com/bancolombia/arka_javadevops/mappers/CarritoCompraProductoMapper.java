package com.bancolombia.arka_javadevops.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.bancolombia.arka_javadevops.DTO.CarritoCompraProductoDTO;
import com.bancolombia.arka_javadevops.models.CarritoCompraProducto;

@Component
public class CarritoCompraProductoMapper {

    public CarritoCompraProductoDTO toDTO(CarritoCompraProducto carritoCompraProducto){
        if(carritoCompraProducto == null){
            return null;
        }

        return new CarritoCompraProductoDTO(
            carritoCompraProducto.getCarritoCompra().getIdCarritoCompra()
            , carritoCompraProducto.getProductoCarritoCompra().getIdProducto()
            , carritoCompraProducto.getProductoCarritoCompra().getNombreProducto()
            , carritoCompraProducto.getProductoCarritoCompra().getPrecioProducto()
            , carritoCompraProducto.getUnidadesProducto());
    }

    public List<CarritoCompraProductoDTO> toDTO(List<CarritoCompraProducto> carritoCompraProductos){
        if(carritoCompraProductos == null){
            return null;
        }
        if(carritoCompraProductos.size() <= 0){
            return null;
        }
        List<CarritoCompraProductoDTO> carritoCompraProductosDTO = new ArrayList<>();
        for (CarritoCompraProducto carritoCompraProducto : carritoCompraProductos) {
            carritoCompraProductosDTO.add(this.toDTO(carritoCompraProducto));
        }
        return carritoCompraProductosDTO;
    }

}
