package com.bancolombia.arka_javadevops.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancolombia.arka_javadevops.models.Perfil;
import com.bancolombia.arka_javadevops.services.PerfilService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/usuarios/perfiles")
public class PerfilController {

    @Autowired
    private PerfilService perfilService;

    public PerfilController(PerfilService perfilService) {
        this.perfilService = perfilService;
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Perfil>> obtenerPerfiles() {
        return new ResponseEntity<>(perfilService.obtenerPerfiles(), HttpStatus.OK);
    }
    

}
