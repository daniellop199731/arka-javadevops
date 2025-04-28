package com.bancolombia.arka_javadevops.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancolombia.arka_javadevops.models.Usuario;
import com.bancolombia.arka_javadevops.services.UsuarioService;
import com.bancolombia.arka_javadevops.utils.ResponseObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    public UsuarioController (UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @GetMapping("")
    public ResponseEntity<ResponseObject> obtenerUsuarios() {        
        try{
            return new ResponseEntity<>(usuarioService.obtenerUsuarios(), HttpStatus.OK);
        } catch(Exception ex){
            return new ResponseEntity<>(new ResponseObject("Error: ".concat(ex.getMessage()), ex)
                , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<ResponseObject> obtenerUsuarioPorId(@PathVariable(value = "idUsuario") int idUsuario){
        try{
            return new ResponseEntity<>(usuarioService.obtenerUsuarioPorId(idUsuario), HttpStatus.OK);
        } catch(Exception ex) {
            return new ResponseEntity<>(new ResponseObject("Error: ".concat(ex.getMessage()), ex)
                , HttpStatus.INTERNAL_SERVER_ERROR);
        }        
    }

    //Define una ruta qye permita buscar y devolver una lista de usuarios filtrados por su nombre
    @GetMapping("/busquedaPorNombre")
    public ResponseEntity<ResponseObject> obtenerUsuariosPorNombre(@RequestParam(value = "nombresUsuario", required = true) String nombreUsuario) {
        try{
            return new ResponseEntity<>(usuarioService.obtenerUsuariosPorNombres(nombreUsuario), HttpStatus.OK);
        } catch(Exception ex){            
            return new ResponseEntity<>(new ResponseObject("Error: ".concat(ex.getMessage()), ex)
                , HttpStatus.INTERNAL_SERVER_ERROR);
        }        
    }

    //Define una ruta que te devuelva la lista de todos los usuarios ordenados alfabéticamente
    @GetMapping("/obtenerUsuariosPorOrdenNombres")
    public ResponseEntity<ResponseObject> obtenerUsuariosPorOrdenNombres(){
        try{
            return new ResponseEntity<>(usuarioService.obtenerUsuariosPorOrdenNombres(), HttpStatus.OK);
        } catch(Exception ex){
            return new ResponseEntity<>(new ResponseObject("Error: ".concat(ex.getMessage()), ex)
                , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/busquedaPorIdentificacion")
    public ResponseEntity<ResponseObject> obtenerUsuarioPorIdentificacion(@RequestParam(value = "identificacionUsuario", required = true) String identificacionUsuario) {
        try{
            return new ResponseEntity<>(
                usuarioService.obtenerUsuarioPorIdentificacion(identificacionUsuario), HttpStatus.OK);      
        }catch(Exception ex){
            return new ResponseEntity<>(new ResponseObject("Error: ".concat(ex.getMessage()), ex)
                , HttpStatus.INTERNAL_SERVER_ERROR);
        }        
    }        

    @PostMapping("/crearNuevo")
    public ResponseEntity<ResponseObject> crearNuevoUsuario(@RequestBody Usuario usuario) {
        try{
            return new ResponseEntity<>(usuarioService.crearNuevUsuario(usuario), HttpStatus.CREATED);
        } catch(DataIntegrityViolationException ex) {        
            return new ResponseEntity<>(new ResponseObject("Error en los campos enviados", ex)
                , HttpStatus.BAD_REQUEST);
        } catch(Exception ex){
            return new ResponseEntity<>(new ResponseObject("Error : ".concat(ex.getMessage()), ex)
                , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/actualizarUsuario")
    public ResponseEntity<ResponseObject> putMethodName(@RequestBody Usuario usuario) {        
        try{            
            return new ResponseEntity<>(usuarioService.actualizarUsuario(usuario), HttpStatus.OK);
        } catch(DataIntegrityViolationException ex){            
            return new ResponseEntity<>(new ResponseObject("Error en los campos enviados", ex)
                , HttpStatus.BAD_REQUEST);
        } catch(Exception ex){
            return new ResponseEntity<>(new ResponseObject("Error : ".concat(ex.getMessage()), ex)
                , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/eliminar/{idUsuario}")
    public ResponseEntity<ResponseObject> eliminarUsuarioPorId(@PathVariable(value = "idUsuario", required = true) int idUsuario){
        try{
            return new ResponseEntity<>(usuarioService.eliminarUsuarioPorId(idUsuario), HttpStatus.OK);
        } catch(Exception ex){
            return new ResponseEntity<>(new ResponseObject("Error: ".concat(ex.getMessage()), ex)
                , HttpStatus.INTERNAL_SERVER_ERROR);
        }        
    }
}
