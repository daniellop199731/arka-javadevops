package com.bancolombia.arka_javadevops.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancolombia.arka_javadevops.models.Proveedor;
import com.bancolombia.arka_javadevops.services.ProveedorService;
import com.bancolombia.arka_javadevops.utils.ResponseObject;

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
    public ResponseEntity<ResponseObject> obtenerProveedores() {
        try{
            return new ResponseEntity<>(proveedorService.obtenerProveedores(), HttpStatus.OK);
        } catch(Exception ex){
            return new ResponseEntity<>(new ResponseObject(false, "Error: ".concat(ex.getMessage()), ex)
                , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{identificacionProveedor}")
    public ResponseEntity<ResponseObject> obtenerProveedorPorIdentificacion(@PathVariable String identificacionProveedor) {
        try{
            return new ResponseEntity<>(proveedorService.obtenerProveedorPorIdentificacion(identificacionProveedor), HttpStatus.OK);
        } catch(Exception ex){
            return new ResponseEntity<>(new ResponseObject(false, "Error: ".concat(ex.getMessage()), ex)
                , HttpStatus.INTERNAL_SERVER_ERROR);
        }        
    }

    @PostMapping("/crearNuevo")
    public ResponseEntity<ResponseObject> crearNuevo(@RequestBody Proveedor proveedor) {
        try{
            return new ResponseEntity<>(proveedorService.crearNuevo(proveedor), HttpStatus.OK);
        } catch(DataIntegrityViolationException ex){
            return new ResponseEntity<>(new ResponseObject(false, "Error en los campos enviados", ex)
                , HttpStatus.BAD_REQUEST);
        } catch(Exception ex){
            return new ResponseEntity<>(new ResponseObject(false, "Error: ".concat(ex.getMessage()), ex)
                , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/actualizar")
    public ResponseEntity<ResponseObject> actualizar(@RequestBody Proveedor proveedor) {
        try{
            return new ResponseEntity<>(proveedorService.actualizar(proveedor), HttpStatus.OK);
        } catch(DataIntegrityViolationException ex){
            return new ResponseEntity<>(new ResponseObject(false, "Error en los campos enviados", ex)
                , HttpStatus.BAD_REQUEST);
        } catch(Exception ex){
            return new ResponseEntity<>(new ResponseObject(false, "Error: ".concat(ex.getMessage()), ex)
                , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
