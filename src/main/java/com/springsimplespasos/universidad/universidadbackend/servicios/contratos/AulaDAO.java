package com.springsimplespasos.universidad.universidadbackend.servicios.contratos;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Aula;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.enumeradores.Pizarron;

import java.util.Optional;

public interface AulaDAO extends GenericoDAO<Aula> {

    Iterable<Aula> findAulasByPizarronContains(Pizarron pizarron);

    Iterable<Aula> findAulaByPabellonNombre(String nombrePabellon);

    Optional<Aula> findAulaByNroAulaContains(Integer nroAula);

}
