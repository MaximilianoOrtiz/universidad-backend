package com.springsimplespasos.universidad.universidadbackend.servicios.contratos;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Carrera;

public interface CarreraDAO extends GenericoDAO<Carrera>{

    Iterable<Carrera> findCarrerasByNombreContains(String nombre);
    Iterable<Carrera> findCarrerasByNombreContainsIgnoresCase(String nombre);
    Iterable<Carrera> findCarrerasByCantidadAnios(Integer cantidadAnios);
    Iterable<Carrera> buscarCarrerasPorProfesorNombreYApellido(String nombre, String apellido);

}

