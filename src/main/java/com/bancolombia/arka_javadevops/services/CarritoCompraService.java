package com.bancolombia.arka_javadevops.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bancolombia.arka_javadevops.models.CarritoCompra;
import com.bancolombia.arka_javadevops.models.EstadoDespacho;
import com.bancolombia.arka_javadevops.models.Usuario;
import com.bancolombia.arka_javadevops.repositories.CarritoCompraRepository;
import com.bancolombia.arka_javadevops.utils.ResponseObject;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CarritoCompraService {

    private final CarritoCompraRepository carritoCompraRepository;
    private final UsuarioService usuarioService;

    private static ResponseObject rObj;

    public ResponseObject crearNuevo(int idUsuario){
        rObj = usuarioService.obtenerUsuarioPorId(idUsuario);
        if(rObj.getSuccessfully()){
            CarritoCompra carritoCompra = new CarritoCompra();
            EstadoDespacho estadoDespacho = new EstadoDespacho();
            estadoDespacho.setIdEstadoDespacho(1);
            carritoCompra.setUsuarioCarritoCompra((Usuario) rObj.getObj());            
            carritoCompra.setEstadoDespacho(estadoDespacho);
            rObj.setAsSuccessfully();
            rObj.setMsj("Carrito de compras creado");
            rObj.setObj(carritoCompraRepository.save(carritoCompra));
            return rObj;
        }
        return rObj;
    }

    public ResponseObject obtenerCarritoPorId(int idCarritoCompra){
        rObj = new ResponseObject();
        Optional<CarritoCompra> carritoCompraEncontrado = carritoCompraRepository.findById(idCarritoCompra);
        if(carritoCompraEncontrado.isPresent()){
            rObj.setAsSuccessfully();
            rObj.setMsj("Carrito de compra encontrado");
            rObj.setObj(carritoCompraEncontrado.get());
            return rObj;
        }
        rObj.setMsj("El carrito de compra con id ".concat(idCarritoCompra+"").concat(" no existe"));
        return rObj;
    }

    public ResponseObject carritoActual(int idUsuario){
        rObj = new ResponseObject();
        List<CarritoCompra> carritosCompra = carritoCompraRepository.findCarritoActual(idUsuario);
        if(carritosCompra.size() == 0){
            rObj.setMsj("No hay carrito de compras actual");
            rObj.setObj("");
            return rObj;
        }

        rObj.setAsSuccessfully();
        rObj.setMsj("Carrito actual encontrado");
        rObj.setObj(carritosCompra.get(0));
        return rObj;    
    }

}
