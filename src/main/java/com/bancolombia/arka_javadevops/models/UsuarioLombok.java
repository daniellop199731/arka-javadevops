package com.bancolombia.arka_javadevops.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
//Lombok
@Data //automatico getters, setters, toString
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioLombok {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private int idUsuario;

    @Column(columnDefinition = "TEXT")
    @Size(max=15)
    private String identificacionUsuario;

    @Email
    private String correoElectronicoUsuario;

    @NotBlank
    @NotNull
    private String nombresUsuario;

    @NotBlank
    @NotNull
    private String apellidosUsuario;

    @NotBlank
    @NotNull
    private String direccionDespachoUsuario;

    @NotBlank
    @NotNull
    private String contrasennaUsuario;

    private String nicknameUsuario;

    private int idPerfilUsuario;

}
