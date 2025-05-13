package com.bancolombia.arka_javadevops.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancolombia.arka_javadevops.services.CarritoCompraProductoService;
import com.bancolombia.arka_javadevops.utils.ResponseObject;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/ecommerce/carritoComprasProducto")
@RequiredArgsConstructor
public class CarritoCompraProductoController {

    private final CarritoCompraProductoService carritoCompraProductoService;

    @GetMapping("/{idCarrito}")
    public ResponseEntity<ResponseObject> obtenerProductosCarrito(@PathVariable int idCarrito) {
        return new ResponseEntity<>(carritoCompraProductoService.obtenerProductosCarrito(idCarrito), HttpStatus.OK);
    }
    

    @PostMapping("/agregarProducto/{idUsuario}/{idProducto}/{unidades}")
    public ResponseEntity<ResponseObject> agregarProductoCarrito(
        @PathVariable(required = true) int idUsuario
        , @PathVariable(required = true) int idProducto
        , @PathVariable(required = true) int unidades       
    ) { 
        return new ResponseEntity<>(
            carritoCompraProductoService.agregarProductoCarrito(
                idUsuario, idProducto, unidades), HttpStatus.OK);
    }
    

}
