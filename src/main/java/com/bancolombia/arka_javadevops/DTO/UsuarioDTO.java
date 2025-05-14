package com.bancolombia.arka_javadevops.DTO;

import com.bancolombia.arka_javadevops.models.Perfil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    private int idUsuario;
    private String identificacionUsuario;
    private String correoElectronicoUsuario;
    private String nombresUsuario;
    private String apellidosUsuario;
    private String direccionDespachoUsuario;
    private String nicknameUsuario;
    private Perfil perfil;

}
