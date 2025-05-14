package com.bancolombia.arka_javadevops.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bancolombia.arka_javadevops.models.MetodoPago;
import com.bancolombia.arka_javadevops.repositories.MetodoPagoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MetodoPagoService {

    private final MetodoPagoRepository metodoPagoRepository;

    public List<MetodoPago> obtenerMetodosPago(){ 
        return (List<MetodoPago>) metodoPagoRepository.findAll();      
    }

    public MetodoPago obtenerMetodoPagoPorId(int idMetodoPago){
        Optional<MetodoPago> metodoPago = metodoPagoRepository.findById(idMetodoPago);
        if(metodoPago.isPresent()){
            return metodoPago.get();
        }
        return null;
        
    }

    public MetodoPago crearNuevo(MetodoPago metodoPago){
        List<MetodoPago> metodoPagos = metodoPagoRepository.findByNombreMetodoPago(metodoPago.getNombreMetodoPago());
        if(metodoPagos.size() > 0){
            return null;
        }
        return metodoPagoRepository.save(metodoPago);
    }

}
