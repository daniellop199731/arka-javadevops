package com.bancolombia.arka_javadevops.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        
    }

}
