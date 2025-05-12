package com.bancolombia.arka_javadevops.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bancolombia.arka_javadevops.models.MetodoPago;

@Repository
public interface MetodoPagoRepository extends CrudRepository<MetodoPago, Integer>{

    List<MetodoPago> findByNombreMetodoPago(String nombreMetodoPago);

}
