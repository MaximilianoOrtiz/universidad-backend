package com.springsimplespasos.universidad.universidadbackend.servicios.contratos;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Persona;

public interface AlumnoDAO extends PersonaDAO{

        // Aca solo vamos a tener las operaciones exclusivos de Alumno

    Iterable<Persona> buscarAlumnoPorNombreCarrera(String nombre);

}

