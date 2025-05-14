package com.bancolombia.arka_javadevops.services;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bancolombia.arka_javadevops.DTO.CarritoCompraDTO;
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

    public CarritoCompra crearNuevo(int idUsuario){
        Usuario usuario = usuarioService.obtenerUsuarioPorIdWitOutDto(idUsuario);
        if(usuario != null){
            CarritoCompra carritoCompra = new CarritoCompra();
            EstadoDespacho estadoDespacho = new EstadoDespacho();
            estadoDespacho.setIdEstadoDespacho(1);
            carritoCompra.setUsuarioCarritoCompra(usuario);            
            carritoCompra.setEstadoDespacho(estadoDespacho);
            return carritoCompraRepository.save(carritoCompra);
        }
        return null;
    }

    public CarritoCompra obtenerCarritoPorId(int idCarritoCompra){
        rObj = new ResponseObject();
        Optional<CarritoCompra> carritoCompraEncontrado = carritoCompraRepository.findById(idCarritoCompra);
        if(carritoCompraEncontrado.isPresent()){
            return carritoCompraEncontrado.get();
        }
        return null;
    }

    public List<CarritoCompraDTO> carritosPorUsuario(int idUsuario){
        if(usuarioService.obtenerUsuarioPorIdWitOutDto(idUsuario) == null){
            return null;
        }
        List<CarritoCompra> carritoCompras = carritoCompraRepository.findByUsuarioCarritoCompra((Usuario) rObj.getObj());
        return carritoCompraMapper.toDto(carritoCompras);
    }

    public CarritoCompra carritoActual(int idUsuario){
        List<CarritoCompra> carritosCompra = carritoCompraRepository.findCarritoActual(idUsuario);
        if(carritosCompra.size() == 0){
            return null;
        }
        return carritosCompra.get(0);    
    }

    public List<CarritoCompraDTO> carritosAbandonados(){
        return carritoCompraMapper.toDto(carritoCompraRepository.carritosAbandonados());
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
