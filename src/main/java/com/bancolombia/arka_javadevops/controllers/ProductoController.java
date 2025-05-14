package com.bancolombia.arka_javadevops.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bancolombia.arka_javadevops.DTO.ProductoDTO;
import com.bancolombia.arka_javadevops.models.Producto;
import com.bancolombia.arka_javadevops.services.ProductoService;

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

    @GetMapping("")
    public ResponseEntity<List<Producto>> obtenerProductos(){
        List<Producto> productos = productoService.obtenerProductos();
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    @GetMapping("/{idProducto}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable int idProducto){
        Producto producto = productoService.obtenerProductoPorId(idProducto);
        if(producto == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(producto, HttpStatus.OK);
    }

    /// Microservicio Soporte para Productos [Actividad Requerida]

    //Crea una ruta para buscar y devolver una lista de productos cuyo nombre o descripción contengan un término específico.
    @GetMapping("/productosNombreDescripcion/{texto}")
    public ResponseEntity<List<Producto>> productosNombreDescripcion(@PathVariable String texto){
        return new ResponseEntity<>(productoService.productosNombreDescripcion(texto), HttpStatus.OK);
    }    

    //Define una ruta que te devuelva la lista de todos los productos ordenados alfabéticamente
    @GetMapping("/productosOrdenadosAsc")
    public ResponseEntity<List<Producto>> productosOrdenadosAsc(){
        return new ResponseEntity<>(productoService.productosOrdenadosAsc(), HttpStatus.OK);
    }    

    //Crea una ruta para buscar y devolver una lista de productos cuyos precios se encuentre en un rango dado por la petición
    @GetMapping("/productosPorRangoPrecio")
    public ResponseEntity<List<Producto>> productosPorRangoPrecio(@RequestParam int precioMinimo, @RequestParam int precioMaximo){
        return new ResponseEntity<>(productoService.productosPorRangoPrecio(precioMinimo, precioMaximo), HttpStatus.OK);
    }    

    /// Microservicio Soporte para Productos [Actividad Requerida]

    /// Integración de Relaciones en Proyecto Arka [Actividad Requerida]

    //Búsqueda de productos por categoría
    @GetMapping("/productosPorCategoria/{idCategoria}")
    public ResponseEntity<List<Producto>> productosPorCategoria(@PathVariable int idCategoria){
        List<Producto> productos = productoService.productosPorCategoria(idCategoria);
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    /// Integración de Relaciones en Proyecto Arka [Actividad Requerida]

    @PostMapping("/crearNuevo")
    public ResponseEntity<Producto> crearNuevo(@Valid @RequestBody Producto producto) {
        Producto nuevoProducto = productoService.crearNuevo(producto);
        if(nuevoProducto == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
    }

    ////// Ejemplo con DTO y Mapper
    @PostMapping("")
    public ResponseEntity<ProductoDTO> crearNuevoDto(@Valid @RequestBody Producto producto) {
        ProductoDTO nuevoProducto = productoService.crearNuevoDto(producto);
        if(nuevoProducto == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
    }
    
    @PutMapping("/actualizar/{idProducto}")
    public ResponseEntity<Producto> actualizar(@PathVariable int idProducto, @Valid @RequestBody Producto producto) {
        Producto productoActualizar = productoService.actualizar(idProducto, producto);
        if(productoActualizar == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } 
        return new ResponseEntity<>(productoActualizar, HttpStatus.OK);
            
    }

    @DeleteMapping("/eliminar/{idProducto}")
    public ResponseEntity<Void> eliminar(@PathVariable int idProducto){
        if(productoService.eliminar(idProducto)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
}
