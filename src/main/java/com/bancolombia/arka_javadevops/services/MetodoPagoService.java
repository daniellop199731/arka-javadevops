package com.bancolombia.arka_javadevops.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bancolombia.arka_javadevops.models.MetodoPago;
import com.bancolombia.arka_javadevops.repositories.MetodoPagoRepository;
import com.bancolombia.arka_javadevops.utils.ResponseObject;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MetodoPagoService {

    private final MetodoPagoRepository metodoPagoRepository;

    private static ResponseObject rObj;

    public ResponseObject obtenerMetodosPago(){
        rObj = new ResponseObject();
        List<MetodoPago> metodosPago = (List<MetodoPago>) metodoPagoRepository.findAll();
        if(!metodosPago.isEmpty()){
            rObj.setMsj("Consulta ejecutada con exito");
            rObj.setObj(metodosPago);
            rObj.setAsSuccessfully();  
            return rObj;        
        }

        rObj.setMsj("No hay metodos de pago creados");            
        rObj.setAsSuccessfully();        
        return rObj;
    }

    public ResponseObject obtenerMetodoPagoPorId(int idMetodoPago){
        rObj = new ResponseObject();
        Optional<MetodoPago> metodoPagoEncontrado = metodoPagoRepository.findById(idMetodoPago);
        if(metodoPagoEncontrado.isPresent()){
            rObj.setAsSuccessfully();
            rObj.setMsj("Metodo de pago encontrado");
            rObj.setObj(metodoPagoEncontrado);
        }
        rObj.setMsj("El metodo de pago con id ".concat(idMetodoPago+"").concat(" no existe"));
        return rObj;
    }

    public ResponseObject crearNuevo(MetodoPago metodoPago){
        rObj = new ResponseObject();
        List<MetodoPago> metodoPagos = metodoPagoRepository.findByNombreMetodoPago(metodoPago.getNombreMetodoPago());
        if(metodoPagos.isEmpty()){
            rObj.setObj(metodoPagoRepository.save(metodoPago));
            rObj.setMsj("Metodo de pago guardado con éxito");
            rObj.setAsSuccessfully();            
        }
        rObj.setMsj("El metodo de pago ".concat(metodoPago.getNombreMetodoPago())
            .concat(" ya existe"));        
        return rObj;
    }

}
