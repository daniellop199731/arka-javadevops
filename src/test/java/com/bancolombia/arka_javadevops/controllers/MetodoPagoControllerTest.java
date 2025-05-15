package com.bancolombia.arka_javadevops.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import com.bancolombia.arka_javadevops.utils.ResponseGenericObject;

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
    public void testObtenerMetodosPago() {
       when(metodoPagoService.obtenerMetodosPago())
             .thenReturn(
                new ResponseGenericObject<List<MetodoPago>>(
                    true
                    , "msj"
                    , HttpStatus.OK
                    , List.of(
                        new MetodoPago(
                            1
                            , "nuevo"
                            , null, null))));
       ResponseEntity<ResponseGenericObject<List<MetodoPago>>> responseEntity = metodoPagoController.obtenerMetodosPago();
       assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
       assertNotNull(responseEntity.getBody());
       assertEquals(1,  responseEntity.getBody().getObj().size());
       assertEquals(1, responseEntity.getBody().getObj().get(0).getIdMetodoPago());
       assertEquals("nuevo", responseEntity.getBody().getObj().get(0).getNombreMetodoPago());
    }

    @Test
    public void testObtenerMetodoPagoPorId(){
        int idMetodoPago = 1;
        when(metodoPagoService.obtenerMetodoPagoPorId(idMetodoPago))
            .thenReturn(new ResponseGenericObject<MetodoPago>(
                true, "msj", HttpStatus.OK, new MetodoPago(
                    idMetodoPago, "nuevo", null, null
                )
            ));
        ResponseEntity<ResponseGenericObject<MetodoPago>> responseEntity = 
            metodoPagoController.obtenerMetodoPagoPorId(idMetodoPago);
        
        //Valida contedido y respuesta del ResponseEntity
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());

        //Valida contedido de ResponseGenericObject
        assertTrue(responseEntity.getBody().isSuccessfully());
        assertEquals("msj", responseEntity.getBody().getMsj());
        assertNotNull(responseEntity.getBody().getObj());

        //Valida el objeto contenido dentro de ResponseGenericObject
        assertEquals(idMetodoPago, responseEntity.getBody().getObj().getIdMetodoPago());
        assertEquals("nuevo", responseEntity.getBody().getObj().getNombreMetodoPago());
    }

}
