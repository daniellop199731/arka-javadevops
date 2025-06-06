package com.bancolombia.arka_javadevops.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bancolombia.arka_javadevops.models.ExpirableProducto;
import com.bancolombia.arka_javadevops.models.Producto;
import com.bancolombia.arka_javadevops.services.ProductoServicePp;
import com.bancolombia.arka_javadevops.services.interfaces.ProductoServiceCreate;
import com.bancolombia.arka_javadevops.services.interfaces.ProductoServiceMod;
import com.bancolombia.arka_javadevops.services.interfaces.ProductoServiceRead;
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

    //Paso 3: Se llama a la interfaz, en el paso 2 se agrega la anotacion 
    //@Primary para que spring implemente la clase con dicha anotacion
    private final ProductoServiceRead productoServiceRead;
    private final ProductoServiceCreate productoServiceCreate;
    private final ProductoServiceMod productoServiceMod;

    private static ResponseObject rObj;

    @GetMapping("")
    public ResponseEntity<ResponseObject> obtenerProductos(){
        return new ResponseEntity<>(productoServiceRead.obtenerProductos(), HttpStatus.OK);
    }

    @GetMapping("/{idProducto}")
    public ResponseEntity<ResponseObject> obtenerProductoPorId(@PathVariable int idProducto){
        return new ResponseEntity<>(productoServiceRead.obtenerProductoPorId(idProducto), HttpStatus.OK);
    }

    /// Microservicio Soporte para Productos [Actividad Requerida]

    //Crea una ruta para buscar y devolver una lista de productos cuyo nombre o descripción contengan un término específico.
    @GetMapping("/productosNombreDescripcion/{texto}")
    public ResponseEntity<ResponseObject> productosNombreDescripcion(@PathVariable String texto){
        return new ResponseEntity<>(productoServiceRead.productosNombreDescripcion(texto), HttpStatus.OK);
    }    

    //Define una ruta que te devuelva la lista de todos los productos ordenados alfabéticamente
    @GetMapping("/productosOrdenadosAsc")
    public ResponseEntity<ResponseObject> productosOrdenadosAsc(){
        return new ResponseEntity<>(productoServiceRead.productosOrdenadosAsc(), HttpStatus.OK);
    }    

    //Crea una ruta para buscar y devolver una lista de productos cuyos precios se encuentre en un rango dado por la petición
    @GetMapping("/productosPorRangoPrecio")
    public ResponseEntity<ResponseObject> productosPorRangoPrecio(@RequestParam int precioMinimo, @RequestParam int precioMaximo){
        return new ResponseEntity<>(productoServiceRead.productosPorRangoPrecio(precioMinimo, precioMaximo), HttpStatus.OK);
    }   
    
    //Búsqueda de productos por categoría
    @GetMapping("/productosPorCategoria/{idCategoria}")
    public ResponseEntity<ResponseObject> productosPorCategoria(@PathVariable int idCategoria){
        return new ResponseEntity<>(productoServiceRead.productosPorCategoria(idCategoria), HttpStatus.OK);
    }    

    /// Microservicio Soporte para Productos [Actividad Requerida]

    /// Integración de Relaciones en Proyecto Arka [Actividad Requerida]

    /// Integración de Relaciones en Proyecto Arka [Actividad Requerida]

    @PostMapping("/crearNuevo")
    public ResponseEntity<ResponseObject> crearNuevo(@Valid @RequestBody Producto producto) {
        return new ResponseEntity<>(productoServiceCreate.crearNuevo(producto), HttpStatus.CREATED);
    }

    //PASO 6: Se crea un nuevo endPoint o se modifica el existente 
    //para recibir como parametro la clase extendida
    @PostMapping("/crearNuevoV2")
    public ResponseEntity<ResponseObject> crearNuevoV2(@Valid @RequestBody ExpirableProducto producto) {
        ResponseObject response = productoServiceCreate.crearNuevoV2(producto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }    

    ////// Ejemplo con DTO y Mapper
    @PostMapping("")
    public ResponseEntity<ResponseObject> crearNuevoDto(@Valid @RequestBody Producto producto) {
        return new ResponseEntity<>(productoServiceCreate.crearNuevoDto(producto), HttpStatus.CREATED);
    }
    

    @PutMapping("/actualizar/{idProducto}")
    public ResponseEntity<ResponseObject> actualizar(@PathVariable int idProducto, @Valid @RequestBody Producto producto) {
        rObj = productoServiceMod.actualizar(idProducto, producto);
        if(rObj.getObj() == null){
            return new ResponseEntity<>(rObj, HttpStatus.NOT_FOUND);
        } 
        return new ResponseEntity<>(rObj, HttpStatus.OK);
            
    }

    @DeleteMapping("/eliminar/{idProducto}")
    public ResponseEntity<ResponseObject> eliminar(@PathVariable int idProducto){
        rObj = productoServiceRead.obtenerProductoPorId(idProducto);
        if(rObj.getObj() != null){
            return new ResponseEntity<>(productoServiceMod.eliminar(idProducto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(rObj, HttpStatus.OK);
        }

                  
    }
    
}
