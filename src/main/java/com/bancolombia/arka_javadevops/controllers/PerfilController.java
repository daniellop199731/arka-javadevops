package com.bancolombia.arka_javadevops.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancolombia.arka_javadevops.models.Perfil;
import com.bancolombia.arka_javadevops.services.PerfilService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/usuarios/perfiles")
@RequiredArgsConstructor
public class PerfilController {

    private final PerfilService perfilService;

    @GetMapping("")
    public ResponseEntity<List<Perfil>> obtenerPerfiles() {
        List<Perfil> perfiles = perfilService.obtenerPerfiles();
        return new ResponseEntity<>(perfiles, HttpStatus.OK);
    }

    @GetMapping("/{idPerfil}")
    public ResponseEntity<Optional<Perfil>> obtenerPerfilPorId(@PathVariable int idPerfil) {
        Optional<Perfil> perfil = perfilService.obtenerPerfilPorId(idPerfil);
        if(perfil.isPresent()){
         return new ResponseEntity<>(perfil, HttpStatus.OK);   
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @PostMapping("/crearNuevo")
    public ResponseEntity<Perfil> crearNuevoPerfil(@Valid @RequestBody Perfil perfil) {
        Perfil nuevoPerfil = perfilService.creaNuevoPerfil(perfil);
        return new ResponseEntity<>(nuevoPerfil, HttpStatus.OK);
    }

    @PutMapping("/actualizar/{idPerfil}")
    public ResponseEntity<Perfil> putMethodName(@PathVariable int idPerfil, @Valid @RequestBody Perfil perfil) {
        Perfil perfilActualizar = perfilService.actualizarPerfil(idPerfil, perfil);
        if(perfilActualizar == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(perfilActualizar, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/eliminar/{idPerfil}")
    public ResponseEntity<Void> eliminar(@PathVariable int idPerfil){
        if(perfilService.eliminarPerfil(idPerfil)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    

}
