package com.bancolombia.arka_javadevops.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bancolombia.arka_javadevops.models.Categoria;
import com.bancolombia.arka_javadevops.models.Producto;


@Repository
public interface ProductoRepository extends CrudRepository<Producto, Integer>{

    Producto findByReferenciaProducto(String referenciaProducto);

    @Query("select p from Producto p where p.nombreProducto like %?1% or p.descripcionProducto like %?1%")
    List<Producto> productosNombreDescripcion(String texto);

    @Query("select p from Producto p order by p.nombreProducto asc")
    List<Producto> productosOrdenadosAsc();

    @Query("select p from Producto p where p.precioProducto between ?1 and ?2 order by p.precioProducto asc")
    List<Producto> productosPorRangoPrecio(int precioMinimo, int precioMaximo);

    //Búsqueda de productos por categoría
    List<Producto> findByCategoria(Categoria categoria);

    
}
