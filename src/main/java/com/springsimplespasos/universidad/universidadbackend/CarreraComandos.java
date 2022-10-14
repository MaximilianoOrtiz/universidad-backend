package com.springsimplespasos.universidad.universidadbackend;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Persona;
import com.springsimplespasos.universidad.universidadbackend.repositorios.PersonaReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CarreraComandos implements CommandLineRunner {
/*

    @Autowired
    private CarreraDAO servicio;
*/
    @Autowired
    private PersonaReposity servicio;

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

        List<Persona> alumnos = (List<Persona>) servicio.findAll();
        alumnos.forEach(System.out::println);

    }


}
