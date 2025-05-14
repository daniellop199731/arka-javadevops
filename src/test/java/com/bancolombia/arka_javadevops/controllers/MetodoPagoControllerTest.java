package com.bancolombia.arka_javadevops.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bancolombia.arka_javadevops.models.MetodoPago;
import com.bancolombia.arka_javadevops.services.MetodoPagoService;
import com.bancolombia.arka_javadevops.utils.ResponseObject;

public class MetodoPagoControllerTest {

    //Doble de pruebas
    @Mock
    MetodoPagoService metodoPagoService;

    //Sujeto de pruebas
    @InjectMocks //Injecte todos los mocks que esten en el contexto del sujeto de pruebas
    MetodoPagoController metodoPagoController;

    /*Metodo que se ejecuta antes de ejecutar cualquier metodo de pruebas*/
    @BeforeEach
    public void setup(){
        //MockitoAnnotations.openMocks(this);
        //Se le indica a MOckito que procese las anotaciones
        //MockitoAnnotations.openMocks(MetodoPagoControllerTest.class);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testReturningOrders() {
       when(metodoPagoController.obtenerMetodosPago())
             .thenReturn(ResponseEntity.of(Optional.ofNullable(List.of(new MetodoPago(1, "a", null, null)))));
       ResponseEntity<List<MetodoPago>> responseEntity = metodoPagoController.obtenerMetodosPago();
       assertEquals(org.springframework.http.HttpStatus.OK, responseEntity.getStatusCode());
    }

}
