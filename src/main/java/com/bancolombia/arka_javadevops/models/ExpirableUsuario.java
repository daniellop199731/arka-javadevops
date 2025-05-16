package com.bancolombia.arka_javadevops.models;

import java.util.Date;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//PASO 1: Se crea extension de Usuario (ExpirableUsuario)
//Siguiente paso: ver ExpirableUsuarioDTO
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpirableUsuario extends Usuario {

    private Date fechaExpiracion;

}
