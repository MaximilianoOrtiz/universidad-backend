package com.springsimplespasos.universidad.universidadbackend;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Alumno;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Carrera;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Persona;
import com.springsimplespasos.universidad.universidadbackend.repositorios.CarreraRepository;
import com.springsimplespasos.universidad.universidadbackend.repositorios.PersonaReposity;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.CarreraDAO;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.PersonaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CarreraComandos implements CommandLineRunner {

    @Autowired
    private CarreraDAO servicio;

//    @Autowired
//    private PersonaDAO servicioPersona;

    @Override
    public void run(String... args) throws Exception {
/*
        Carrera IngSistemas = new Carrera(null, "Ingeniria en Sistemas", 50, 5);
        Carrera save = servicio.save(IngSistemas);
        System.out.println(save.toString());
*/    /*  Carrera carrera = null;
        Optional<Carrera> oCarrera = servicio.findById(1);
        if (oCarrera.isPresent()){
            carrera = oCarrera.get();
            System.out.println(carrera.toString());
        }
        else {
            System.out.println("no se Encontro carrera");
        }
        carrera.setCantidadAnios(6);
        carrera.setCantidadDeMaterias(65);

        servicio.save(carrera);

        System.out.println(servicio.findById(1).orElse(new Carrera()).toString());

        servicio.deleteById(1);

        System.out.println(servicio.findById(1).orElse(new Carrera()).toString());*/

        //Persona alumno = new Alumno();
        // alumno.setDni("39952523");
        //alumno.setNombre("MAxi nuevo");

        //Persona alumno1 = personaServices.save(alumno);
        //System.out.println(alumno1.toString());

        /*
        Direccion direccion = new Direccion("Pedriel", "123", "1623", "", "", "Junin");
        Persona alumno = new Alumno(null,"PAblo", "Perez", "3333333", direccion);
        Persona save = servicio.save(alumno);
        System.out.println(save.toString());
        */
//
//        List<Persona> alumnos = (List<Persona>) servicio.findAll();
//        alumnos.forEach(System.out::println);
/*

        Carrera ingSistema = new Carrera(null, "Ingenieria en Sistemas", 60, 5);
        Carrera ingIndustrial = new Carrera(null, "Ingenieria Industrial", 55, 5);
        Carrera ingAlimentos = new Carrera(null, "Ingenieria en Alimentos", 53, 5);
        Carrera ingElectronica = new Carrera(null, "Ingenieria Electronica", 45, 5);
        Carrera licSistema = new Carrera(null, "Licenciatura en Sistemas", 40, 4);
        Carrera licTurismo = new Carrera(null, "Licenciatura en Turismo", 42, 4);
        Carrera licYoga = new Carrera(null, "Licenciatura en Yoga", 25, 3);
        Carrera licRecursos = new Carrera(null, "Licenciatura en Recursos Humanos - RRHH", 33, 3);

        servicio.save(ingSistema);
        servicio.save(ingIndustrial);
        servicio.save(ingAlimentos);
        servicio.save(ingElectronica);
        servicio.save(licSistema);
        servicio.save(licTurismo);
        servicio.save(licYoga);
        servicio.save(licRecursos);
*/

        /*List<Carrera> carreras = (List<Carrera>)servicio.findCarrerasByNombreContains("Sistemas");
        carreras.forEach(System.out::println);

        List<Carrera> carreraIgnoreCase1 = (List<Carrera>)servicio.findCarrerasByNombreContainsIgnoresCase("SISTEMAS");
        List<Carrera> carreraIgnoreCase2 = (List<Carrera>)servicio.findCarrerasByNombreContainsIgnoresCase("sistemas");
        carreraIgnoreCase1.forEach(System.out::println);
        carreraIgnoreCase2.forEach(System.out::println);
        */
/*

        List<Carrera> carrerasPorAnio = (List<Carrera>) servicio.findCarrerasByCantidadAniosAfter(3);

        carrerasPorAnio.forEach(System.out::println);
*/



    }

}
