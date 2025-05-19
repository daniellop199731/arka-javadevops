package com.bancolombia.arka_javadevops.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.bancolombia.arka_javadevops.DTO.UsuarioDTO;
import com.bancolombia.arka_javadevops.mappers.UsuarioMapper;
import com.bancolombia.arka_javadevops.models.Usuario;
import com.bancolombia.arka_javadevops.repositories.UsuarioRepository;
import com.bancolombia.arka_javadevops.services.UsuarioService;
import com.bancolombia.arka_javadevops.utils.ResponseGenericObject;
import com.bancolombia.arka_javadevops.utils.ResponseObject;

public class UsuarioServiceTest {

    //Instancia falsa, para simular la funcionalidad real
    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private UsuarioMapper usuarioMapper;

    //Inyecta la dependencia en la clase
    @InjectMocks
    private UsuarioService usuarioService;

    //Procesa las anotaciones creadas
    //Para este caso, crea la instancia falsa (proxy) de usuarioRepository
    //luego le inyecta la instancia falsa a usuarioController
    @BeforeEach
    public void setup(){
        org.mockito.MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testObtenerUsuariosConRegistros(){
        when(usuarioRepository.findAll())
            .thenReturn(List.of(new Usuario()));

        ResponseGenericObject<List<UsuarioDTO>> response = usuarioService.obtenerUsuarios();
        assertNotNull(response);
        assertTrue(response.isSuccessfully());
        assertEquals("Consulta ejecutada con exito", response.getMsj());
        assertNotNull(response.getObj());
    }

    @Test
    public void testObtenerUsuariosSinRegistros(){
        when(usuarioRepository.findAll())
            .thenReturn(List.of());
        ResponseGenericObject<List<UsuarioDTO>> response = usuarioService.obtenerUsuarios();
        assertNotNull(response);
        assertFalse(response.isSuccessfully());
        assertEquals("No hay usuarios creados", response.getMsj());
        assertNull(response.getObj());
    }

    @Test
    public void testObtenerUsuariosPorOrdenNombresConRegistros(){
        when(usuarioRepository.usuariosOrderByNombres())
            .thenReturn(List.of(new Usuario()));
        ResponseGenericObject<List<UsuarioDTO>> response = usuarioService.obtenerUsuariosPorOrdenNombres();
        assertNotNull(response);
        assertTrue(response.isSuccessfully());
        assertEquals("Consulta ejecutada con exito", response.getMsj());
        assertNotNull(response.getObj());
    } 
    
    @Test
    public void testObtenerUsuariosPorOrdenNombresSinRegistros(){
        when(usuarioRepository.usuariosOrderByNombres())
            .thenReturn(List.of());
        ResponseGenericObject<List<UsuarioDTO>> response = usuarioService.obtenerUsuariosPorOrdenNombres();
        assertNotNull(response);
        assertFalse(response.isSuccessfully());
        assertEquals("No hay usuarios creados", response.getMsj());
        assertNull(response.getObj());
    }    

    @Test
    public void testObtenerUsuarioPorIdConRegistro(){
        when(usuarioRepository.findById(any()))
            .thenReturn(Optional.of(new Usuario()));
        when(usuarioMapper.usuarioToUsuarioDTO(any()))
            .thenReturn(new UsuarioDTO());
        ResponseGenericObject<UsuarioDTO> response = usuarioService.obtenerUsuarioPorId(1);
        assertNotNull(response);
        assertTrue(response.isSuccessfully());
        assertEquals("Usuario encontrado", response.getMsj());
        assertNotNull(response.getObj());
    }

    @Test
    public void testObtenerUsuarioPorIdSinRegistro(){
        when(usuarioRepository.findById(1))
            .thenReturn(Optional.ofNullable(null));
        ResponseGenericObject<UsuarioDTO> response = usuarioService.obtenerUsuarioPorId(1);
        assertNotNull(response);
        assertFalse(response.isSuccessfully());
        assertEquals("El usuario con id ".concat(1+"").concat(" no existe"), response.getMsj());
        assertNull(response.getObj());
    }  
    
    @Test
    public void testObtenerUsuarioPorIdWitOutDtoConRegistro(){
        when(usuarioRepository.findById(1))
            .thenReturn(Optional.of(new Usuario()));
        ResponseObject response = usuarioService.obtenerUsuarioPorIdWitOutDto(1);
        assertNotNull(response);
        assertTrue(response.getSuccessfully());
        assertEquals("Usuario encontrado", response.getMsj());
        assertNotNull(response.getObj());
    }

    @Test
    public void testObtenerUsuarioPorIdWitOutDtoSinRegistro(){
        when(usuarioRepository.findById(1))
            .thenReturn(Optional.ofNullable(null));
        ResponseObject response = usuarioService.obtenerUsuarioPorIdWitOutDto(1);
        assertNotNull(response);
        assertFalse(response.getSuccessfully());
        assertEquals("El usuario con id ".concat(1+"").concat(" no existe"), response.getMsj());
        assertNull(response.getObj());
    }    

    @Test
    public void testCrearNuevoExitoso(){
        when(usuarioRepository.save(any()))
            .thenReturn(new Usuario(1, "123123123", "correo@correo.com"
            , "nombres", "apellidos", null, null, null, null, null, null, null));

        when(usuarioRepository.findByIdentificacionUsuario(any(String.class)))
            .thenReturn(null);

        when(usuarioMapper.usuarioDtoToUsuario(any(UsuarioDTO.class)))
            .thenReturn(new Usuario(1, "123123123", "correo@correo.com"
            , "nombres", "apellidos", null, null, null, null, null, null, null));

        when(usuarioMapper.usuarioToUsuarioDTO(any(Usuario.class)))
            .thenReturn(new UsuarioDTO("123123123", "correo@correo.com", "nombres", "apellidos",null));            

        ResponseGenericObject<UsuarioDTO> response = usuarioService.crearNuevoUsuario(
            new UsuarioDTO("123123123", "correo@correo.com", "nombres", "apellidos", null )
        );
        assertNotNull(response);
        assertTrue(response.isSuccessfully());
        assertEquals("Usuario creado con exito", response.getMsj());
        assertNotNull(response.getObj());
    }

    @Test
    public void testCrearNuevoNoExitoso(){
        when(usuarioRepository.save(any()))
            .thenReturn(new Usuario(1, "123123123", "correo@correo.com"
            , "nombres", "apellidos", null, null, null, null, null, null, null));

        when(usuarioRepository.findByIdentificacionUsuario("123123123"))
            .thenReturn(new Usuario(1, "123123123", "correo@correo.com"
            , "nombres", "apellidos", null, null, null, null, null, null, null));

        when(usuarioMapper.usuarioDtoToUsuario(any(UsuarioDTO.class)))
            .thenReturn(new Usuario(1, "123123123", "correo@correo.com"
            , "nombres", "apellidos", null, null, null, null, null, null, null));

        ResponseGenericObject<UsuarioDTO> response = usuarioService.crearNuevoUsuario(
            new UsuarioDTO("123123123", "correo@correo.com", "nombres", "apellidos", null )
        );
        assertNotNull(response);
        assertFalse(response.isSuccessfully());
        assertEquals("El usuario con la identificacion "
            .concat("123123123").concat(" ya existe en el sistema"), response.getMsj());
        assertNull(response.getObj());
    } 
    
    @Test
    public void testActualizarUsuarioExitoso(){
        when(usuarioRepository.save(any()))
            .thenReturn(new Usuario(1, "123123123", "correo@correo.com"
            , "nombres", "apellidos", null, null, null, null, null, null, null));

        when(usuarioRepository.findById(any()))
            .thenReturn(Optional.of(new Usuario()));            

        when(usuarioRepository.findByIdentificacionUsuario("123123123"))
            .thenReturn(new Usuario(1, "123123123", "correo@correo.com"
            , "nombres", "apellidos", null, null, null, null, null, null, null));        

        when(usuarioMapper.usuarioToUsuarioDTO(any()))
            .thenReturn(new UsuarioDTO());            

        ResponseGenericObject<UsuarioDTO> response = usuarioService.actualizarUsuario(1, new Usuario(0, "123123123", "correo@correo.com"
            , "nombres", "apellidos", null, null, null, null, null, null, null));
        assertNotNull(response);
        assertTrue(response.isSuccessfully());
        assertEquals("Usuario actualizado con exito", response.getMsj());
        assertNotNull(response.getObj());        
    }

    @Test
    public void testActualizarUsuarioNoExitosoUsuarioNoExiste(){
        when(usuarioRepository.save(any()))
            .thenReturn(new Usuario(1, "123123123", "correo@correo.com"
            , "nombres", "apellidos", null, null, null, null, null, null, null));

        when(usuarioRepository.findById(any()))
            .thenReturn(Optional.ofNullable(null));           

        ResponseGenericObject<UsuarioDTO> response = usuarioService.actualizarUsuario(1, new Usuario(0, "123123123", "correo@correo.com"
            , "nombres", "apellidos", null, null, null, null, null, null, null));
        assertNotNull(response);
        assertFalse(response.isSuccessfully());
        assertEquals("El usuario con el ID ".concat(1+"").concat(" no existe"), response.getMsj());
        assertNull(response.getObj());        
    }  
    
    @Test
    public void testActualizarUsuarioNoExitosoIdentificacionExisteConOtroUsuario(){
        when(usuarioRepository.save(any()))
            .thenReturn(new Usuario(1, "123123123", "correo@correo.com"
            , "nombres", "apellidos", null, null, null, null, null, null, null));

        when(usuarioRepository.findById(any()))
            .thenReturn(Optional.of(new Usuario()));            

        when(usuarioRepository.findByIdentificacionUsuario("123123123"))
            .thenReturn(new Usuario(2, "123123123", "correo@correo.com"
            , "nombres", "apellidos", null, null, null, null, null, null, null));          

        ResponseGenericObject<UsuarioDTO> response = usuarioService.actualizarUsuario(1, new Usuario(0, "123123123", "correo@correo.com"
            , "nombres", "apellidos", null, null, null, null, null, null, null));
        assertNotNull(response);
        assertFalse(response.isSuccessfully());
        assertEquals("Ya existe un usuario con la identificacion 123123123", response.getMsj());
        assertNull(response.getObj());        
    }  
    
    @Test
    public void testEliminarUsuarioExitoso(){
        when(usuarioRepository.findById(1))
            .thenReturn(Optional.of(new Usuario(1, "123123123", "correo@correo.com"
            , "nombres", "apellidos", null, null, null, null, null, null, null)));                  

        when(usuarioMapper.usuarioToUsuarioDTO(any()))
            .thenReturn(new UsuarioDTO());            

        ResponseGenericObject<UsuarioDTO> response = usuarioService.eliminarUsuarioPorId(1);
        assertNotNull(response);
        assertTrue(response.isSuccessfully());
        assertEquals("Usuario eliminado con exito", response.getMsj());
        assertNotNull(response.getObj());
    }

    @Test
    public void testEliminarUsuarioNoExitoso(){
        when(usuarioRepository.findById(1))
            .thenReturn(Optional.ofNullable(null));         

        ResponseGenericObject<UsuarioDTO> response = usuarioService.eliminarUsuarioPorId(1);
        assertNotNull(response);
        assertFalse(response.isSuccessfully());
        assertEquals("El usuario con el ID ".concat(1+"").concat(" no existe"), response.getMsj());
        assertNull(response.getObj());
    }    

}
