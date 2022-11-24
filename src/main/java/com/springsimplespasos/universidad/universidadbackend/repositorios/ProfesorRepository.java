package com.springsimplespasos.universidad.universidadbackend.repositorios;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Profesor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("repositoryProfesor")
public interface ProfesorRepository extends PersonaReposity {

    @Query("select  c.profesores from Carrera c where c.nombre = ?1")
    Iterable<Profesor> findProfesoresByCarrera(String carrera);

    @Query(value = "select p from Profesor p")
    Iterable<Profesor> buscarTodos();
}
