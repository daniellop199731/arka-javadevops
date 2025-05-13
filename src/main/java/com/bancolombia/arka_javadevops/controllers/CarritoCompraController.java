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
    

}
