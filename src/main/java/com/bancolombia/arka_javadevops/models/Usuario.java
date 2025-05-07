package com.bancolombia.arka_javadevops.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    /*
     * Se indica que ese campo se enlaza con la secuentacia 
     * que tenga el campo en la base de datos
     */    
    private int idUsuario;

    @NotNull
    @NotBlank(message = "Debe proporcionar una ideantificacion")
    @Size(min = 9, max = 15, message = "Debe proporcionar una ideantificacion valida")
    private String identificacionUsuario;

    @NotNull
    @Email(message = "Debe proporcionar una direccion de correo con una estructura valida")
    private String correoElectronicoUsuario;

    @NotNull
    @NotBlank(message = "Debe proporcionar los nombres")
    @Size(min = 3, max = 45)
    private String nombresUsuario;

    @NotNull
    @NotBlank(message = "Debe proporcionar los apellidos")   
    @Size(min = 5, max = 45)
    private String apellidosUsuario;

    @NotNull
    @NotBlank(message = "Debe proporcionar una direccion de despacho")
    private String direccionDespachoUsuario;

    @NotNull
    @NotBlank(message = "Debe proporcionar una contraseña")
    @Size(min = 8, max = 45)
    private String contrasennaUsuario;

    private String nicknameUsuario;

    @Positive(message = "Debe proporcionar un ID de perfil valido")
    private int idPerfilUsuario;

    

}
