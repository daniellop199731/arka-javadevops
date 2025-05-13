package com.bancolombia.arka_javadevops.DTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarritoCompraDTO {

    private int idCarritoCompra;
    private String identificacionUsuario;
    private String nombreEstadoDespacho;
    private String nombreMetodoPago;
    private Date fechaCreacionCarritoCompra;

}
