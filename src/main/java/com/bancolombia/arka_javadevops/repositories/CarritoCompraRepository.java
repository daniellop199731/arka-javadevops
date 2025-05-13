package com.bancolombia.arka_javadevops.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bancolombia.arka_javadevops.models.CarritoCompra;
import com.bancolombia.arka_javadevops.models.Usuario;


@Repository
public interface CarritoCompraRepository extends CrudRepository<CarritoCompra, Integer> {

    List<CarritoCompra> findByUsuarioCarritoCompra(Usuario usuarioCarritoCompra);

    @Query(value = "select * from carritosCompra C where idUsuarioCarritoCompra = ?1 and idEstadoDespachoCarritoCompra = 1 order by fechaCreacionCarritoCompra desc", nativeQuery = true)
    List<CarritoCompra> findCarritoActual(int idUsuario);

    @Query(value = "select * from carritosCompra C where idEstadoDespachoCarritoCompra = 100", nativeQuery = true)
    List<CarritoCompra> carritosAbandonados();

    List<CarritoCompra> findByFechaCreacionCarritoCompraBetween(Date dateStar, Date dateEnd);

}
