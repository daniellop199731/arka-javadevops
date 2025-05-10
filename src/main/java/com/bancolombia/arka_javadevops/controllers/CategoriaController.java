package com.bancolombia.arka_javadevops.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancolombia.arka_javadevops.models.Categoria;
import com.bancolombia.arka_javadevops.services.CategoriaService;
import com.bancolombia.arka_javadevops.utils.ResponseObject;

import jakarta.validation.Valid;

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
        return new ResponseEntity<>(categoriaService.obtenerCategorias(), HttpStatus.OK);
    }

    @GetMapping("/busquedaPorNombre/{nombreCategoria}")
    public ResponseEntity<ResponseObject> obtenerCategoriasPorNombre(@PathVariable String nombreCategoria) {
            return new ResponseEntity<>(categoriaService.obtenerCategoriasPorNombre(nombreCategoria), HttpStatus.OK);
    }

    @PostMapping("/crearNueva")
    public ResponseEntity<ResponseObject> crearNueva(@Valid @RequestBody Categoria categoria) {
        return new ResponseEntity<>(categoriaService.crearNueva(categoria), HttpStatus.CREATED);
    }
    
    @PutMapping("/actualizar")
    public ResponseEntity<ResponseObject> actualizar(@Valid @RequestBody Categoria categoria) {
        return new ResponseEntity<>(categoriaService.actualizarCategoria(categoria), HttpStatus.OK);
    }
    
}
