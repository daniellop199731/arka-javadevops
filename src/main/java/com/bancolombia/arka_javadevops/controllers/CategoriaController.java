package com.bancolombia.arka_javadevops.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancolombia.arka_javadevops.models.Categoria;
import com.bancolombia.arka_javadevops.services.CategoriaService;
import com.bancolombia.arka_javadevops.utils.ResponseObject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/inventario/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("")
    public ResponseEntity<ResponseObject> obtenerCategorias() {
        try {
            return new ResponseEntity<>(categoriaService.obtenerCategorias(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new ResponseObject("Error: ".concat(ex.getMessage()), ex)
                , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/busquedaPorNombre/{nombreCategoria}")
    public ResponseEntity<ResponseObject> obtenerCategoriasPorNombre(@PathVariable String nombreCategoria) {
        try{
            return new ResponseEntity<>(categoriaService.obtenerCategoriasPorNombre(nombreCategoria), HttpStatus.OK);
        }catch (Exception ex) {
            return new ResponseEntity<>(new ResponseObject("Error: ".concat(ex.getMessage()), ex)
                , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/crearNueva")
    public ResponseEntity<ResponseObject> crearNueva(@RequestBody Categoria categoria) {
        try{
            return new ResponseEntity<>(categoriaService.crearNueva(categoria), HttpStatus.CREATED);
        } catch(DataIntegrityViolationException ex){
            return new ResponseEntity<>(new ResponseObject("Error en los campos enviados", ex)
                , HttpStatus.BAD_REQUEST);
        } catch(Exception ex){
            return new ResponseEntity<>(new ResponseObject("Error: ".concat(ex.getMessage()), ex)
                , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/actualizar")
    public ResponseEntity<ResponseObject> actualizar(@RequestBody Categoria categoria) {
        try{
            return new ResponseEntity<>(categoriaService.actualizarCategoria(categoria), HttpStatus.OK);
        } catch(DataIntegrityViolationException ex){
            return new ResponseEntity<>(new ResponseObject("Error en los campos enviados", ex)
                , HttpStatus.BAD_REQUEST);
        } catch(Exception ex){
            return new ResponseEntity<>(new ResponseObject("Error: ".concat(ex.getMessage()), ex)
                , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
