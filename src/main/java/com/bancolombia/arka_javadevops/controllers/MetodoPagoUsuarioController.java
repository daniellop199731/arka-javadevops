package com.bancolombia.arka_javadevops.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancolombia.arka_javadevops.models.MetodoPagoUsuario;
import com.bancolombia.arka_javadevops.services.MetodoPagoUsuarioService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/usuarios/metodosPagosUsuario")
@RequiredArgsConstructor
public class MetodoPagoUsuarioController {

    private final MetodoPagoUsuarioService metodoPagoUsuarioService;

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<MetodoPagoUsuario>> obtenerMetodosPagosUsuario(@PathVariable int idUsuario) {
        return new ResponseEntity<>(metodoPagoUsuarioService.obtenerMetodosPagosUsuario(idUsuario), HttpStatus.OK);
    }
    
    @PostMapping("/agregarMetodoPago/{idUsaurio}/{idMetodo}/{valorCuenta}")
    public ResponseEntity<Void> agregarMetodoPagoUsuario(@PathVariable(required = true) int idUsaurio
            , @PathVariable(required = true) int idMetodo
            , @PathVariable(required = true) double valorCuenta) {        
        if(metodoPagoUsuarioService.agregarMetodoPagoUsuario(idUsaurio, idMetodo, valorCuenta)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
    
    }
    



}
