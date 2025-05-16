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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.bancolombia.arka_javadevops.models.MetodoPago;
import com.bancolombia.arka_javadevops.repositories.MetodoPagoRepository;
import com.bancolombia.arka_javadevops.services.MetodoPagoService;
import com.bancolombia.arka_javadevops.utils.ResponseGenericObject;

public class MetodoPagoServiceTest {

    @Mock
    MetodoPagoRepository metodoPagoRepository;

    @InjectMocks
    MetodoPagoService metodoPagoService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testObtenerMetodosPagoDevuelveObjetos(){
        /*
            Practicamente lo que se quiere decir es que  findAll() de MetodoPagoRepository me va a devolver lo que yo le 
            indique en thenReturn
        */
        when(metodoPagoRepository.findAll()).thenReturn(List.of(new MetodoPago(1, "Metodo1", null, null)));

        /* 
            Como tal si se ejecuta en realidad obtenerMetodosPago de MetodoPagoService, pero como este depdende de lo que 
            le devuelva findAll() (y va a devolver lo que se le indico), obtenerMetodosPago va a trabajar con ese que se indico
        */
        ResponseGenericObject<List<MetodoPago>> rgObj = metodoPagoService.obtenerMetodosPago();
        //Valida que el ResponseGenericObject que se devuelve no sea null
        assertNotNull(rgObj);
        //Valida que el successfully del ResponseGenericObject que se devuelve sea true (Porque si encontro MetodoPago) 
        assertEquals(true, rgObj.isSuccessfully());
        //Valida el mensaje que devuelve ResponseGenericObject
        assertEquals("Consulta ejecutada con exito", rgObj.getMsj());
        //Valida el objeto T que se devuelve en ResponseGenericObject
        assertNotNull(rgObj.getObj());
        //Valida que la lista si tenga la cantidad de elementos indicados
        assertEquals(1, rgObj.getObj().size());
    }

    @Test
    public void testObtenerMetodosPagoNoDevuelveObjetos(){
        when(metodoPagoRepository.findAll()).thenReturn(List.of());
        ResponseGenericObject<List<MetodoPago>> rgObj = metodoPagoService.obtenerMetodosPago();
        assertNotNull(rgObj);
        assertEquals(false, rgObj.isSuccessfully());
        assertEquals("No hay metodos de pago creados", rgObj.getMsj());
        assertNull(rgObj.getObj());
    }

    @Test
    public void testObtenerMetodoPagoConIdConRegistro(){
        when(metodoPagoRepository.findById(1))
            .thenReturn(Optional.of(new MetodoPago(1, "nuevo", null, null)));
        ResponseGenericObject<MetodoPago> response = metodoPagoService.obtenerMetodoPagoPorId(1);
        assertNotNull(response);
        assertTrue(response.isSuccessfully());
        assertEquals("Metodo de pago encontrado", response.getMsj());
        assertNotNull(response.getObj());
    }

    @Test
    public void testObtenerMetodoPagoConIdSinRegistro(){
        when(metodoPagoRepository.findById(1))
            .thenReturn(Optional.ofNullable(null));
        ResponseGenericObject<MetodoPago> response = metodoPagoService.obtenerMetodoPagoPorId(1);
        assertNotNull(response);
        assertFalse(response.isSuccessfully());
        assertEquals("El metodo de pago con id ".concat(1+"").concat(" no existe"), response.getMsj());
        assertNull(response.getObj());
    }    

    @Test
    public void testCrearNuevoExitoso(){
        when(metodoPagoRepository.save(any()))
            .thenReturn(new MetodoPago(1, "Metodo1", null, null));
        ResponseGenericObject<MetodoPago> response = metodoPagoService.crearNuevo(new MetodoPago(0, "Metodo1", null, null));
        assertNotNull(response);
        assertTrue(response.isSuccessfully());
        assertEquals("Metodo de pago guardado con éxito", response.getMsj());
        assertNotNull(response.getObj());
        assertNotEquals(0, response.getObj().getIdMetodoPago());
        assertEquals("Metodo1", response.getObj().getNombreMetodoPago());
    }

    @Test
    public void testCrearNuevoNoExitoso(){
        when(metodoPagoRepository.save(any()))
            .thenReturn(new MetodoPago(1, "Metodo1", null, null));
        when(metodoPagoRepository.findByNombreMetodoPago("Metodo1"))
            .thenReturn(List.of(new MetodoPago(0, "Metodo1", null, null)));
        ResponseGenericObject<MetodoPago> response = metodoPagoService.crearNuevo(new MetodoPago(0, "Metodo1", null, null));
        assertNotNull(response);
        assertFalse(response.isSuccessfully());
        assertEquals("El metodo de pago ".concat("Metodo1").concat(" ya existe"), response.getMsj());
        assertNull(response.getObj());
    }    

}
