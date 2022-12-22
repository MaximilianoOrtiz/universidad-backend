package com.springsimplespasos.universidad.universidadbackend.controlador;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Carrera;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Persona;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Profesor;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.CarreraDAO;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.PersonaDAO;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.ProfesorDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Deprecated
@RestController
@RequestMapping("/profesores")
@ConditionalOnProperty(prefix = "app", name = "controller.enable-dto", havingValue = "false")
public class ProfesorController extends PersonaController {

    private final CarreraDAO carreraDAO;

    public ProfesorController(@Qualifier("profesorDAOImpl") PersonaDAO profesorServices, CarreraDAO carreraDAO) {
        super(profesorServices);
        this.carreraDAO = carreraDAO;
        this.nombreEntidad = "Profesor";
    }

    @GetMapping("/profesores-carreras/{carrera}")
    public ResponseEntity<?> findProfesoresByCarrera(@PathVariable String carrera) {
        Map<String, Object> mensaje = new HashMap<>();
        List<Profesor> profesoresByCarrera = (List<Profesor>) ((ProfesorDAO) service).findProfesoresByCarrera(carrera);
        if (profesoresByCarrera.isEmpty()) {
            //throw new BadRequestExecption(String.format("No existen profesores asignados a la carrera: %s", carrera));
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("success", String.format("No existen profesores asignados a la carrera: %s", carrera));
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("success", Boolean.TRUE);
        mensaje.put("datos", profesoresByCarrera);
        return ResponseEntity.ok(mensaje);
    }
    @PutMapping("/{idProfesor}/carrera/{idCarrera}")
    public ResponseEntity<?> asignarCarreraProfesor(@PathVariable Integer idProfesor, @PathVariable Integer idCarrera) {
        Map<String, Object> mensaje = new HashMap<>();
        Optional<Persona> oProfesor = service.findById(idProfesor);
        Optional<Carrera> oCarrera = carreraDAO.findById(idCarrera);
        if (!oProfesor.isPresent()){
            //throw new BadRequestExecption(String.format("No existe el profesor con id %d", idProfesor));
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No existe el profesor con id %d", idProfesor));
            return ResponseEntity.badRequest().body(mensaje);
        }
        if (!oCarrera.isPresent()){
            //throw new BadRequestExecption(String.format("No existe la carrera con id %d", idCarrera));
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No existe la carrera con id %d", idCarrera));
            return ResponseEntity.badRequest().body(mensaje);
        }
        Persona profesor = oProfesor.get();
        Carrera carrera = oCarrera.get();
        Set<Carrera> carrerasAsignadas = ((Profesor)profesor).getCarreras();
        carrerasAsignadas.add(carrera);
        ((Profesor)profesor).setCarreras(carrerasAsignadas);

        mensaje.put("success", Boolean.TRUE);
        mensaje.put("datos", service.save(profesor));

        return ResponseEntity.ok(mensaje);
    }
}
