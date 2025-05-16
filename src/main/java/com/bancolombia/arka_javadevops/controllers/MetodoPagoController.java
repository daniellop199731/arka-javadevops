package com.bancolombia.arka_javadevops.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancolombia.arka_javadevops.models.MetodoPago;
import com.bancolombia.arka_javadevops.services.MetodoPagoService;
import com.bancolombia.arka_javadevops.utils.ResponseGenericObject;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/usuarios/metodosPago")
@RequiredArgsConstructor
public class MetodoPagoController {

    private final MetodoPagoService metodoPagoService;

    @GetMapping("")
    public ResponseEntity<ResponseGenericObject<List<MetodoPago>>> 
        obtenerMetodosPago() {
            ResponseGenericObject<List<MetodoPago>> response = metodoPagoService.obtenerMetodosPago();
            if(response.getObj().isEmpty()){
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{idMetodoPago}")
    public ResponseEntity<ResponseGenericObject<MetodoPago>> 
        obtenerMetodoPagoPorId(@PathVariable int idMetodoPago) {
            ResponseGenericObject<MetodoPago> response = metodoPagoService.obtenerMetodoPagoPorId(idMetodoPago);
            if(response.getObj() == null){
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);         
    }

    @PostMapping("/crearNuevo")
    public ResponseEntity<ResponseGenericObject<MetodoPago>> crearNuevo(@Valid @RequestBody MetodoPago metodoPago) {
        ResponseGenericObject<MetodoPago> response = metodoPagoService.crearNuevo(metodoPago);
        if(response.getObj() == null){
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    

}
