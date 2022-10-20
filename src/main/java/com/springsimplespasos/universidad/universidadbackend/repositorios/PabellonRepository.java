package com.springsimplespasos.universidad.universidadbackend.repositorios;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Pabellon;
import org.springframework.data.repository.CrudRepository;

public interface PabellonRepository extends CrudRepository<Pabellon, Integer> {

    Iterable<Pabellon> findPabellonByDireccionLocalidad(String localidad);
    Iterable<Pabellon> findPabellonByNombre(String nombre);

}
