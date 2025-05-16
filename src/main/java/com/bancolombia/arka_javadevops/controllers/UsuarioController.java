package com.bancolombia.arka_javadevops.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancolombia.arka_javadevops.DTO.UsuarioDTO;
import com.bancolombia.arka_javadevops.models.Usuario;
import com.bancolombia.arka_javadevops.services.UsuarioService;
import com.bancolombia.arka_javadevops.utils.ResponseGenericObject;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

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

    @GetMapping("")
    public ResponseEntity<ResponseGenericObject<List<UsuarioDTO>>> obtenerUsuarios() {     
        ResponseGenericObject<List<UsuarioDTO>> response = usuarioService.obtenerUsuarios();
        if(response.isSuccessfully()){
            return new ResponseEntity<>(response, HttpStatus.OK);
        } 
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<ResponseGenericObject<UsuarioDTO>> obtenerUsuarioPorId(@PathVariable int idUsuario){
        ResponseGenericObject<UsuarioDTO> response = usuarioService.obtenerUsuarioPorId(idUsuario);
        if(response.isSuccessfully()){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);    
    }

    //Define una ruta qye permita buscar y devolver una lista de usuarios filtrados por su nombre
    @GetMapping("/busquedaPorNombre")
    public ResponseEntity<ResponseGenericObject<List<UsuarioDTO>>> obtenerUsuariosPorNombre(
        @RequestParam(required = true) String nombreUsuario) {
            ResponseGenericObject<List<UsuarioDTO>> response = usuarioService.obtenerUsuariosPorNombres(nombreUsuario);
            if(response.isSuccessfully()){
                return new ResponseEntity<>(response, HttpStatus.OK);      
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Define una ruta que te devuelva la lista de todos los usuarios ordenados alfabéticamente
    @GetMapping("/obtenerUsuariosPorOrdenNombres")
    public ResponseEntity<ResponseGenericObject<List<UsuarioDTO>>> obtenerUsuariosPorOrdenNombres(){
    ResponseGenericObject<List<UsuarioDTO>> response = usuarioService.obtenerUsuariosPorOrdenNombres();
    if(response.isSuccessfully()){
        return new ResponseEntity<>(response, HttpStatus.OK);      
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/busquedaPorIdentificacion")
    public ResponseEntity<ResponseGenericObject<UsuarioDTO>> 
        obtenerUsuarioPorIdentificacion(@RequestParam(required = true) String identificacionUsuario) {
            ResponseGenericObject<UsuarioDTO> response = usuarioService.obtenerUsuarioPorIdentificacion(identificacionUsuario);
            if(response.isSuccessfully()){
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }        

    @PostMapping("/crearNuevo")
    public ResponseEntity<ResponseGenericObject<UsuarioDTO>> crearNuevoUsuario(@Valid @RequestBody UsuarioDTO usuario) {
        ResponseGenericObject<UsuarioDTO> response = usuarioService.crearNuevoUsuario(usuario);
        if(response.isSuccessfully()){
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/actualizarUsuario/{idUsuario}")
    public ResponseEntity<ResponseGenericObject<UsuarioDTO>> actualizar(@PathVariable int idUsuario, @Valid @RequestBody Usuario usuario) {    
        ResponseGenericObject<UsuarioDTO> response = usuarioService.actualizarUsuario(idUsuario, usuario);
        if(response.isSuccessfully()){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @DeleteMapping("/eliminar/{idUsuario}")
    public ResponseEntity<ResponseGenericObject<UsuarioDTO>> eliminarUsuarioPorId(@PathVariable(required = true) int idUsuario){
        ResponseGenericObject<UsuarioDTO> response = usuarioService.eliminarUsuarioPorId(idUsuario);
        if(response.isSuccessfully()){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
             
    }   
}
