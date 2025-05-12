package com.bancolombia.arka_javadevops.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancolombia.arka_javadevops.models.MetodoPagoUsuario;
import com.bancolombia.arka_javadevops.services.MetodoPagoUsuarioService;
import com.bancolombia.arka_javadevops.utils.ResponseObject;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/usuarios/metodosPagosUsuario")
@RequiredArgsConstructor
public class MetodoPagoUsuarioController {

    private final MetodoPagoUsuarioService metodoPagoUsuarioService;

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<ResponseObject> obtenerMetodosPagosUsuario(@PathVariable int idUsuario) {
        return new ResponseEntity<>(metodoPagoUsuarioService.obtenerMetodosPagosUsuario(idUsuario), HttpStatus.OK);
    }
    
    @PostMapping("/agregarMetodoPago/{idUsaurio}/{idMetodo}/{valorCuenta}")
    public ResponseEntity<ResponseObject> agregarMetodoPagoUsuario(@PathVariable(required = true) int idUsaurio
            , @PathVariable(required = true) int idMetodo
            , @PathVariable(required = true) double valorCuenta) {        
        return new ResponseEntity<>(metodoPagoUsuarioService.agregarMetodoPagoUsuario(idUsaurio, idMetodo, valorCuenta), HttpStatus.CREATED);
    }
    



}
