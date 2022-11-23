package com.springsimplespasos.universidad.universidadbackend;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Persona;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.AlumnoDAO;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.CarreraDAO;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.PersonaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class pruebaCarrera implements CommandLineRunner {


    @Autowired
    private CarreraDAO carreraDAO;

    private final PersonaDAO alumno;

    @Autowired
    public pruebaCarrera(@Qualifier("alumnoDAOImpl") AlumnoDAO alumno) {
        this.alumno = alumno;
    }


    @Override
    public void run(String... args) throws Exception {

     /*   List<Carrera> carreras = (List<Carrera>) carreraDAO.findAll();
        carreras.forEach(System.out::println);*/
        List<Persona> alumnos = (List<Persona>) ((AlumnoDAO)alumno).buscarTodos();
        alumnos.forEach(System.out::println);

    }
}
