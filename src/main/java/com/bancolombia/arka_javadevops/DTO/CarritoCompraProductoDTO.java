package com.bancolombia.arka_javadevops.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarritoCompraProductoDTO {

    private int idCarritoCompra;
    private int idProducto;
    private String nombreProducto;
    private double precioProducto;
    private int unidades;

}
