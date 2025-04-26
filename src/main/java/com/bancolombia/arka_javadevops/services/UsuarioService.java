package com.bancolombia.arka_javadevops.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bancolombia.arka_javadevops.models.Usuario;
import com.bancolombia.arka_javadevops.repositories.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioService (UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> obtenerUsuarios(){
        return (List<Usuario>) usuarioRepository.findAll();
    }

    public Optional<Usuario> obtenerUsuarioPorId(int idUsuario){
        return usuarioRepository.findById(idUsuario);
    }

    public Usuario crearNuevUsuario(Usuario usuario){
        Usuario usuarioNuevo = usuarioRepository.save(usuario);
        /*
         * Problema 1
         * En la BD el la primary key de usuarios es autoincrementable
         * Cuando hibernate genera al nuevo usuario no devuelve el nuevo ID en
         * el objeto, pero si la demas informacion, puede ser que es porque en 
         * la clase entity no se usa @GenerateValue en el campo marcado con @Id
         * 
         * Solucion:
         * En la clase entity, el atributo marcado con @Id, se debe marcar con @GenerateValue
         * con estrategia IDENTITY, con eso se indica que ese campo se enlaza con la secuentacia 
         * que tenga el campo en la base de datos
         */
        return usuarioNuevo; 
    }

    public Optional<Usuario> eliminarUsuarioPorId(int idUsuario){
        Optional<Usuario> usuarioAEliminar = usuarioRepository.findById(idUsuario);
        usuarioRepository.deleteById(idUsuario);
        return usuarioAEliminar;
    }

    public Usuario actualizarUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

}
