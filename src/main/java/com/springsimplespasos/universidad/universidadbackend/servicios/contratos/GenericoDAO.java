package com.springsimplespasos.universidad.universidadbackend.servicios.contratos;

import java.util.Optional;

public interface GenericoDAO<E> {

    Optional<E> findById(Integer id);
    E save(E carrera);
    Iterable<E> findAll();
    void deleteId(Integer id);
}
