package com.bancolombia.arka_javadevops.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancolombia.arka_javadevops.services.CarritoCompraService;
import com.bancolombia.arka_javadevops.utils.ResponseObject;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/ecommerce/carritosCompra")
@RequiredArgsConstructor
public class CarritoCompraController {

    private final CarritoCompraService carritoCompraService;

    @GetMapping("/{idCarritoCompra}")
    public ResponseEntity<ResponseObject> obtenerCarritoPorId(@PathVariable int idCarritoCompra) {
        return new ResponseEntity<>(carritoCompraService.obtenerCarritoPorId(idCarritoCompra), HttpStatus.OK);
    }

    @GetMapping("/carritoActual/{idUsuario}")
    public ResponseEntity<ResponseObject> carritoActual(@PathVariable int idUsuario) {
        return new ResponseEntity<>(carritoCompraService.carritoActual(idUsuario), HttpStatus.OK);
    }

    /// Integración de Relaciones en Proyecto Arka [Actividad Requerida]
    
    //Obtener carritos abandonados
    @GetMapping("/abandonados")
    public ResponseEntity<ResponseObject> carritosAbandonados() {
        return new ResponseEntity<>(carritoCompraService.carritosAbandonados(), HttpStatus.OK);
    }

    // Búsqueda de Pedidos en un rango de fechas
    @GetMapping("")
    public ResponseEntity<ResponseObject> carritoComprasPorFechas(
        @RequestParam(required = true) String minDate
        , @RequestParam(required = true) String maxDate) {
        return new ResponseEntity<>(carritoCompraService.carritoComprasPorFechas(minDate, maxDate), HttpStatus.OK);
    }

    //Historial de Pedidos de un Cliente
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<ResponseObject> carritosUsuario(@PathVariable int idUsuario) {
        return new ResponseEntity<>(carritoCompraService.caarritosPorUsuario(idUsuario), HttpStatus.OK);
    }

    /// Integración de Relaciones en Proyecto Arka [Actividad Requerida]
    

}
