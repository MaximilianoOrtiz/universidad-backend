package com.springsimplespasos.universidad.universidadbackend.servicios.contratos;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Carrera;

import java.util.Optional;

public interface GenericoDAO<E> {


    Optional<E> findById(Integer id);
    E save(E carrera);
    Iterable<E> findAll();
    void deleteId(Integer id);
}
