package com.bancolombia.arka_javadevops.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancolombia.arka_javadevops.models.Usuario;
import com.bancolombia.arka_javadevops.services.UsuarioService;
import com.bancolombia.arka_javadevops.utils.ResponseObject;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    private static ResponseObject rObj;

    @GetMapping("")
    public ResponseEntity<ResponseObject> obtenerUsuarios() {        
        return new ResponseEntity<>(usuarioService.obtenerUsuarios(), HttpStatus.OK);
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<ResponseObject> obtenerUsuarioPorId(@PathVariable int idUsuario){
        return new ResponseEntity<>(usuarioService.obtenerUsuarioPorId(idUsuario), HttpStatus.OK);    
    }

    //Define una ruta qye permita buscar y devolver una lista de usuarios filtrados por su nombre
    @GetMapping("/busquedaPorNombre")
    public ResponseEntity<ResponseObject> obtenerUsuariosPorNombre(@RequestParam(value = "nombresUsuario", required = true) String nombreUsuario) {
        return new ResponseEntity<>(usuarioService.obtenerUsuariosPorNombres(nombreUsuario), HttpStatus.OK);      
    }

    //Define una ruta que te devuelva la lista de todos los usuarios ordenados alfabéticamente
    @GetMapping("/obtenerUsuariosPorOrdenNombres")
    public ResponseEntity<ResponseObject> obtenerUsuariosPorOrdenNombres(){
        return new ResponseEntity<>(usuarioService.obtenerUsuariosPorOrdenNombres(), HttpStatus.OK);
    }

    @GetMapping("/busquedaPorIdentificacion")
    public ResponseEntity<ResponseObject> obtenerUsuarioPorIdentificacion(@RequestParam(required = true) String identificacionUsuario) {
        return new ResponseEntity<>(
            usuarioService.obtenerUsuarioPorIdentificacion(identificacionUsuario), HttpStatus.OK);
    }        

    @PostMapping("/crearNuevo")
    public ResponseEntity<ResponseObject> crearNuevoUsuario(@Valid @RequestBody Usuario usuario) {
        return new ResponseEntity<>(usuarioService.crearNuevoUsuario(usuario), HttpStatus.CREATED);
    }

    @PutMapping("/actualizarUsuario")
    public ResponseEntity<ResponseObject> putMethodName(@Valid @RequestBody Usuario usuario) {    
        rObj = usuarioService.obtenerUsuarioPorId(usuario.getIdUsuario());
        if(rObj.getObj() != null){
            return new ResponseEntity<>(usuarioService.actualizarUsuario(usuario), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(rObj, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/eliminar/{idUsuario}")
    public ResponseEntity<ResponseObject> eliminarUsuarioPorId(@PathVariable(required = true) int idUsuario){
        rObj = usuarioService.obtenerUsuarioPorId(idUsuario);
        if(rObj.getObj() != null){
            return new ResponseEntity<>(usuarioService.eliminarUsuarioPorId(idUsuario), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(rObj, HttpStatus.NOT_FOUND);
        }
             
    }   
}
