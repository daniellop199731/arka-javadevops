package com.bancolombia.arka_javadevops.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bancolombia.arka_javadevops.mappers.UsuarioMapper;
import com.bancolombia.arka_javadevops.models.Usuario;
import com.bancolombia.arka_javadevops.repositories.UsuarioRepository;
import com.bancolombia.arka_javadevops.utils.ResponseObject;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    private static ResponseObject rObj;

    /* @RequiredArgsConstructor //Reemplaza el constructor
        La etiqueta lo que hara es que a todos los atributos con final, lo tendra encuenta
        en el contructor implicito que invoca @RequiredArgsConstructor

        Si se tiene final no es necesario el @Autowired

    public UsuarioService (UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }
    */    

    public ResponseObject obtenerUsuarios(){
        rObj = new ResponseObject();
        rObj.setAsSuccessfully();
        rObj.setMsj("Consulta ejecutada con exito");        
        rObj.setObj(usuarioMapper.toDto((List<Usuario>) usuarioRepository.findAll()));
        return rObj;
    }

    public ResponseObject obtenerUsuariosPorOrdenNombres(){
        rObj = new ResponseObject();
        rObj.setAsSuccessfully();
        rObj.setMsj("Consulta ejecutada con exito");
        rObj.setObj(usuarioMapper.toDto(usuarioRepository.usuariosOrderByNombres()));
        return rObj;
    }

    public ResponseObject obtenerUsuarioPorId(int idUsuario){
        rObj = new ResponseObject();
        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
        if(usuario.isPresent()){
            rObj.setObj(usuarioMapper.toDto(usuario.get()));
            rObj.setMsj("Usuario encontrado");
            rObj.setAsSuccessfully();
        } else {
            rObj.setMsj("El usuario con id ".concat(idUsuario+"").concat(" no existe"));
        }
        return rObj;
    }

    public ResponseObject obtenerUsuarioPorIdWitOutDto(int idUsuario){
        rObj = new ResponseObject();
        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
        if(usuario.isPresent()){
            rObj.setObj(usuario.get());
            rObj.setMsj("Usuario encontrado");
            rObj.setAsSuccessfully();
        } else {
            rObj.setMsj("El usuario con id ".concat(idUsuario+"").concat(" no existe"));
        }
        return rObj;
    }    

    public ResponseObject crearNuevoUsuario(Usuario usuario){
        rObj = new ResponseObject();
        boolean continueToSave = true;
        if(this.existeUsuarioPorIdentificacion(usuario.getIdentificacionUsuario())){            
            rObj.setMsj("El usuario con la identificacion "
                .concat(usuario.getIdentificacionUsuario()).concat(" ya existe en el sistema"));            
                continueToSave = false;
        } 

        if(continueToSave){
            /**
             * LOGICA ANTES DE GUARDAR EN BASE DE DATOS, AQUI
             * ...
             */           
            usuarioRepository.save(usuario);
            rObj.setObj(usuarioMapper.toDto(usuario));
            rObj.setMsj("Usuario creado con exito");
            rObj.setAsSuccessfully();
        }
        return rObj; 
    }

    public ResponseObject actualizarUsuario(int idUsuario,Usuario usuario){
        rObj = new ResponseObject();
        Optional<Usuario> usuarioEncontrado = usuarioRepository.findById(idUsuario);
        if(usuarioEncontrado.isPresent()){            
            /**
             * POSIBLE LOGICA ANTES DE ACTUALIZAR EN BASE DE DATOS, AQUI
            */
            usuario.setIdUsuario(idUsuario);    
            if(this.existeUsuarioPorIdentificacionParaActualizar(usuario)){
                rObj.setMsj("Ya existe un usuario con la identificacion ".concat(usuario.getIdentificacionUsuario()));
                rObj.setObj("");
                return rObj;           
            }                    
            /**/            
            usuarioRepository.save(usuario);
            rObj.setObj(usuarioMapper.toDto(usuario));
            rObj.setMsj("Usuario actualizado con exito");
            rObj.setAsSuccessfully(); 
            return rObj;               
        } 
        
        rObj.setMsj("El usuario con el ID ".concat(idUsuario+"").concat(" no existe"));
        return rObj;
    }    

    public ResponseObject eliminarUsuarioPorId(int idUsuario){
        rObj = new ResponseObject();
        Optional<Usuario> usuarioEncontrado = usuarioRepository.findById(idUsuario);        
        if(usuarioEncontrado.isPresent()){
            /**
             * LOGICA ANTES DE ELIMINAR EN BASE DE DATOS, AQUI
             * ...
             */            
            usuarioRepository.deleteById(idUsuario);
            rObj.setObj(usuarioMapper.toDto(usuarioEncontrado.get()));
            rObj.setMsj("Usuario eliminado con exito");
            rObj.setAsSuccessfully();
            return rObj;
        }
        rObj.setMsj("El usuario con el ID ".concat(idUsuario+"").concat(" no existe"));
        return rObj;
        
    }

    public ResponseObject obtenerUsuariosPorNombres(String nombresuString){
        rObj = new ResponseObject();
        List<Usuario> usuarios = usuarioRepository.findByNombresUsuario(nombresuString);
        if(usuarios.size() > 0){
            rObj.setObj(usuarioMapper.toDto(usuarios));
            rObj.setMsj("Usuarios encontrados");
            rObj.setAsSuccessfully();
        } else {
            rObj.setMsj("De momento no existen usuarios");
            rObj.setAsSuccessfully();
        }
        return rObj;
    }

    public ResponseObject obtenerUsuarioPorIdentificacion(String identificacionUsuario){
        rObj = new ResponseObject();
        Usuario usuario = usuarioRepository.findByIdentificacionUsuario(identificacionUsuario);
        if(usuario != null){
            rObj.setObj(usuarioMapper.toDto(usuario));
            rObj.setMsj("Usuario encontrado");
            rObj.setAsSuccessfully();
        } else {
            rObj.setMsj("No existe usuario con identificacion ".concat(identificacionUsuario));
            rObj.setAsSuccessfully();
        }
        return rObj;
    }

    //////////////////////////////////////
    public ResponseObject crearUsuarioSencillo(Usuario usuario){
        rObj = new ResponseObject();
        if(!this.existeUsuarioPorIdentificacion(usuario.getIdentificacionUsuario())){
            rObj.setAsSuccessfully();
            rObj.setMsj("Usuario creado con exito");
            rObj.setObj(usuarioMapper.toDto(usuarioRepository.save(usuario)));
        } else {
            rObj.setAsNotSuccessfully();;
            rObj.setMsj("Ya existe un usuario con la identificacion "
                .concat(usuario.getIdentificacionUsuario()));            
        }
        return rObj;
    }
    //////////////////////////////////////

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
