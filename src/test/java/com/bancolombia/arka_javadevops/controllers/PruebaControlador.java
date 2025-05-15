package com.bancolombia.arka_javadevops.controllers;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PruebaControlador {

    @Autowired
    private MainController mainController;

    @Autowired
    private PerfilController perfilController;

    @Autowired
    private UsuarioController usuarioController;

    @Autowired
    private MetodoPagoUsuarioController metodoPagoUsuarioController;

    @Autowired
    private MetodoPagoController metodoPagoController;

    @Autowired 
    private CategoriaController categoriaController;

    @Autowired
    private ProveedorController proveedorController;

    @Autowired
    private ProductoController productoController;

    @Autowired
    private CarritoCompraProductoController carritoCompraProductoController;

    @Autowired
    private CarritoCompraController carritoCompraController;

    @Test
    public void probarControllers(){
        assertNotNull(mainController);
        assertNotNull(perfilController);
        assertNotNull(usuarioController);
        assertNotNull(metodoPagoUsuarioController);
        assertNotNull(metodoPagoController);
        assertNotNull(categoriaController);
        assertNotNull(proveedorController);
        assertNotNull(productoController);
        assertNotNull(carritoCompraProductoController);
        assertNotNull(carritoCompraController);
    }

}
