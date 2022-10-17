package com.springsimplespasos.universidad.universidadbackend.repositorios;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Persona;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

//No genera un bean de esta clase en particular
@NoRepositoryBean
public interface PersonaReposity extends CrudRepository<Persona, Integer> {

    Optional<Persona> buscarPorNombreYApellido(String nombre, String apellido);
    Optional<Persona> buscarPorDni (String din);
    Iterable<Persona> buscarPersonaPorApellido (String apellido);

}
