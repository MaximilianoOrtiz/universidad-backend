package com.springsimplespasos.universidad.universidadbackend.controlador;

import com.springsimplespasos.universidad.universidadbackend.exception.BadRequestExecption;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Carrera;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Persona;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Profesor;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.CarreraDAO;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.PersonaDAO;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.ProfesorDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/profesores")
public class ProfesorController extends PersonaController {

    private final CarreraDAO carreraDAO;

    public ProfesorController(@Qualifier("profesorDAOImpl") PersonaDAO profesorServices, CarreraDAO carreraDAO) {
        super(profesorServices);
        this.carreraDAO = carreraDAO;
        this.nombreEntidad = "Profesor";
    }

    @GetMapping("/profesores-carreras/{carrera}")
    public List<Profesor> findProfesoresByCarrera(@PathVariable String carrera){
        List<Profesor> profesoresByCarrera = (List<Profesor>)((ProfesorDAO)service).findProfesoresByCarrera(carrera);
        if (profesoresByCarrera.isEmpty()){
            throw new BadRequestExecption(String.format("No existen profesores asignados a la carrera: %s", carrera));
        }
        return profesoresByCarrera;
    }

    @PutMapping("/{idProfesor}/carrera/{idCarrera}")
    public Persona asignarCarreraProfesor(@PathVariable Integer idProfesor,@PathVariable Integer idCarrera){
        Optional<Persona> oProfesor = service.findById(idProfesor);
        Optional<Carrera> oCarrera = carreraDAO.findById(idCarrera);
        if (!oProfesor.isPresent())
            throw new BadRequestExecption(String.format("No existe el profesor con id %d", idProfesor));
        if (!oCarrera.isPresent())
            throw new BadRequestExecption(String.format("No existe la carrera con id %d", idCarrera));
        else{
            Persona profesor = oProfesor.get();
            Carrera carrera = oCarrera.get();
            Set<Carrera> carrerasAsignadas = ((Profesor)profesor).getCarreras();
            carrerasAsignadas.add(carrera);
            ((Profesor)profesor).setCarreras(carrerasAsignadas);
            return service.save(profesor);
        }
    }
}
