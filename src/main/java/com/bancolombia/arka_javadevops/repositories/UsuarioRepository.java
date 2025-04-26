package com.bancolombia.arka_javadevops.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bancolombia.arka_javadevops.models.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

}
