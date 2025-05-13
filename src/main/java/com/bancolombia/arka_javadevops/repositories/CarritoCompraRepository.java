package com.bancolombia.arka_javadevops.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bancolombia.arka_javadevops.models.CarritoCompra;

@Repository
public interface CarritoCompraRepository extends CrudRepository<CarritoCompra, Integer> {

    @Query(value = "select * from carritosCompra C where idUsuarioCarritoCompra = ?1 and idEstadoDespachoCarritoCompra = 1 order by fechaCreacionCarritoCompra desc", nativeQuery = true)
    List<CarritoCompra> findCarritoActual(int idUsuario);

}
