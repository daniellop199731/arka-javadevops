package com.bancolombia.arka_javadevops.controllers;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PruebaControlador {

    @Autowired
    private MainController mainController;

    @Test
    public void probarMainController(){
        assertNotNull(mainController);
    }

}
