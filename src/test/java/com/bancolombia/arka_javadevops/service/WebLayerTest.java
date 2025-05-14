package com.bancolombia.arka_javadevops.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import com.bancolombia.arka_javadevops.controllers.MainController;

@WebMvcTest(MainController.class)
public class WebLayerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void regresaMensaje() throws Exception{
    /*this.mockMvc.perform(get("/"))
                   .andDo(print())
                   .andExpect(status().isOk())
                   .andExpect(content().string(containsString("¡Hola desde Spring!")));        

    */
    }

}
