package com.bancolombia.arka_javadevops.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancolombia.arka_javadevops.models.Proveedor;
import com.bancolombia.arka_javadevops.services.ProveedorService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/inventario/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    public ProveedorController() {
    }

    @GetMapping("")
    public ResponseEntity<List<Proveedor>> obtenerProveedores() {        
        return new ResponseEntity<>(proveedorService.obtenerProveedores(), HttpStatus.OK);
    }

    @GetMapping("/{identificacionProveedor}")
    public ResponseEntity<Proveedor> obtenerProveedorPorIdentificacion(@PathVariable String identificacionProveedor) {
        Proveedor proveedor = proveedorService.obtenerProveedorPorIdentificacion(identificacionProveedor);
        if(proveedor == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(proveedor, HttpStatus.OK);       
    }

    @PostMapping("/crearNuevo")
    public ResponseEntity<Proveedor> crearNuevo(@Valid @RequestBody Proveedor proveedor) {
        Proveedor nuevoProveedor = proveedorService.crearNuevo(proveedor);
        if(nuevoProveedor == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(nuevoProveedor, HttpStatus.CREATED);
    }
    
    @PutMapping("/actualizar/{idProveedor}")
    public ResponseEntity<Proveedor> actualizar(@PathVariable int idProveedor, @Valid @RequestBody Proveedor proveedor) {
        return new ResponseEntity<>(proveedorService.actualizar(idProveedor, proveedor), HttpStatus.OK);
    }
}
