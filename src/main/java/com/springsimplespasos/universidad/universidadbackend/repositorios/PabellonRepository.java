package com.springsimplespasos.universidad.universidadbackend.repositorios;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Pabellon;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PabellonRepository extends CrudRepository<Pabellon, Integer> {

   // @Query("select p from Pabellon p join fetch p.direccion d where d.localidad = ?1")
    Iterable<Pabellon> findPabellonByDireccionLocalidad(String localidad);
    Iterable<Pabellon> findPabellonByNombre(String nombre);

}
