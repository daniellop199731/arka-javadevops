package com.bancolombia.arka_javadevops.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bancolombia.arka_javadevops.models.Perfil;
import com.bancolombia.arka_javadevops.repositories.PerfilRepository;

import lombok.RequiredArgsConstructor;

@Service //Denota que la clase forma parte de la capa de servicion como un bean de Spring
@RequiredArgsConstructor
public class PerfilService {

    private final PerfilRepository perfilRepository;

    public List<Perfil> obtenerPerfiles(){
        return (List<Perfil>) perfilRepository.findAll();
    }

    public Optional<Perfil> obtenerPerfilPorId(int idPerfil){
        return perfilRepository.findById(idPerfil);
    }

    public Perfil creaNuevoPerfil(Perfil perfil){
        return perfilRepository.save(perfil);
    }

    public Perfil actualizarPerfil(int idPerfil, Perfil perfil){
        Optional<Perfil> perfilEncontrado = obtenerPerfilPorId(idPerfil);
        if(perfilEncontrado.isPresent()){
            perfil.setIdPerfil(idPerfil);
            return perfilRepository.save(perfil);
        }
        return null;
    }    

    public boolean eliminarPerfil(int idPerfil){
        Optional<Perfil> perfilEncontrado = obtenerPerfilPorId(idPerfil);
        if(perfilEncontrado.isPresent()){
            perfilRepository.deleteById(idPerfil);
            return true;
        }
        return false;
    }



    

}
