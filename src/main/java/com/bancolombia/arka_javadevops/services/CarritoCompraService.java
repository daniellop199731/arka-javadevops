package com.bancolombia.arka_javadevops.services;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bancolombia.arka_javadevops.mappers.CarritoCompraMapper;
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
    private final CarritoCompraMapper carritoCompraMapper;

    private static ResponseObject rObj;

    public ResponseObject crearNuevo(int idUsuario){
        rObj = usuarioService.obtenerUsuarioPorIdWitOutDto(idUsuario);
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

    public ResponseObject caarritosPorUsuario(int idUsuario){
        rObj = usuarioService.obtenerUsuarioPorIdWitOutDto(idUsuario);
        if(!rObj.getSuccessfully()){
            return rObj;
        }
        List<CarritoCompra> carritoCompras = carritoCompraRepository.findByUsuarioCarritoCompra((Usuario) rObj.getObj());
        rObj.setAsSuccessfully();
        rObj.setMsj("Carritos encontrados");
        rObj.setObj(carritoCompraMapper.toDto(carritoCompras));
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

    public ResponseObject carritosAbandonados(){
        rObj = new ResponseObject();
        List<CarritoCompra> carritoCompras = carritoCompraRepository.carritosAbandonados();
        rObj.setAsSuccessfully();
        if(carritoCompras.isEmpty()){            
            rObj.setMsj("No hay carritos abandonados");
            rObj.setObj(carritoCompras);
        }
        rObj.setMsj("Consulta ejecutada con exito");
        rObj.setObj(carritoCompraMapper.toDto(carritoCompras));
        return rObj;
    }

    public ResponseObject carritoComprasPorFechas(String minDate, String maxDate){
        rObj = new ResponseObject();
        try{
            List<CarritoCompra> carritoCompras = carritoCompraRepository.findByFechaCreacionCarritoCompraBetween(
                new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(minDate+" 00:00")
                , new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(maxDate+" 11:59"));
            rObj.setAsSuccessfully();
            if(carritoCompras.size() == 0){
                rObj.setMsj("No hay carritos entre el rango de fechas");
                rObj.setObj(carritoCompras);
                return rObj;
            }

            rObj.setMsj("Consulta ejecutada con exito");
            rObj.setObj(carritoCompraMapper.toDto(carritoCompras));

        } catch (Exception ex){
            rObj.setAsNotSuccessfully();
            rObj.setMsj("Error al ejecutar: ".concat(ex.getMessage()));
            rObj.setObj(null);
        }
    
        return rObj;
    }

}
