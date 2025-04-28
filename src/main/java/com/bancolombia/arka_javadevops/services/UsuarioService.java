package com.bancolombia.arka_javadevops.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bancolombia.arka_javadevops.models.Usuario;
import com.bancolombia.arka_javadevops.repositories.UsuarioRepository;
import com.bancolombia.arka_javadevops.utils.ResponseObject;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private static ResponseObject rObj;

    public UsuarioService (UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public ResponseObject obtenerUsuarios(){
        rObj = new ResponseObject();
        rObj.setObj((List<Usuario>) usuarioRepository.findAll());
        rObj.setMsj("Consulta ejecutada con exito");
        rObj.setAsSuccessfully();
        return rObj;
    }

    public ResponseObject obtenerUsuariosPorOrdenNombres(){
        rObj = new ResponseObject();
        rObj.setAsSuccessfully();
        rObj.setMsj("Consulta ejecutada con exito");
        rObj.setObj(usuarioRepository.usuariosOrderByNombres());
        return rObj;
    }

    public ResponseObject obtenerUsuarioPorId(int idUsuario){
        rObj = new ResponseObject();
        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
        if(usuario.isPresent()){
            rObj.setObj(usuario);
            rObj.setMsj("Usuario encontrado");
            rObj.setAsSuccessfully();
        } else {
            rObj.setMsj("Usuario no encontrado");
        }
        return rObj;
    }

    public ResponseObject crearNuevUsuario(Usuario usuario){
        rObj = new ResponseObject();
        Usuario usuarioNuevo = usuarioRepository.findByIdentificacionUsuario(
            usuario.getIdentificacionUsuario());
        if(usuarioNuevo != null){
            rObj.setObj(usuarioNuevo);
            rObj.setMsj("El usuario con la identificacion "
                .concat(usuario.getIdentificacionUsuario()).concat(" ya existe en el sistema"));            
        } else {
            /**
             * LOGICA ANTES DE GUARDAR EN BASE DE DATOS, AQUI
             * ...
             */
            usuarioNuevo = usuarioRepository.save(usuario);
            rObj.setObj(usuarioNuevo);
            rObj.setMsj("Usuario creado con exito");
            rObj.setAsSuccessfully();
        }
        return rObj; 
    }

    public ResponseObject actualizarUsuario(Usuario usuario){
        rObj = new ResponseObject();
        Optional<Usuario> usuarioEncontrado = usuarioRepository.findById(usuario.getIdUsuario());
        if(usuarioEncontrado.isPresent()){
            /**
             * POSIBLE LOGICA ANTES DE ACTUALIZAR EN BASE DE DATOS, AQUI
             * ...
             */            
            rObj.setObj(usuarioRepository.save(usuario));
            rObj.setMsj("Usuario actualizado con exito");
            rObj.setAsSuccessfully();
        } else {
            rObj.setObj(usuarioEncontrado);
            rObj.setMsj("El usuario a actualizar no existe");
        }
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
            rObj.setObj(usuarioRepository.findById(idUsuario));
            rObj.setMsj("Usuario eliminado con exito");
            rObj.setAsSuccessfully();
        } else {
            rObj.setMsj("El usuario que se quiere eliminar no existe");
        }
        return rObj;
    }

    public ResponseObject obtenerUsuariosPorNombres(String nombresuString){
        rObj = new ResponseObject();
        List<Usuario> usuarios = usuarioRepository.findByNombresUsuario(nombresuString);
        if(usuarios.size() > 0){
            rObj.setObj(usuarios);
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
            rObj.setObj(usuario);
            rObj.setMsj("Usuario encontrado");
            rObj.setAsSuccessfully();
        } else {
            rObj.setMsj("No existe usuario con identificacion ".concat(identificacionUsuario));
            rObj.setAsSuccessfully();
        }
        return rObj;
    }

}
