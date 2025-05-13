package com.bancolombia.arka_javadevops.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.bancolombia.arka_javadevops.DTO.CarritoCompraDTO;
import com.bancolombia.arka_javadevops.models.CarritoCompra;

@Component
public class CarritoCompraMapper {

    public CarritoCompraDTO toDto(CarritoCompra carritoCompra){
        if(carritoCompra == null){
            return null;
        }

        String identificacionUsuario = carritoCompra.getUsuarioCarritoCompra() != null ?
            carritoCompra.getUsuarioCarritoCompra().getIdentificacionUsuario() : null;
        String nombreEstadoDespacho = carritoCompra.getEstadoDespacho() != null ?
            carritoCompra.getEstadoDespacho().getNombreEstadoDespacho() : null;
        String nombreMetodoPago = carritoCompra.getMetodoPagoCarritoCompra() != null ?
            carritoCompra.getMetodoPagoCarritoCompra().getNombreMetodoPago() : null;

        return new CarritoCompraDTO(
            carritoCompra.getIdCarritoCompra()
            ,identificacionUsuario
            ,nombreEstadoDespacho
            ,nombreMetodoPago
            ,carritoCompra.getFechaCreacionCarritoCompra()
        );
    }

    public List<CarritoCompraDTO> toDto(List<CarritoCompra> carritoCompras){
        List<CarritoCompraDTO> carritosCompraDTO = new ArrayList<>();
        for (CarritoCompra carritoCompra : carritoCompras) {
            carritosCompraDTO.add(toDto(carritoCompra));
        }
        return carritosCompraDTO;
    }

}
