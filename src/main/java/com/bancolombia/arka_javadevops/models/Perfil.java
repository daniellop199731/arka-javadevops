package com.bancolombia.arka_javadevops.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity //Indica a Spring que esta clase es un bean y que refleja informacion de la base de datos
@Table(name = "perfiles") //Se especifica el nombre de la base de datos
public class Perfil {

    @Id //Indica que este atributo es el ID de la tabla
    @Column(name = "idPerfil")
    private int idPerfil;

    //Si el atributo se llama igual a la columna de la tabla la anotacion @Column 
    //no es necesaria
    @Column(name = "nombrePerfil")                                 
    private String nombrePerfil; 

    //Para JPA es necesarios un constructor vacio
    public Perfil() {
    }

    public int getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getNombrePerfil() {
        return nombrePerfil;
    }

    public void setNombrePerfil(String nombrePerfil) {
        this.nombrePerfil = nombrePerfil;
    }
    
}
