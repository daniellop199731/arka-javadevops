package com.bancolombia.arka_javadevops.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bancolombia.arka_javadevops.DTO.UsuarioDTO;
import com.bancolombia.arka_javadevops.mappers.UsuarioMapper;
import com.bancolombia.arka_javadevops.models.Usuario;
import com.bancolombia.arka_javadevops.repositories.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    /* @RequiredArgsConstructor //Reemplaza el constructor
        La etiqueta lo que hara es que a todos los atributos con final, lo tendra encuenta
        en el contructor implicito que invoca @RequiredArgsConstructor

        Si se tiene final no es necesario el @Autowired

    public UsuarioService (UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }
    */    

    public List<UsuarioDTO> obtenerUsuarios(){
        return usuarioMapper.toDto((List<Usuario>) usuarioRepository.findAll());
    }

    public List<UsuarioDTO> obtenerUsuariosPorOrdenNombres(){
        return usuarioMapper.toDto(usuarioRepository.usuariosOrderByNombres());
    }

    public UsuarioDTO obtenerUsuarioPorId(int idUsuario){
        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
        if(usuario.isPresent()){
            return usuarioMapper.toDto(usuario.get());
        }
        return null;
    }

    public Usuario obtenerUsuarioPorIdWitOutDto(int idUsuario){
        return usuarioRepository.findById(idUsuario).orElse(null);
    }    

    public UsuarioDTO crearNuevoUsuario(Usuario usuario){
        if(this.existeUsuarioPorIdentificacion(usuario.getIdentificacionUsuario())){            
            return null;
        } 
        return usuarioMapper.toDto(usuarioRepository.save(usuario)); 
    }

    public UsuarioDTO actualizarUsuario(int idUsuario,Usuario usuario){
        Optional<Usuario> usuarioEncontrado = usuarioRepository.findById(idUsuario);
        if(usuarioEncontrado.isPresent()){            
            /**
             * POSIBLE LOGICA ANTES DE ACTUALIZAR EN BASE DE DATOS, AQUI
            */
            usuario.setIdUsuario(idUsuario);    
            if(this.existeUsuarioPorIdentificacionParaActualizar(usuario)){
                return null;           
            }                    
            /**/ 
            return usuarioMapper.toDto(usuarioRepository.save(usuario));              
        } 
        
        return null;
    }    

    public boolean eliminarUsuarioPorId(int idUsuario){
        Optional<Usuario> usuarioEncontrado = usuarioRepository.findById(idUsuario);        
        if(usuarioEncontrado.isPresent()){
            /**
             * LOGICA ANTES DE ELIMINAR EN BASE DE DATOS, AQUI
             * ...
             */            
            usuarioRepository.deleteById(idUsuario);
            return true;
        }
        return false;
        
    }

    public List<UsuarioDTO> obtenerUsuariosPorNombres(String nombresuString){
        return usuarioMapper.toDto(usuarioRepository.findByNombresUsuario(nombresuString));
    }

    public UsuarioDTO obtenerUsuarioPorIdentificacion(String identificacionUsuario){
        Usuario usuario = usuarioRepository.findByIdentificacionUsuario(identificacionUsuario);
        if(usuario == null){
            return null;
        } 
        return usuarioMapper.toDto(usuario);
    }

    public boolean existeUsuarioPorIdentificacion(String identificacionUsuario){
        if(identificacionUsuario != null){
            Usuario usuarioPorIdentificacion = usuarioRepository.findByIdentificacionUsuario(identificacionUsuario);
            if(usuarioPorIdentificacion != null){
                usuarioPorIdentificacion = null;
                return true;
            }
        }
        return false;
    }    

    public boolean existeUsuarioPorIdentificacionParaActualizar(Usuario usuarioActualizar){
        if(usuarioActualizar.getIdentificacionUsuario() != null){
            Usuario usuarioPorIdentificacion = usuarioRepository.findByIdentificacionUsuario(usuarioActualizar.getIdentificacionUsuario());
            if(usuarioPorIdentificacion != null && 
                ((usuarioPorIdentificacion != null ? usuarioPorIdentificacion.getIdUsuario():0)
                    !=
                    (usuarioActualizar.getIdUsuario())) ){
                        usuarioPorIdentificacion = null;
                return true;
            }
        }
        return false;
    }    

}
