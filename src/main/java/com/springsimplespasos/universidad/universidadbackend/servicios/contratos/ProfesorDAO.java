package com.springsimplespasos.universidad.universidadbackend.servicios.contratos;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Profesor;

public interface ProfesorDAO extends  PersonaDAO{

    Iterable<Profesor> findProfesoresByCarrera(String carrera);

    Iterable<Profesor> buscarTodos();
}
