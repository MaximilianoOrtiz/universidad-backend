package com.springsimplespasos.universidad.universidadbackend.servicios.contratos;


import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Alumno;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Persona;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AlumnoDAO{

    Optional<Persona> findById(Integer id);
    Persona save(Persona persona);
    Iterable<Persona> findAll();
    void deleteId(Integer id);


}
