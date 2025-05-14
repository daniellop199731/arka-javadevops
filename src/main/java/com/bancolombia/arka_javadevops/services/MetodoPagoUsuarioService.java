package com.bancolombia.arka_javadevops.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bancolombia.arka_javadevops.models.MetodoPago;
import com.bancolombia.arka_javadevops.models.MetodoPagoUsuario;
import com.bancolombia.arka_javadevops.models.Usuario;
import com.bancolombia.arka_javadevops.repositories.MetodoPagoUsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MetodoPagoUsuarioService {

    private final MetodoPagoUsuarioRepository metodoPagoUsuarioRepository;
    private final UsuarioService usuarioService;
    private final MetodoPagoService metodoPagoService;

    public List<MetodoPagoUsuario> obtenerMetodosPagosUsuario(int idUsuario){
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(idUsuario);
        List<MetodoPagoUsuario> metodosPagoUsuario = metodoPagoUsuarioRepository.findByUsuarioMetodoPago(usuario);
        return metodosPagoUsuario;
    }

    public boolean agregarMetodoPagoUsuario(int idUsuario, int idMetodoPago, double valorCuenta){
        if(usuarioService.obtenerUsuarioPorId(idUsuario) == null){
            return false;
        }
        if(metodoPagoService.obtenerMetodoPagoPorId(idMetodoPago) == null){
            return false;
        }   
        if(valorCuenta <= 0){
            return false;
        }
        
        MetodoPagoUsuario metodoPagoUsuario = new MetodoPagoUsuario();
        Usuario usuario = new Usuario();
        MetodoPago metodoPago = new MetodoPago();

        usuario.setIdUsuario(idUsuario);
        metodoPago.setIdMetodoPago(idMetodoPago);
        metodoPagoUsuario.setUsuarioMetodoPago(usuario);
        metodoPagoUsuario.setMetodoPago(metodoPago);
        metodoPagoUsuario.setValorCuentaMetodoPago(valorCuenta);
        metodoPagoUsuarioRepository.save(metodoPagoUsuario);
        return true;
    }

}
