package com.bancolombia.arka_javadevops.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancolombia.arka_javadevops.models.MetodoPago;
import com.bancolombia.arka_javadevops.services.MetodoPagoService;
import com.bancolombia.arka_javadevops.utils.ResponseGenericObject;
import com.bancolombia.arka_javadevops.utils.ResponseObject;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/usuarios/metodosPago")
@RequiredArgsConstructor
public class MetodoPagoController {

    private final MetodoPagoService metodoPagoService;

    @GetMapping("")
    public ResponseEntity<ResponseGenericObject<List<MetodoPago>>> 
        obtenerMetodosPago() {
            ResponseGenericObject<List<MetodoPago>> obj = metodoPagoService.obtenerMetodosPago();
            return new ResponseEntity<>(obj, obj.getHttpStatus());
    }

    @GetMapping("/{idMetodoPago}")
    public ResponseEntity<ResponseGenericObject<MetodoPago>> 
        obtenerMetodoPagoPorId(@PathVariable int idMetodoPago) {
            ResponseGenericObject<MetodoPago> response = metodoPagoService.obtenerMetodoPagoPorId(idMetodoPago);
            return new ResponseEntity<>(response, response.getHttpStatus());         
    }

    @PostMapping("/crearNuevo")
    public ResponseEntity<ResponseGenericObject<MetodoPago>> crearNuevo(@Valid @RequestBody MetodoPago metodoPago) {
        ResponseGenericObject<MetodoPago> response = metodoPagoService.crearNuevo(metodoPago);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }
    
    

}
