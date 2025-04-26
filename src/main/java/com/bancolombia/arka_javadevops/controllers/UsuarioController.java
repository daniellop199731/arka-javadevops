package com.bancolombia.arka_javadevops.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancolombia.arka_javadevops.models.Usuario;
import com.bancolombia.arka_javadevops.services.UsuarioService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    public UsuarioController (UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @GetMapping("")
    public ResponseEntity<List<Usuario>> obtenerUsuarios() {
        return new ResponseEntity<>(usuarioService.obtenerUsuarios(), HttpStatus.OK);
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<Optional<Usuario>> obtenerUsuarioPorId(@PathVariable(value = "idUsuario") int idUsuario){
        return new ResponseEntity<>(usuarioService.obtenerUsuarioPorId(idUsuario), HttpStatus.OK);
    }

    @PostMapping("/crearNuevo")
    public ResponseEntity<Usuario> crearNuevoUsuario(@RequestBody Usuario nuevUsuario) {
        return new ResponseEntity<>(usuarioService.crearNuevUsuario(nuevUsuario), HttpStatus.OK);
    }

    @PutMapping("/actualizarUsuario")
    public ResponseEntity<Usuario> putMethodName(@RequestBody Usuario usuario) {        
        return new ResponseEntity<>(usuarioService.actualizarUsuario(usuario), HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{idUsuario}")
    public ResponseEntity<Optional<Usuario>> eliminarUsuarioPorId(@PathVariable(value = "idUsuario") int idUsuario){
        return new ResponseEntity<>(usuarioService.eliminarUsuarioPorId(idUsuario), HttpStatus.OK);
    }

}
