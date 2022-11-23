package com.springsimplespasos.universidad.universidadbackend.repositorios;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Persona;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("repositoryAlumnos")
public interface AlumnoRepository extends PersonaReposity{
    @Query("select a from Alumno a where a.carrera.nombre = ?1")
    Iterable<Persona> buscarAlumnoPorNombreCarrera(String nombre);

    @Query(value = "select a from Alumno a")
    Iterable<Persona> buscarTodos();
}

