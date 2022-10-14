package com.springsimplespasos.universidad.universidadbackend.servicios.contratos;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Alumno;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Carrera;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Persona;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CarreraDAO  {

    Optional<Carrera> findById(Integer id);
    Carrera save(Carrera carrera);
    Iterable<Carrera> findAll();
    void deleteId(Integer id);

}

