package com.bancolombia.arka_javadevops.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bancolombia.arka_javadevops.DTO.UsuarioDTO;
import com.bancolombia.arka_javadevops.mappers.UsuarioMapper;
import com.bancolombia.arka_javadevops.models.Usuario;
import com.bancolombia.arka_javadevops.repositories.UsuarioRepository;
import com.bancolombia.arka_javadevops.utils.ResponseGenericObject;
import com.bancolombia.arka_javadevops.utils.ResponseObject;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    private static ResponseObject rObj;
    private static ResponseGenericObject<List<UsuarioDTO>> rgObjList;
    private static ResponseGenericObject<UsuarioDTO> rgObjDto;

    /* @RequiredArgsConstructor //Reemplaza el constructor
        La etiqueta lo que hara es que a todos los atributos con final, lo tendra encuenta
        en el contructor implicito que invoca @RequiredArgsConstructor

        Si se tiene final no es necesario el @Autowired

    public UsuarioService (UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }
    */    

    public ResponseGenericObject<List<UsuarioDTO>> obtenerUsuarios(){
        rgObjList = new ResponseGenericObject<>();
        List<Usuario> usuarios = (List<Usuario>) usuarioRepository.findAll();
        if(usuarios.isEmpty()){
            rgObjList.setAsNotSuccessfully("No hay usuarios creados");
            return rgObjList;
        }
        rgObjList.setAsSuccessfully("Consulta ejecutada con exito"
            , usuarioMapper.usuariosToUsuariosDto(usuarios));
        return rgObjList;
    }

    public ResponseGenericObject<List<UsuarioDTO>> obtenerUsuariosPorOrdenNombres(){
        rgObjList = new ResponseGenericObject<>();
        List<Usuario> usuarios = (List<Usuario>) usuarioRepository.usuariosOrderByNombres();
        if(usuarios.isEmpty()){
            rgObjList.setAsNotSuccessfully("No hay usuarios creados");
            return rgObjList;
        }
        rgObjList.setAsSuccessfully("Consulta ejecutada con exito"
            , usuarioMapper.usuariosToUsuariosDto(usuarios));
        return rgObjList;
    }

    public ResponseGenericObject<UsuarioDTO> obtenerUsuarioPorId(int idUsuario){
        rgObjDto = new ResponseGenericObject<>();
        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
        if(usuario.isPresent()){
            rgObjDto.setAsSuccessfully("Usuario encontrado", usuarioMapper.usuarioToUsuarioDTO(usuario.get()));
            return rgObjDto;
        }
        rgObjDto.setAsNotSuccessfully("El usuario con id ".concat(idUsuario+"").concat(" no existe"));
        return rgObjDto;
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

    public ResponseGenericObject<UsuarioDTO> crearNuevoUsuario(UsuarioDTO usuarioDto){
        rgObjDto = new ResponseGenericObject<>();
        //PASO 4: Validar cual DTO se recibe de la peticion para saber si el DTO, se convierte a ExpirableUsuario o a Usuario.
        Usuario usuario = new Usuario();
        if(Objects.nonNull(usuarioDto.getFechaExpiracion())){
            //Si entra aqui quiere decir que en la peticion se recibio el campo fechaExpiracion
            usuario = usuarioMapper.usuarioDTOToExpirableUsuario(usuarioDto);
        } else {
            //Si entra aqui quiere decir que en la peticion no se recibio el campo fechaExpiracion
            usuario = usuarioMapper.usuarioDtoToUsuario(usuarioDto);
        } 
        
        /**
         * LOGICA ANTES DE GUARDAR EN BASE DE DATOS, AQUI
         * ...
         */
        if(this.existeUsuarioPorIdentificacion(usuario.getIdentificacionUsuario())){    
            rgObjDto.setAsNotSuccessfully(
                "El usuario con la identificacion "
                .concat(usuario.getIdentificacionUsuario()).concat(" ya existe en el sistema")
            );            
            return rgObjDto;
        } 
         /**/      
        rgObjDto.setAsSuccessfully("Usuario creado con exito", usuarioMapper.usuarioToUsuarioDTO(usuarioRepository.save(usuario)));
        return rgObjDto; 
    }

    public ResponseGenericObject<UsuarioDTO> actualizarUsuario(int idUsuario,Usuario usuario){
        rgObjDto = new ResponseGenericObject<>();
        Optional<Usuario> usuarioEncontrado = usuarioRepository.findById(idUsuario);
        if(usuarioEncontrado.isPresent()){            
            /**
             * POSIBLE LOGICA ANTES DE ACTUALIZAR EN BASE DE DATOS, AQUI
            */
            usuario.setIdUsuario(idUsuario);    
            if(this.existeUsuarioPorIdentificacionParaActualizar(usuario)){
                rgObjDto.setAsNotSuccessfully("Ya existe un usuario con la identificacion ".concat(usuario.getIdentificacionUsuario()));
                return rgObjDto;           
            }                    
            /**/   
            rgObjDto.setAsSuccessfully("Usuario actualizado con exito", usuarioMapper.usuarioToUsuarioDTO(usuarioRepository.save(usuario)));
            return rgObjDto;               
        } 
        rgObjDto.setAsNotSuccessfully("El usuario con el ID ".concat(idUsuario+"").concat(" no existe"));
        return rgObjDto;
    }    

    public ResponseGenericObject<UsuarioDTO> eliminarUsuarioPorId(int idUsuario){
        rgObjDto = new ResponseGenericObject<>();
        Optional<Usuario> usuarioEncontrado = usuarioRepository.findById(idUsuario);        
        if(usuarioEncontrado.isPresent()){
            /**
             * LOGICA ANTES DE ELIMINAR EN BASE DE DATOS, AQUI
             * ...
             */     
            usuarioRepository.deleteById(idUsuario);
            rgObjDto.setAsSuccessfully("Usuario eliminado con exito", usuarioMapper.usuarioToUsuarioDTO(usuarioEncontrado.get()));
            return rgObjDto;
        }
        rgObjDto.setAsNotSuccessfully("El usuario con el ID ".concat(idUsuario+"").concat(" no existe"));
        return rgObjDto;
        
    }

    public ResponseGenericObject<List<UsuarioDTO>> obtenerUsuariosPorNombres(String nombresuString){
        rgObjList = new ResponseGenericObject<>();
        List<Usuario> usuarios = usuarioRepository.findByNombresUsuario(nombresuString);
        if(usuarios.isEmpty()){
            rgObjList.setAsNotSuccessfully("De momento no existen usuarios");
            return rgObjList;
        }
        rgObjList.setAsSuccessfully("Consulta ejecutada con exito", usuarioMapper.usuariosToUsuariosDto(usuarios));
        return rgObjList;
    }

    public ResponseGenericObject<UsuarioDTO> obtenerUsuarioPorIdentificacion(String identificacionUsuario){
        rgObjDto = new ResponseGenericObject<>();
        Usuario usuario = usuarioRepository.findByIdentificacionUsuario(identificacionUsuario);
        if(usuario != null){
            rgObjDto.setAsSuccessfully("Usuario encontrado", usuarioMapper.usuarioToUsuarioDTO(usuario));
            return rgObjDto;
        }
        rgObjDto.setAsNotSuccessfully("No existe usuario con identificacion ".concat(identificacionUsuario));
        return rgObjDto;
    }

    //////////////////////////////////////
    public ResponseObject crearUsuarioSencillo(Usuario usuario){
        rObj = new ResponseObject();
        if(!this.existeUsuarioPorIdentificacion(usuario.getIdentificacionUsuario())){
            rObj.setAsSuccessfully();
            rObj.setMsj("Usuario creado con exito");
            rObj.setObj(usuarioMapper.usuarioToUsuarioDTO(usuarioRepository.save(usuario)));
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
