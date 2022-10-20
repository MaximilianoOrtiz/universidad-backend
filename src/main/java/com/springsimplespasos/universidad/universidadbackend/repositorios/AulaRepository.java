package com.springsimplespasos.universidad.universidadbackend.repositorios;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Aula;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.enumeradores.Pizarron;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AulaRepository extends CrudRepository<Aula, Integer> {

    Iterable<Aula> findAulasByPizarronContains(Pizarron pizarron);
    Iterable<Aula> findAulaByPabellonNombre(String nombrePabellon);
    Optional<Aula> findAulaByNroAulaContains(Integer nroAula);
}

