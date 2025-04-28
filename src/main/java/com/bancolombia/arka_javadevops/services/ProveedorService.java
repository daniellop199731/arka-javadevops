package com.bancolombia.arka_javadevops.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bancolombia.arka_javadevops.models.Proveedor;
import com.bancolombia.arka_javadevops.repositories.ProveedorRepository;
import com.bancolombia.arka_javadevops.utils.ResponseObject;

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    private static ResponseObject rObj;

    public ProveedorService() {
    }

    public ResponseObject obtenerProveedores(){
        rObj = new ResponseObject();
        List<Proveedor> proveedores = (List<Proveedor>)proveedorRepository.findAll();
        if(!proveedores.isEmpty()){
            rObj.setMsj("Consulta ejecutada con exito");
            rObj.setAsSuccessfully();
            rObj.setObj(proveedores);
        } else {
            rObj.setMsj("No hay proveedores registrados");
        }
        return rObj;
    }

    public ResponseObject obtenerProveedorPorIdentificacion(String identificacionProveedor){
        rObj = new ResponseObject();
        Proveedor proveedor = proveedorRepository.findByIdentificacionProveedor(identificacionProveedor);
        if(proveedor != null){
            rObj.setMsj("Proveedor encontrado");
            rObj.setAsNotSuccessfully();
            rObj.setObj(proveedor);
        } else {
            rObj.setMsj("No existe el proveedor con identificacion: ".concat(identificacionProveedor));
        }
        return rObj;
    }

    public ResponseObject crearNuevo(Proveedor proveedor){
        rObj = new ResponseObject();
        Proveedor proveedorNuevo = (Proveedor) this.obtenerProveedorPorIdentificacion(proveedor.getIdentificacionProveedor()).getObj();
        if(proveedorNuevo != null){
            rObj.setMsj("El proveedor con identificacion ".concat(proveedor.getIdentificacionProveedor()
                .concat(" ya existe")));
        } else {
            rObj.setObj(proveedorRepository.save(proveedor));
            rObj.setMsj("Proveedor guardado con éxito");
            rObj.setAsSuccessfully();
        }
        return rObj;
    }

    public ResponseObject actualizar(Proveedor proveedor){
        rObj = new ResponseObject();
        Proveedor proveedorEncontrado = (Proveedor) this.obtenerProveedorPorIdentificacion(proveedor.getIdentificacionProveedor()).getObj();
        if(proveedorEncontrado != null && 
            ((proveedorEncontrado != null ? proveedorEncontrado.getIdProveedor():0) 
                != 
                proveedor.getIdProveedor())){
            rObj.setMsj("El proveedor con identificacion ".concat(proveedor.getIdentificacionProveedor()
                .concat(" ya existe")));
        } else {
            rObj.setObj(proveedorRepository.save(proveedor));
            rObj.setMsj("Proveedor actualizado con éxito");
            rObj.setAsSuccessfully();            
        }
        return rObj;
    }
    
}
