package com.bancolombia.arka_javadevops.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bancolombia.arka_javadevops.models.MetodoPago;
import com.bancolombia.arka_javadevops.repositories.MetodoPagoRepository;
import com.bancolombia.arka_javadevops.utils.ResponseGenericObject;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MetodoPagoService {

    private final MetodoPagoRepository metodoPagoRepository;

    private static ResponseGenericObject<List<MetodoPago>> rgObjList;
    private static ResponseGenericObject<MetodoPago> rgObj;

    public ResponseGenericObject<List<MetodoPago>> obtenerMetodosPago(){
        rgObjList = new ResponseGenericObject<>();
        List<MetodoPago> metodosPago = (List<MetodoPago>) metodoPagoRepository.findAll();
        if(!metodosPago.isEmpty()){
            rgObjList.setAsSuccessfully("Consulta ejecutada con exito", metodosPago);
            return rgObjList;        
        }
        rgObjList.setAsNotSuccessfully("No hay metodos de pago creados", HttpStatus.NOT_FOUND);   
        return rgObjList;
    }

    public ResponseGenericObject<MetodoPago> obtenerMetodoPagoPorId(int idMetodoPago){
        rgObj = new ResponseGenericObject<>();
        Optional<MetodoPago> metodoPagoEncontrado = metodoPagoRepository.findById(idMetodoPago);
        if(metodoPagoEncontrado.isPresent()){
            rgObj.setAsSuccessfully("Metodo de pago encontrado", metodoPagoEncontrado.get());
            return rgObj;
        }
        rgObj.setAsNotSuccessfully("El metodo de pago con id ".concat(idMetodoPago+"").concat(" no existe")
            , HttpStatus.NOT_FOUND);
        return rgObj;
    }

    public ResponseGenericObject<MetodoPago> crearNuevo(MetodoPago metodoPago){
        rgObj = new ResponseGenericObject<>();
        List<MetodoPago> metodoPagos = metodoPagoRepository.findByNombreMetodoPago(metodoPago.getNombreMetodoPago());
        if(metodoPagos.isEmpty()){            
            rgObj.setAsSuccessfully("Metodo de pago guardado con éxito", HttpStatus.CREATED, metodoPagoRepository.save(metodoPago));
            return rgObj;       
        }       
        rgObj.setAsNotSuccessfully("El metodo de pago ".concat(metodoPago.getNombreMetodoPago())
            .concat(" ya existe"), HttpStatus.BAD_REQUEST);
        return rgObj;
    }
}
