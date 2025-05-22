package com.bancolombia.arka_javadevops.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancolombia.arka_javadevops.models.CarritoCompraProducto;
import com.bancolombia.arka_javadevops.services.CarritoCompraProductoService;
import com.bancolombia.arka_javadevops.utils.ResponseObject;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    /// Integración de Relaciones en Proyecto Arka [Actividad Requerida]
    /// 
    //Búsqueda de Pedidos por producto
    @GetMapping("/producto/{idProducto}")
    public ResponseEntity<ResponseObject> carritosPorProducto(@PathVariable int idProducto) {
        return new ResponseEntity<>(carritoCompraProductoService.carritosPorProducto(idProducto), HttpStatus.OK);
    }

    /// Integración de Relaciones en Proyecto Arka [Actividad Requerida]
    

    @PostMapping("/agregarProducto/{idUsuario}")
    public ResponseEntity<ResponseObject> agregarProductoCarrito(
        @PathVariable(required = true) int idUsuario
        , @RequestBody List<CarritoCompraProducto> carritoCompraProductos    
    ) { 
        return new ResponseEntity<>(
            carritoCompraProductoService.agregarProductoCarrito(
                idUsuario, carritoCompraProductos), HttpStatus.OK);
    }
    

}
