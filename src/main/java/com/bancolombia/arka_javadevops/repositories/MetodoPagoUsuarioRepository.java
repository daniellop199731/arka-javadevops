package com.bancolombia.arka_javadevops.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bancolombia.arka_javadevops.models.MetodoPagoUsuario;
import com.bancolombia.arka_javadevops.models.Usuario;

@Repository
public interface MetodoPagoUsuarioRepository extends CrudRepository<MetodoPagoUsuario, Integer> {

    List<MetodoPagoUsuario> findByUsuarioMetodoPago(Usuario usuario);

}
