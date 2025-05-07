package com.bancolombia.arka_javadevops.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancolombia.arka_javadevops.models.Usuario;
import com.bancolombia.arka_javadevops.services.UsuarioService;
import com.bancolombia.arka_javadevops.utils.ResponseObject;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/usuariosSencillo")
@RequiredArgsConstructor
public class UsuarioSencilloController {

    private final UsuarioService usuarioService;

    private static ResponseObject rObj;

    @GetMapping("")
    public ResponseEntity<ResponseObject> obtenerUsuarios(){
        return new ResponseEntity<>(usuarioService.obtenerUsuarios(), HttpStatus.OK);
    }

    @PostMapping("/crearNuevo")
    public ResponseEntity<ResponseObject> crearNuevo(@Valid @RequestBody Usuario usuario) {        
        return new ResponseEntity<>(usuarioService.crearUsuarioSencillo(usuario), HttpStatus.CREATED);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<ResponseObject> actualizar(@Valid @RequestBody Usuario usuario) {
        rObj = usuarioService.obtenerUsuarioPorId(usuario.getIdUsuario());
        if(rObj.getObj() != null){
            return new ResponseEntity<>(usuarioService.actualizarUsuario(usuario), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(rObj, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/eliminar/{idUsuario}")
    public ResponseEntity<ResponseObject> eliminar(@PathVariable int idUsuario){
        rObj = usuarioService.obtenerUsuarioPorId(idUsuario);
        if(rObj.getObj() != null){
            return new ResponseEntity<>(usuarioService.eliminarUsuarioPorId(idUsuario), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(rObj, HttpStatus.NOT_FOUND);
        }
        
    }
    

}
