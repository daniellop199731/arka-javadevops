package com.bancolombia.arka_javadevops.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancolombia.arka_javadevops.DTO.CarritoCompraDTO;
import com.bancolombia.arka_javadevops.models.CarritoCompra;
import com.bancolombia.arka_javadevops.services.CarritoCompraService;
import com.bancolombia.arka_javadevops.utils.ResponseObject;

import lombok.RequiredArgsConstructor;

import java.util.List;

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
    public ResponseEntity<CarritoCompra> obtenerCarritoPorId(@PathVariable int idCarritoCompra) {
        CarritoCompra carritoCompra = carritoCompraService.obtenerCarritoPorId(idCarritoCompra);
        if(carritoCompra == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(carritoCompra, HttpStatus.OK);
    }

    @GetMapping("/carritoActual/{idUsuario}")
    public ResponseEntity<CarritoCompra> carritoActual(@PathVariable int idUsuario) {
        CarritoCompra carritoCompra = carritoCompraService.carritoActual(idUsuario);
        if(carritoCompra == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(carritoCompra, HttpStatus.OK);
    }

    /// Integración de Relaciones en Proyecto Arka [Actividad Requerida]
    
    //Obtener carritos abandonados
    @GetMapping("/abandonados")
    public ResponseEntity<List<CarritoCompraDTO>> carritosAbandonados() {
        List<CarritoCompraDTO> carritoCompraDTOs = carritoCompraService.carritosAbandonados();
        return new ResponseEntity<>(carritoCompraDTOs, HttpStatus.OK);
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
    public ResponseEntity<List<CarritoCompraDTO>> carritosUsuario(@PathVariable int idUsuario) {
        List<CarritoCompraDTO> carritoCompraDTOs = carritoCompraService.carritosPorUsuario(idUsuario);
        if(carritoCompraDTOs == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(carritoCompraDTOs, HttpStatus.OK);
        
    }

    /// Integración de Relaciones en Proyecto Arka [Actividad Requerida]
    

}
