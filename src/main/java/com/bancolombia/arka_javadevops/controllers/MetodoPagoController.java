package com.bancolombia.arka_javadevops.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancolombia.arka_javadevops.models.MetodoPago;
import com.bancolombia.arka_javadevops.services.MetodoPagoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/usuarios/metodosPago")
@RequiredArgsConstructor
public class MetodoPagoController {

    private final MetodoPagoService metodoPagoService;

    @GetMapping("")
    public ResponseEntity<List<MetodoPago>> obtenerMetodosPago() {
        return new ResponseEntity<>(metodoPagoService.obtenerMetodosPago(), HttpStatus.OK);
    }

    @PostMapping("/crearNuevo")
    public ResponseEntity<MetodoPago> postMethodName(@Valid @RequestBody MetodoPago metodoPago) {
        MetodoPago nuevoMeotMetodoPago = metodoPagoService.crearNuevo(metodoPago);
        if(nuevoMeotMetodoPago == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(nuevoMeotMetodoPago, HttpStatus.CREATED);
    }
    
    

}
