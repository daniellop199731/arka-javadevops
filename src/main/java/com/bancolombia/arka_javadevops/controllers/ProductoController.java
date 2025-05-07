package com.bancolombia.arka_javadevops.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/inventario/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/")
    public ResponseEntity<ResponseObject> obtenerProductos(){
        try{
            return new ResponseEntity<>(productoService.obtenerProductos(), HttpStatus.OK);
        } catch(Exception ex){
            return new ResponseEntity<>(new ResponseObject(false, "Error: ".concat(ex.getMessage()), ex)
                , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{idProducto}")
    public ResponseEntity<ResponseObject> obtenerProductoPorId(@PathVariable int idProducto){
        try{
            return new ResponseEntity<>(productoService.obtenerProductoPorId(idProducto), HttpStatus.OK);
        } catch(Exception ex){
            return new ResponseEntity<>(new ResponseObject(false, "Error: ".concat(ex.getMessage()), ex)
                , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Crea una ruta para buscar y devolver una lista de productos cuyo nombre o descripción contengan un término específico.
    @GetMapping("/productosNombreDescripcion/{texto}")
    public ResponseEntity<ResponseObject> productosNombreDescripcion(@PathVariable String texto){
        try{
            return new ResponseEntity<>(productoService.productosNombreDescripcion(texto), HttpStatus.OK);
        } catch(Exception ex){
            return new ResponseEntity<>(new ResponseObject(false, "Error: ".concat(ex.getMessage()), ex)
                , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }    

    //Define una ruta que te devuelva la lista de todos los productos ordenados alfabéticamente
    @GetMapping("/productosOrdenadosAsc")
    public ResponseEntity<ResponseObject> productosOrdenadosAsc(){
        try{
            return new ResponseEntity<>(productoService.productosOrdenadosAsc(), HttpStatus.OK);
        } catch(Exception ex){
            return new ResponseEntity<>(new ResponseObject(false, "Error: ".concat(ex.getMessage()), ex)
                , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }    

    //Crea una ruta para buscar y devolver una lista de productos cuyos precios se encuentre en un rango dado por la petición
    @GetMapping("/productosPorRangoPrecio")
    public ResponseEntity<ResponseObject> productosPorRangoPrecio(@RequestParam int precioMinimo, @RequestParam int precioMaximo){
        try{
            return new ResponseEntity<>(productoService.productosPorRangoPrecio(precioMinimo, precioMaximo), HttpStatus.OK);
        } catch(Exception ex){
            return new ResponseEntity<>(new ResponseObject(false, "Error: ".concat(ex.getMessage()), ex)
                , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }    

    @PostMapping("/crearNuevo")
    public ResponseEntity<ResponseObject> crearNuevo(@RequestBody Producto producto) {
        try{
            return new ResponseEntity<>(productoService.crearNuevo(producto), HttpStatus.OK);
        } catch(DataIntegrityViolationException ex){
            return new ResponseEntity<>(new ResponseObject(false, "Error en los campos enviados", ex)
                , HttpStatus.BAD_REQUEST);
        } catch(Exception ex){
            return new ResponseEntity<>(new ResponseObject(false, "Error: ".concat(ex.getMessage()), ex)
                , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<ResponseObject> actualizar(@RequestBody Producto producto) {
        try{
            return new ResponseEntity<>(productoService.actualizar(producto), HttpStatus.OK);
        } catch(DataIntegrityViolationException ex){
            return new ResponseEntity<>(new ResponseObject(false, "Error en los campos enviados", ex)
                , HttpStatus.BAD_REQUEST);
        } catch(Exception ex){
            return new ResponseEntity<>(new ResponseObject(false, "Error: ".concat(ex.getMessage()), ex)
                , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/eliminar/{idProducto}")
    public ResponseEntity<ResponseObject> eliminar(@PathVariable int idProducto){
        try{
            return new ResponseEntity<>(productoService.eliminar(idProducto), HttpStatus.OK);
        } catch(Exception ex){
            return new ResponseEntity<>(new ResponseObject(false, "Error: ".concat(ex.getMessage()), ex)
                , HttpStatus.INTERNAL_SERVER_ERROR);
        }        
    }
    
}
