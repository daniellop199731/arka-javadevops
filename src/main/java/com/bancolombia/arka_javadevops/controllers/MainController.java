package com.bancolombia.arka_javadevops.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/main")
public class MainController {

    @GetMapping("/test1")
    public String holaMundo() {
        return "Hola mundo";
    }

    @GetMapping("/respuesta")
    public ResponseEntity<String> respuesta() {
        return new ResponseEntity<>("Esta es mi respuesta", HttpStatus.ACCEPTED);
    }
    

    @GetMapping("/buscar")
    public ResponseEntity<String> buscar(@RequestParam(value = "q", required = false) String busqueda) {

        if(busqueda != null){
            return new ResponseEntity<>("Estas buscando " + busqueda, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Falta el campo q ", HttpStatus.BAD_REQUEST);
        }

        
    }

    /*
     * Ejemplo de URL en postman: http://localhost:8080/main/usuarios/1040755872
     */
    @GetMapping("/usuarios/{id}")
    public ResponseEntity<String> mostrar(@PathVariable("id") String idUsuario) {
        return new ResponseEntity<>("Se solicitó info del usuario con  id: " + idUsuario, HttpStatus.OK);
    }
    
    
    

}
