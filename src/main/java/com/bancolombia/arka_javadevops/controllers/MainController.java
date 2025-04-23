package com.bancolombia.arka_javadevops.controllers;

import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping("/buscar")
    public String buscar(@RequestParam(value = "q", required = false) String busqueda) {
        return "Estas buscando " + busqueda;
    }

    /*
     * Ejemplo de URL en postman: http://localhost:8080/main/usuarios/1040755872
     */
    @GetMapping("/usuarios/{id}")
    public String mostrar(@PathVariable("id") String idUsuario) {
        return "Se solicitó info del usuario con  id: " + idUsuario;
    }
    
    
    

}
