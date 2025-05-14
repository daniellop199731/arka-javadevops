package com.bancolombia.arka_javadevops.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancolombia.arka_javadevops.models.CarritoCompraProducto;
import com.bancolombia.arka_javadevops.services.CarritoCompraProductoService;

import lombok.RequiredArgsConstructor;

import java.util.List;

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
    public ResponseEntity<List<CarritoCompraProducto>> obtenerProductosCarrito(@PathVariable int idCarrito) {
        List<CarritoCompraProducto> carritoCompraProductos = carritoCompraProductoService.obtenerProductosCarrito(idCarrito);
        return new ResponseEntity<>(carritoCompraProductos, HttpStatus.OK);
    }

    /// Integración de Relaciones en Proyecto Arka [Actividad Requerida]
    /// 
    //Búsqueda de Pedidos por producto
    @GetMapping("/producto/{idProducto}")
    public ResponseEntity<List<CarritoCompraProducto>> carritosPorProducto(@PathVariable int idProducto) {
        List<CarritoCompraProducto> carritoCompraProductos = carritoCompraProductoService.carritosPorProducto(idProducto);
        if(carritoCompraProductos == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(carritoCompraProductos, HttpStatus.OK);
    }

    /// Integración de Relaciones en Proyecto Arka [Actividad Requerida]
    

    @PostMapping("/agregarProducto/{idUsuario}/{idProducto}/{unidades}")
    public ResponseEntity<Object> agregarProductoCarrito(
        @PathVariable(required = true) int idUsuario
        , @PathVariable(required = true) int idProducto
        , @PathVariable(required = true) int unidades       
    ) { 
        Object obj = carritoCompraProductoService.agregarProductoCarrito(idUsuario, idProducto, unidades);
        if(obj == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(obj.getClass() == String.class){
            return new ResponseEntity<>(obj, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);        
    }
    

}
