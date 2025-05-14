package com.bancolombia.arka_javadevops.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancolombia.arka_javadevops.DTO.UsuarioDTO;
import com.bancolombia.arka_javadevops.models.Usuario;
import com.bancolombia.arka_javadevops.services.UsuarioService;

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
    public ResponseEntity<List<UsuarioDTO>> obtenerUsuarios() {      
        List<UsuarioDTO> usuarios = usuarioService.obtenerUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<UsuarioDTO> obtenerUsuarioPorId(@PathVariable int idUsuario){
        UsuarioDTO usuario = usuarioService.obtenerUsuarioPorId(idUsuario);
        if(usuario == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    //Define una ruta qye permita buscar y devolver una lista de usuarios filtrados por su nombre
    @GetMapping("/busquedaPorNombre")
    public ResponseEntity<List<UsuarioDTO>> obtenerUsuariosPorNombre(@RequestParam(value = "nombresUsuario", required = true) String nombreUsuario) {
        List<UsuarioDTO> usuarios = usuarioService.obtenerUsuariosPorNombres(nombreUsuario);
        return new ResponseEntity<>(usuarios, HttpStatus.OK);      
    }

    //Define una ruta que te devuelva la lista de todos los usuarios ordenados alfabéticamente
    @GetMapping("/obtenerUsuariosPorOrdenNombres")
    public ResponseEntity<List<UsuarioDTO>> obtenerUsuariosPorOrdenNombres(){
        List<UsuarioDTO> usuarios = usuarioService.obtenerUsuariosPorOrdenNombres();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/busquedaPorIdentificacion")
    public ResponseEntity<UsuarioDTO> obtenerUsuarioPorIdentificacion(@RequestParam(required = true) String identificacionUsuario) {
        UsuarioDTO usuario = usuarioService.obtenerUsuarioPorIdentificacion(identificacionUsuario);
        if(usuario == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }        

    @PostMapping("/crearNuevo")
    public ResponseEntity<UsuarioDTO> crearNuevoUsuario(@Valid @RequestBody Usuario usuario) {
        UsuarioDTO usuarioDTO = usuarioService.crearNuevoUsuario(usuario);
        if(usuarioDTO == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(usuarioDTO, HttpStatus.CREATED);
    }

    @PutMapping("/actualizarUsuario/{idUsuario}")
    public ResponseEntity<UsuarioDTO> putMethodName(@PathVariable int idUsuario, @Valid @RequestBody Usuario usuario) {    
        UsuarioDTO usuarioActualizar = usuarioService.actualizarUsuario(idUsuario, usuario);
        if(usuarioActualizar == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(usuarioActualizar, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{idUsuario}")
    public ResponseEntity<Void> eliminarUsuarioPorId(@PathVariable(required = true) int idUsuario){
        if(usuarioService.eliminarUsuarioPorId(idUsuario)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
             
    }   
}
