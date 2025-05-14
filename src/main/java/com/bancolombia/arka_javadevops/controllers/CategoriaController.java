package com.bancolombia.arka_javadevops.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancolombia.arka_javadevops.models.Categoria;
import com.bancolombia.arka_javadevops.services.CategoriaService;

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
    public ResponseEntity<List<Categoria>> obtenerCategorias() {
        List<Categoria> categorias = categoriaService.obtenerCategorias();
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }

    @GetMapping("/busquedaPorNombre/{nombreCategoria}")
    public ResponseEntity<List<Categoria>> obtenerCategoriasPorNombre(@PathVariable String nombreCategoria) {
            return new ResponseEntity<>(categoriaService.obtenerCategoriasPorNombre(nombreCategoria), HttpStatus.OK);
    }

    @PostMapping("/crearNueva")
    public ResponseEntity<Categoria> crearNueva(@Valid @RequestBody Categoria categoria) {
        Categoria nuevaCategoria = categoriaService.crearNueva(categoria);
        if(nuevaCategoria == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(nuevaCategoria, HttpStatus.CREATED);
    }
    
    @PutMapping("/actualizar/{idCategoria}")
    public ResponseEntity<Categoria> actualizar(@PathVariable int idCategoria, @Valid @RequestBody Categoria categoria) {
        Categoria categoriaActualizar = categoriaService.actualizarCategoria(idCategoria, categoria);
        if(categoriaActualizar == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categoriaActualizar, HttpStatus.OK);
    }
    
}
