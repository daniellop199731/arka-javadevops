package com.bancolombia.arka_javadevops.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.bancolombia.arka_javadevops.services.OperacionesAritmeticasSinEstado;

public class OperacionesAritmeticasSinEstadoTest {

    @ParameterizedTest
    @ValueSource(ints = {1,2,3})
    public void probarSuma(int entrada){
        //1: Arrange (Arreglar): Preparar el ambiente para mi prueba unitaria
        OperacionesAritmeticasSinEstado opass = new OperacionesAritmeticasSinEstado();

        //2: Act (Invocar la funcionalidad): Consumo la instancia de la clase a probar con el método que quiero validar
        int result1 = 0;
        result1 = opass.suma(entrada, entrada);

        //3: Assert( verificar) : En este paso validamos que las salidas de la operación son las esperadas.
        assertEquals(entrada+entrada, result1);

    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3})
    public void probarResta(int entrada){
        OperacionesAritmeticasSinEstado opass = new OperacionesAritmeticasSinEstado();
        int result = 0;
        result = opass.resta(entrada, entrada);
        assertEquals(entrada-entrada, result);
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,4})
    public void probarMultiplicacion(int entrada){
        OperacionesAritmeticasSinEstado opass = new OperacionesAritmeticasSinEstado();
        int result = 0;
        result = opass.multiplicacion(entrada, entrada);
        assertEquals(entrada*entrada, result);
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,4})
    public void probarDivision(int entrada){
        OperacionesAritmeticasSinEstado opass = new OperacionesAritmeticasSinEstado();
        int result = 0;
        result = opass.division(entrada, entrada);
        assertEquals(entrada/entrada, result);
    }    
}
