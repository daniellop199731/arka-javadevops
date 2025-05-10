package com.bancolombia.arka_javadevops.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity //Indica a Spring que esta clase es un bean y que refleja informacion de la base de datos
@Table(name = "perfiles") //Se especifica el nombre de la base de datos
@Data //Lombok, geters y seters envevidos
@NoArgsConstructor //Lombok: Constructor sin atributos envevido
@AllArgsConstructor //Lombok: Contructor con todos los atributos envevido
public class Perfil {

    @Id //Indica que este atributo es el ID de la tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPerfil")
    private int idPerfil;

    //Si el atributo se llama igual a la columna de la tabla la anotacion @Column 
    //no es necesaria
    @Column(name = "nombrePerfil")   
    @NotBlank
    @Max(30)                              
    private String nombrePerfil;
    
}
