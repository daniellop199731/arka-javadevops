package com.bancolombia.arka_javadevops.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bancolombia.arka_javadevops.models.MetodoPago;
import com.bancolombia.arka_javadevops.models.MetodoPagoUsuario;
import com.bancolombia.arka_javadevops.models.Usuario;
import com.bancolombia.arka_javadevops.repositories.MetodoPagoUsuarioRepository;
import com.bancolombia.arka_javadevops.utils.ResponseGenericObject;
import com.bancolombia.arka_javadevops.utils.ResponseObject;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MetodoPagoUsuarioService {

    private final MetodoPagoUsuarioRepository metodoPagoUsuarioRepository;
    private final UsuarioService usuarioService;
    private final MetodoPagoService metodoPagoService;

    private static ResponseObject rObj;

    public ResponseObject obtenerMetodosPagosUsuario(int idUsuario){
        rObj = new ResponseObject();
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(idUsuario);
        List<MetodoPagoUsuario> metodosPagoUsuario = metodoPagoUsuarioRepository.findByUsuarioMetodoPago(usuario);
        rObj.setAsSuccessfully();
        rObj.setObj(metodosPagoUsuario);
        if(metodosPagoUsuario.isEmpty()){
            rObj.setMsj("El usuario no tiene metodos de pago");
        } else {
            rObj.setMsj("Consulta ejecutada con exito");
        }
        return rObj;
    }

    public ResponseObject agregarMetodoPagoUsuario(int idUsuario, int idMetodoPago, double valorCuenta){
        rObj = usuarioService.obtenerUsuarioPorId(idUsuario);
        if(!rObj.getSuccessfully()){
            return rObj;
        }
        ResponseGenericObject<MetodoPago> rgObj = metodoPagoService.obtenerMetodoPagoPorId(idMetodoPago);
        if(!rgObj.isSuccessfully()){
            rObj.setSuccessfully(rgObj.isSuccessfully());
            rObj.setMsj(rgObj.getMsj());
            rObj.setObj(rgObj.getObj());
            return rObj;
        }   
        if(valorCuenta <= 0){
            rObj.setAsNotSuccessfully();
            rObj.setMsj("El valor debe ser mayor a cero");
            rObj.setObj("");
            return rObj;
        }
        
        MetodoPagoUsuario metodoPagoUsuario = new MetodoPagoUsuario();
        Usuario usuario = new Usuario();
        MetodoPago metodoPago = new MetodoPago();

        usuario.setIdUsuario(idUsuario);
        metodoPago.setIdMetodoPago(idMetodoPago);
        metodoPagoUsuario.setUsuarioMetodoPago(usuario);
        metodoPagoUsuario.setMetodoPago(metodoPago);
        metodoPagoUsuario.setValorCuentaMetodoPago(valorCuenta);

        rObj.setAsSuccessfully();
        rObj.setMsj("Metodo de pago agregado");
        rObj.setObj(metodoPagoUsuarioRepository.save(metodoPagoUsuario));
        return rObj;
    }

}
