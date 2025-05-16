package com.bancolombia.arka_javadevops.mackmvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.bancolombia.arka_javadevops.controllers.MetodoPagoController;
import com.bancolombia.arka_javadevops.models.MetodoPago;
import com.bancolombia.arka_javadevops.services.MetodoPagoService;
import com.bancolombia.arka_javadevops.utils.ResponseGenericObject;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MetodoPagoController.class)
public class MetodoPagoControllerMockMvcTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    MetodoPagoService metodoPagoService;

    @Test
    void testObtenerMetodosPagoConRegistros() throws Exception{
        when(metodoPagoService.obtenerMetodosPago())
            .thenReturn(new ResponseGenericObject<List<MetodoPago>>(
                true, "msj", List.of(new MetodoPago())
            ));
        this.mockMvc.perform(get("/usuarios/metodosPago")).andDo(print()).andExpect(status().isOk());
    }  
    
    @Test
    void testObtenerMetodosPagoSinRegistros() throws Exception{
        when(metodoPagoService.obtenerMetodosPago())
            .thenReturn(new ResponseGenericObject<List<MetodoPago>>(
                false, "msj", List.of()
            ));
        this.mockMvc.perform(get("/usuarios/metodosPago")).andDo(print()).andExpect(status().isNotFound());
    }    

    @Test
    void testObtenerMetodoPagoPorIdConRegistro() throws Exception{
        when(metodoPagoService.obtenerMetodoPagoPorId(1))
            .thenReturn(new ResponseGenericObject<MetodoPago>(true, "msj"
            , new MetodoPago(1, "nuevo", null, null)));
        this.mockMvc.perform(get("/usuarios/metodosPago/1")).andDo(print()).andExpect(status().isOk());
    }
    @Test
    void testObtenerMetodoPagoPorIdSinRegistro() throws Exception{
        when(metodoPagoService.obtenerMetodoPagoPorId(1))
            .thenReturn(new ResponseGenericObject<MetodoPago>(true, "msj", null));
        this.mockMvc.perform(get("/usuarios/metodosPago/1")).andDo(print()).andExpect(status().isNotFound());
    }    

    @Test
    void testCrearNuevoExitoso() throws Exception{
        when(metodoPagoService.crearNuevo(any()))
            .thenReturn(new ResponseGenericObject<MetodoPago>(
                    true, "msj"
                    , new MetodoPago(1000, "nuevo", null, null)));

        String requestBody = new ObjectMapper().writeValueAsString(
            new MetodoPago(0, "nuevo", null, null)
        );
        
        MvcResult result =
        this.mockMvc.perform(post("/usuarios/metodosPago/crearNuevo")
            .contentType(MediaType.APPLICATION_JSON).content(requestBody))
            .andDo(print())
            .andExpect(status().isCreated())
            .andReturn();
        
        assertFalse(result.getResponse().getContentAsString().isEmpty());
    }

    @Test
    void testCrearNuevoNoExitoso() throws Exception{
        when(metodoPagoService.crearNuevo(any()))
            .thenReturn(new ResponseGenericObject<MetodoPago>(
                    false, "msj", null));
        String requestBody = new ObjectMapper().writeValueAsString(
            new MetodoPago(0, "nuevo", null, null)
        );
        MvcResult result =
        this.mockMvc.perform(post("/usuarios/metodosPago/crearNuevo")
            .contentType(MediaType.APPLICATION_JSON).content(requestBody))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andReturn();
        
        assertFalse(result.getResponse().getContentAsString().isEmpty());
    }    

}