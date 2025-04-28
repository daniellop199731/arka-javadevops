package com.bancolombia.arka_javadevops.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bancolombia.arka_javadevops.models.Proveedor;


@Repository
public interface ProveedorRepository extends CrudRepository<Proveedor, Integer>{

    Proveedor findByIdentificacionProveedor(String identificacionProveedor);

}
