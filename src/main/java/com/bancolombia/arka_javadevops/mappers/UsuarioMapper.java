package com.bancolombia.arka_javadevops.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.bancolombia.arka_javadevops.DTO.UsuarioDTO;
import com.bancolombia.arka_javadevops.models.Usuario;

@Component
public class UsuarioMapper {

    public UsuarioDTO toDto(Usuario usuario){
        if(usuario == null){
            return null;
        }

        return new UsuarioDTO(
            usuario.getIdentificacionUsuario()
            ,usuario.getCorreoElectronicoUsuario()
            ,usuario.getNombresUsuario()
            ,usuario.getApellidosUsuario()
            ,usuario.getDireccionDespachoUsuario()
            ,usuario.getNicknameUsuario()
        );
    }

    public List<UsuarioDTO> toDto(List<Usuario> usuarios){
        List<UsuarioDTO> usuariosDTO = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            usuariosDTO.add(toDto(usuario));
        }
        return usuariosDTO;
    }

}
