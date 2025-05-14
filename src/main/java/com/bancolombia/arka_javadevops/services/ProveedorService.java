package com.bancolombia.arka_javadevops.services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.bancolombia.arka_javadevops.models.Proveedor;
import com.bancolombia.arka_javadevops.repositories.ProveedorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProveedorService {

    private final ProveedorRepository proveedorRepository;

    public List<Proveedor> obtenerProveedores(){
        return (List<Proveedor>)proveedorRepository.findAll();
    }

    public Proveedor obtenerProveedorPorIdentificacion(String identificacionProveedor){
        Proveedor proveedor = proveedorRepository.findByIdentificacionProveedor(identificacionProveedor);
        if(proveedor == null){
            return null;
        }
        return proveedor;
    }

    public Proveedor crearNuevo(Proveedor proveedor){
        Proveedor proveedorNuevo = this.obtenerProveedorPorIdentificacion(proveedor.getIdentificacionProveedor());
        if(proveedorNuevo != null){
            return null;
        }
        return proveedorRepository.save(proveedor);
    }

    public Proveedor actualizar(int idProveedor, Proveedor proveedor){
        Optional<Proveedor> provedorEncontrado = proveedorRepository.findById(idProveedor);
        if(provedorEncontrado.isPresent()){            
            /**
             * POSIBLE LOGICA ANTES DE ACTUALIZAR EN BASE DE DATOS, AQUI
            */
            if(provedorEncontrado.isPresent() && 
                ((provedorEncontrado.isPresent() ? provedorEncontrado.get().getIdProveedor():0) 
                    != 
                    proveedor.getIdProveedor())){
                    return null;
            }                  
            /**/ 
            return proveedorRepository.save(proveedor);              
        } 
        return null;
    }
    
}
