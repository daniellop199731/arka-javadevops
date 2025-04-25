package com.bancolombia.arka_javadevops.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bancolombia.arka_javadevops.models.Perfil;

@Repository
public interface PerfilRepository extends CrudRepository<Perfil, Integer>{

  // Aquí se pueden agregar métodos adicionales de consulta personalizada si es necesario
  //Ejemplo
  //List<Perfil> findByNombre(String nombrePerfil);
  
  /*
   * Con solo extender CrudRepository, obtienes automaticamente métodos como:
   * 
   * findById(Long id): para buscar por ID.
   * findAll(): para obtener todos los registros.
   * save(Objeto obj): para guardar o actualizar un producto.
   * deleteById(Long id): para eliminar un producto por su ID. 
   */

}
