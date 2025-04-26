package com.bancolombia.arka_javadevops.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bancolombia.arka_javadevops.models.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

    List<Usuario> findByNombresUsuario(String nombresUsuario);

    Usuario findByIdentificacionUsuario(String identificacionUsuario);

    @Query("select u from Usuario u order by nombresUsuario asc")
    List<Usuario> usuariosOrderByNombres();

}
