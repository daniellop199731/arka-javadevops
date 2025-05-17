package com.bancolombia.arka_javadevops.mackmvc;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.bancolombia.arka_javadevops.DTO.UsuarioDTO;
import com.bancolombia.arka_javadevops.controllers.UsuarioController;
import com.bancolombia.arka_javadevops.models.MetodoPago;
import com.bancolombia.arka_javadevops.models.Perfil;
import com.bancolombia.arka_javadevops.models.Usuario;
import com.bancolombia.arka_javadevops.services.UsuarioService;
import com.bancolombia.arka_javadevops.utils.ResponseGenericObject;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(UsuarioController.class)
public class UsuarioControllerMvcTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    UsuarioService usuarioService;

    @Test
    void testObtenerUsuariosConRegistros() throws Exception{
        when(usuarioService.obtenerUsuarios()) 
            .thenReturn(new ResponseGenericObject<List<UsuarioDTO>>(true, "msj", List.of(new UsuarioDTO())));
        this.mockMvc.perform(get("/usuarios")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testObtenerUsuariosConRegistrosSinRegistros() throws Exception{
        when(usuarioService.obtenerUsuarios()) 
            .thenReturn(new ResponseGenericObject<List<UsuarioDTO>>(false, "msj", null));
        this.mockMvc.perform(get("/usuarios")).andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    void testObtenerUsuarioPorIdExitoso() throws Exception{
        when(usuarioService.obtenerUsuarioPorId(1))
            .thenReturn(new ResponseGenericObject<UsuarioDTO>(true, "msj", new UsuarioDTO()));
        MvcResult result =
            this.mockMvc.perform(get("/usuarios/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        assertFalse(result.getResponse().getContentAsString().isEmpty());        
    }

    @Test
    void testObtenerUsuarioPorIdNoExitoso() throws Exception{
        when(usuarioService.obtenerUsuarioPorId(1))
            .thenReturn(new ResponseGenericObject<UsuarioDTO>(false, "msj", null));
        MvcResult result =
            this.mockMvc.perform(get("/usuarios/1"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().isEmpty());        
    }    

    @Test
    void testObtenerUsuariosPorNombreConRegistros() throws Exception{
        when(usuarioService.obtenerUsuariosPorNombres("nombres"))
            .thenReturn(new ResponseGenericObject<List<UsuarioDTO>>(
                true, "msj"
                , List.of(
                    new UsuarioDTO("123", "correo@correo.com", "nombres"
                    , "apellidos", null))
            ));
        MvcResult result = 
            this.mockMvc.perform(get("/usuarios/busquedaPorNombre?nombreUsuario=nombres"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        assertFalse(result.getResponse().getContentAsString().isEmpty());
    }

    @Test
    void testObtenerUsuariosPorNombreSinRegistros() throws Exception{
        when(usuarioService.obtenerUsuariosPorNombres("nombres"))
            .thenReturn(new ResponseGenericObject<List<UsuarioDTO>>(
                false, "msj", null
            ));
        MvcResult result = 
            this.mockMvc.perform(get("/usuarios/busquedaPorNombre?nombreUsuario=nombres"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().isEmpty());
    }
    
    @Test
    void testobtenerUsuariosPorOrdenNombresConRegistros() throws Exception{
        when(usuarioService.obtenerUsuariosPorOrdenNombres())
            .thenReturn(new ResponseGenericObject<List<UsuarioDTO>>(
                true, "msj"
                , List.of(
                    new UsuarioDTO("123", "correo", "nombres"
                    , "apellidos", null))
            ));
        MvcResult result = 
            this.mockMvc.perform(get("/usuarios/obtenerUsuariosPorOrdenNombres"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        assertFalse(result.getResponse().getContentAsString().isEmpty());
    }  
    
    @Test
    void testobtenerUsuariosPorOrdenNombresSinRegistros() throws Exception{
        when(usuarioService.obtenerUsuariosPorOrdenNombres())
            .thenReturn(new ResponseGenericObject<List<UsuarioDTO>>(
                false, "msj", null
            ));
        MvcResult result = 
            this.mockMvc.perform(get("/usuarios/obtenerUsuariosPorOrdenNombres"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().isEmpty());
    }    

    @Test 
    void testObtenerUsuarioPorIdentificacionExitoso() throws Exception{
        when(usuarioService.obtenerUsuarioPorIdentificacion("123"))
            .thenReturn(new ResponseGenericObject<UsuarioDTO>(
                true, "msj", new UsuarioDTO("123", "correo", "nombres"
                    , "apellidos", null)
            ));
        MvcResult result =
        this.mockMvc.perform(get("/usuarios/busquedaPorIdentificacion?identificacionUsuario=123"))
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn();
        assertFalse(result.getResponse().getContentAsString().isEmpty());
    }

    @Test 
    void testObtenerUsuarioPorIdentificacionNoExitoso() throws Exception{
        when(usuarioService.obtenerUsuarioPorIdentificacion("123"))
            .thenReturn(new ResponseGenericObject<UsuarioDTO>(
                false, "msj", null
            ));
        MvcResult result =
        this.mockMvc.perform(get("/usuarios/busquedaPorIdentificacion?identificacionUsuario=123"))
            .andDo(print())
            .andExpect(status().isNotFound())
            .andReturn();
        assertTrue(result.getResponse().getContentAsString().isEmpty());
    }    

    @Test
    void testCrearNuevoExitoso() throws Exception{
        when(usuarioService.crearNuevoUsuario(any()))
            .thenReturn(new ResponseGenericObject<UsuarioDTO>(
                true, "msj"
                , new UsuarioDTO("123123123", "correo@correo.com", "nombres"
                    , "apellidos", null)
            ));

        String requestBody = new ObjectMapper().writeValueAsString(
            new Usuario(0, "123123123", "correo@correo.com", "nombres", "apellidos"
            , "direccion", "contrasenna", null, new Perfil(1, "perfil", null)
            , null, null, null)
        );
            
        MvcResult result =
        this.mockMvc.perform(post("/usuarios/crearNuevo")
            .contentType(MediaType.APPLICATION_JSON).content(requestBody))
            .andDo(print())
            .andExpect(status().isCreated())
            .andReturn();

        assertFalse(result.getResponse().getContentAsString().isEmpty());
    }

    @Test
    void testCrearNuevoNoExitoso() throws Exception{
        when(usuarioService.crearNuevoUsuario(any()))
            .thenReturn(new ResponseGenericObject<UsuarioDTO>(
                false, "msj", null
            ));

        String requestBody = new ObjectMapper().writeValueAsString(
            new Usuario(0, "123123123", "correo@correo.com", "nombres", "apellidos"
            , "direccion", "contrasenna", null, new Perfil(1, "perfil", null)
            , null, null, null)
        );
            
        MvcResult result =
        this.mockMvc.perform(post("/usuarios/crearNuevo")
            .contentType(MediaType.APPLICATION_JSON).content(requestBody))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andReturn();

        assertTrue(result.getResponse().getContentAsString().isEmpty());
    }    

    @Test
    void testActualizarExitoso() throws Exception{
        when(usuarioService.actualizarUsuario(1, new Usuario(1, "123123123", "correo@correo.com", "nombres", "apellidos"
            , "direccion", "contrasenna", null, new Perfil(1, "perfil", null)
            , null, null, null) ))
            .thenReturn(new ResponseGenericObject<UsuarioDTO>(
                true, "msj", new UsuarioDTO("123123123", "correo@correo.com", "nombres"
                    , "apellidos", null)
            ));

        String requestBody = new ObjectMapper().writeValueAsString(
            new Usuario(1, "123123123", "correo@correo.com", "nombres", "apellidos"
            , "direccion", "contrasenna", null, new Perfil(1, "perfil", null)
            , null, null, null)            
        );

        MvcResult result = 
        this.mockMvc.perform(put("/usuarios/actualizarUsuario/1")
            .contentType(MediaType.APPLICATION_JSON).content(requestBody))
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn();
        assertFalse(result.getResponse().getContentAsString().isEmpty());
    }

    @Test
    void testActualizarNoExitoso() throws Exception{
        when(usuarioService.actualizarUsuario(1, new Usuario(1, "123123123", "correo@correo.com", "nombres", "apellidos"
            , "direccion", "contrasenna", null, new Perfil(1, "perfil", null)
            , null, null, null) ))
            .thenReturn(new ResponseGenericObject<UsuarioDTO>(
                false, "msj", null
            ));

        String requestBody = new ObjectMapper().writeValueAsString(
            new Usuario(1, "123123123", "correo@correo.com", "nombres", "apellidos"
            , "direccion", "contrasenna", null, new Perfil(1, "perfil", null)
            , null, null, null)            
        );

        MvcResult result = 
        this.mockMvc.perform(put("/usuarios/actualizarUsuario/1")
            .contentType(MediaType.APPLICATION_JSON).content(requestBody))
            .andDo(print())
            .andExpect(status().isConflict())
            .andReturn();
        assertTrue(result.getResponse().getContentAsString().isEmpty());
    }  
    
    @Test
    void testEliminarUsuarioExitoso() throws Exception {
        when(usuarioService.eliminarUsuarioPorId(1))
            .thenReturn(new ResponseGenericObject<UsuarioDTO>(
                true, "msj", new UsuarioDTO("123123123", "correo@correo.com", "nombres"
                    , "apellidos", null)
            ));

        MvcResult result = 
        this.mockMvc.perform(delete("/usuarios/eliminar/1"))
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn();
        assertFalse(result.getResponse().getContentAsString().isEmpty());
    }

    @Test
    void testEliminarUsuarioNoExitoso() throws Exception {
        when(usuarioService.eliminarUsuarioPorId(1))
            .thenReturn(new ResponseGenericObject<UsuarioDTO>(
                false, "msj", null
            ));

        MvcResult result = 
        this.mockMvc.perform(delete("/usuarios/eliminar/1"))
            .andDo(print())
            .andExpect(status().isNotFound())
            .andReturn();
        assertTrue(result.getResponse().getContentAsString().isEmpty());
    }    
}
