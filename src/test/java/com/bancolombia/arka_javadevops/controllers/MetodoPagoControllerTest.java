package com.bancolombia.arka_javadevops.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bancolombia.arka_javadevops.models.MetodoPago;
import com.bancolombia.arka_javadevops.services.MetodoPagoService;

public class MetodoPagoControllerTest {

    //Doble de pruebas
    @Mock
    MetodoPagoService metodoPagoService;

    //Sujeto de pruebas
    @InjectMocks
    MetodoPagoController metodoPagoController;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testReturningMetodosPago(){
        when(metodoPagoService.obtenerMetodosPago())
            .thenReturn(List.of(new MetodoPago(1, "nuevo", null, null)));
        ResponseEntity<List<MetodoPago>> responseEntity = metodoPagoController.obtenerMetodosPago();

        //Valida el codigo de la respuesa
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        //Valida que el ResponseEntity si tenga algo diferente de null
        assertNotNull(responseEntity.getBody());
        //Como dijimos que se devuelve un solo el elemento de la lista, se valida que solo se 
        //devuelva un elemento
        assertEquals(1, responseEntity.getBody().size());
        //Valida el id del elemento que se devuelve
        assertEquals(1, responseEntity.getBody().get(0).getIdMetodoPago());
        //Valida el nombre del mentodo de pago que se devuelve
        assertEquals("nuevo", responseEntity.getBody().get(0).getNombreMetodoPago());
        //valida que las listas sean nullas
        assertNull(responseEntity.getBody().get(0).getMetodosPagoUsuario());
        assertNull(responseEntity.getBody().get(0).getCarritosCompras());
    }

}
