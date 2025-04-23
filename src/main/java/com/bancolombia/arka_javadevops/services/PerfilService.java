package com.bancolombia.arka_javadevops.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bancolombia.arka_javadevops.models.Perfil;
import com.bancolombia.arka_javadevops.repositories.PerfilRepository;

@Service //Denota que la clase forma parte de la capa de servicion como un bean de Spring
public class PerfilService {

    @Autowired  //Indica a Spring que busque una implementacion para esta interfaz
                //Como PerfilRepository extiende de CrudRepository y en esta clase
                //solo se usan metodos de CrudRepository no es necesario tener una clase que 
                //implemente PerfilRepository
    private final PerfilRepository perfilRepository;

    public PerfilService(PerfilRepository perfilRepository) {
        this.perfilRepository = perfilRepository;
    }

    public List<Perfil> obtenerPerfiles(){
        return (List<Perfil>) perfilRepository.findAll();
    }

    public Optional<Perfil> obtenerPerfilPorId(int idPerfil){
        return perfilRepository.findById(idPerfil);
    }

    public Perfil creaPerfil(Perfil perfil){
        return perfilRepository.save(perfil);
    }

    public void eliminarPerfil(int idPerfil){
        perfilRepository.deleteById(idPerfil);
    }

    

}
