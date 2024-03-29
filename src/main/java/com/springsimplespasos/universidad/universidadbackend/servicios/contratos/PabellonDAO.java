package com.springsimplespasos.universidad.universidadbackend.servicios.contratos;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Pabellon;

public interface PabellonDAO extends  GenericoDAO<Pabellon>{
    Iterable<Pabellon> findPabellonByDireccionLocalidad(String localidad);
    Iterable<Pabellon> findPabellonByNombre(String nombre);

}
