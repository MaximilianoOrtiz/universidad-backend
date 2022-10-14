package com.springsimplespasos.universidad.universidadbackend.servicios.implementaciones;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Alumno;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Persona;
import com.springsimplespasos.universidad.universidadbackend.repositorios.PersonaReposity;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.AlumnoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class ALumnoDAOImpl implements AlumnoDAO {

    @Autowired
    @Qualifier("repositoryAlumnos")
    private PersonaReposity reposity;

    @Override
    @Transactional(readOnly = true)
    public Optional<Persona> findById(Integer id) {
        return reposity.findById(id);
    }

    @Override
    public Persona save(Persona persona) {
        return null;
    }

    @Override
    public Iterable<Persona> findAll() {
        return null;
    }

    @Override
    public void deleteId(Integer id) {

    }
}
