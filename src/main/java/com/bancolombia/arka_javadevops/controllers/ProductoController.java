package com.bancolombia.arka_javadevops.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bancolombia.arka_javadevops.models.Producto;
import com.bancolombia.arka_javadevops.services.ProductoService;
import com.bancolombia.arka_javadevops.utils.ResponseObject;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/inventario/productos")
@RequiredArgsConstructor

public class ProductoController {

    private final ProductoService productoService;

    private static ResponseObject rObj;

    @GetMapping("")
    public ResponseEntity<ResponseObject> obtenerProductos(){
        return new ResponseEntity<>(productoService.obtenerProductos(), HttpStatus.OK);
    }

    @GetMapping("/{idProducto}")
    public ResponseEntity<ResponseObject> obtenerProductoPorId(@PathVariable int idProducto){
        return new ResponseEntity<>(productoService.obtenerProductoPorId(idProducto), HttpStatus.OK);
    }

    /// Microservicio Soporte para Productos [Actividad Requerida]

    //Crea una ruta para buscar y devolver una lista de productos cuyo nombre o descripción contengan un término específico.
    @GetMapping("/productosNombreDescripcion/{texto}")
    public ResponseEntity<ResponseObject> productosNombreDescripcion(@PathVariable String texto){
        return new ResponseEntity<>(productoService.productosNombreDescripcion(texto), HttpStatus.OK);
    }    

    //Define una ruta que te devuelva la lista de todos los productos ordenados alfabéticamente
    @GetMapping("/productosOrdenadosAsc")
    public ResponseEntity<ResponseObject> productosOrdenadosAsc(){
        return new ResponseEntity<>(productoService.productosOrdenadosAsc(), HttpStatus.OK);
    }    

    //Crea una ruta para buscar y devolver una lista de productos cuyos precios se encuentre en un rango dado por la petición
    @GetMapping("/productosPorRangoPrecio")
    public ResponseEntity<ResponseObject> productosPorRangoPrecio(@RequestParam int precioMinimo, @RequestParam int precioMaximo){
        return new ResponseEntity<>(productoService.productosPorRangoPrecio(precioMinimo, precioMaximo), HttpStatus.OK);
    }    

    /// Microservicio Soporte para Productos [Actividad Requerida]

    /// Integración de Relaciones en Proyecto Arka [Actividad Requerida]

    //Búsqueda de productos por categoría
    @GetMapping("/productosPorCategoria/{idCategoria}")
    public ResponseEntity<ResponseObject> productosPorCategoria(@PathVariable int idCategoria){
        return new ResponseEntity<>(productoService.productosPorCategoria(idCategoria), HttpStatus.OK);
    }

    /// Integración de Relaciones en Proyecto Arka [Actividad Requerida]

    @PostMapping("/crearNuevo")
    public ResponseEntity<ResponseObject> crearNuevo(@Valid @RequestBody Producto producto) {
        return new ResponseEntity<>(productoService.crearNuevo(producto), HttpStatus.CREATED);
    }

    ////// Ejemplo con DTO y Mapper
    @PostMapping("")
    public ResponseEntity<ResponseObject> crearNuevoDto(@Valid @RequestBody Producto producto) {
        return new ResponseEntity<>(productoService.crearNuevoDto(producto), HttpStatus.CREATED);
    }
    

    @PutMapping("/actualizar/{idProducto}")
    public ResponseEntity<ResponseObject> actualizar(@PathVariable int idProducto, @Valid @RequestBody Producto producto) {
        rObj = productoService.actualizar(idProducto, producto);
        if(rObj.getObj() == null){
            return new ResponseEntity<>(rObj, HttpStatus.NOT_FOUND);
        } 
        return new ResponseEntity<>(rObj, HttpStatus.OK);
            
    }

    @DeleteMapping("/eliminar/{idProducto}")
    public ResponseEntity<ResponseObject> eliminar(@PathVariable int idProducto){
        rObj = productoService.obtenerProductoPorId(idProducto);
        if(rObj.getObj() != null){
            return new ResponseEntity<>(productoService.eliminar(idProducto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(rObj, HttpStatus.OK);
        }

                  
    }
    
}
