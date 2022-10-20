package com.springsimplespasos.universidad.universidadbackend;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Carrera;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Direccion;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Persona;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Profesor;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.CarreraDAO;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.PersonaDAO;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.ProfesorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class ProfesorCommand implements CommandLineRunner {

    @Autowired(required = true)
    @Qualifier("profesorDAOImpl")
    private PersonaDAO personaDAO;

    @Autowired
    private CarreraDAO carreraDAO;

    @Override
    public void run(String... args) throws Exception {
/*

        Optional<Carrera> carrera = carreraDAO.findById(2);
        Optional<Carrera> carrera1 = carreraDAO.findById(3);

        Direccion direccion = new Direccion("Pergamino", "448", "1888",null, null, "Florencio Varela");
        Profesor profesor = new Profesor(5, "Facu", "Perez", "38950545",direccion, new BigDecimal(90000));
        Profesor profesor2 = new Profesor(8, "Maxi", "Ortiz", "38950585",direccion, new BigDecimal(90000));
*/

     /*   Set<Profesor> profesores  = new HashSet<Profesor>();
        profesores.add(profesor);
        profesores.add(profesor2);*/
/*

        Set<Carrera> carreras1 = new HashSet<Carrera>();
        carreras1.add(carrera1.get());
        carreras1.add(carrera.get());
        profesor.setCarreras(carreras1);

        Set<Profesor> profesores1  = new HashSet<Profesor>();
        profesores1.add(profesor);
        //carrera.get().setProfesores(profesores);
        carrera1.get().setProfesores(profesores1);
*/

     /*   Set<Carrera> carreras  = new HashSet<Carrera>();
        carreras.add(carrera.get());
        profesor.setCarreras(carreras);
        profesor2.setCarreras(carreras);*/



       /* Carrera carreraGuardada = carreraDAO.save(carrera.get());
        System.out.println(carreraGuardada.toString());*/
/*
        Carrera carreraGuardada1 = carreraDAO.save(carrera1.get());
        System.out.println(carreraGuardada1.toString());


        Persona persona = personaDAO.save(profesor);
        System.out.println(persona.toString());*/
/*
        Persona persona1 = personaDAO.save(profesor2);
        System.out.println(persona1);*/

        //System.out.println(carrera.toString());
        //carrera.get().addProfesor(profesor);
        //carrera.get().addProfesor(profesor2);

        //profesor.addCarrera(carrera.get());
        //profesor2.addCarrera(carrera.get());


   /*     Iterable<Profesor> listProfesoresByCarrera = ((ProfesorDAO) personaDAO).findProfesoresByCarrera("Ingenieria en Sistemas");
        listProfesoresByCarrera.forEach(profesor -> System.out.println(profesor.toString()));

        System.out.println("Proxima carrera");

        Iterable<Profesor> listProfesoresByCarrera1 = ((ProfesorDAO) personaDAO).findProfesoresByCarrera("Ingenieria Industrial");
        listProfesoresByCarrera1.forEach(profesor -> System.out.println(profesor.toString()));
*/
    }
}
