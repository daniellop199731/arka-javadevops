package com.bancolombia.arka_javadevops.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.bancolombia.arka_javadevops.models.Usuario;
import com.bancolombia.arka_javadevops.repositories.UsuarioRepository;

@ExtendWith(SpringExtension.class)
public class UsuarioServiceTest {

    //Instancia falsa, para simular la funcionalidad real
    @Mock
    private UsuarioRepository usuarioRepository;

    //Inyecta la dependencia en la clase
    @InjectMocks
    private UsuarioService usuarioService;

    //Procesa las anotaciones creadas
    //Para este caso, crea la instancia falsa (proxy) de usuarioRepository
    //luego le inyecta la instancia falsa a usuarioController
    @BeforeEach
    public void setup(){
        org.mockito.MockitoAnnotations.openMocks(this);
    }

    @Test
    public void probarCrearNuevo(){
        when(usuarioRepository.save(any(Usuario.class)))
            .thenReturn(new Usuario());
    }

}
