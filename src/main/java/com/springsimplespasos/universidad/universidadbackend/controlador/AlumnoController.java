package com.springsimplespasos.universidad.universidadbackend.controlador;

import com.springsimplespasos.universidad.universidadbackend.exception.BadRequestExecption;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Alumno;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Carrera;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Persona;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.CarreraDAO;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.PersonaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController extends PersonaController{
    private final CarreraDAO carreraDAO;

    @Autowired
    public AlumnoController(@Qualifier("alumnoDAOImpl") PersonaDAO alumnoDAO, CarreraDAO carreraDAO) {
        super(alumnoDAO);
        nombreEntidad = "Alumno";
        this.carreraDAO = carreraDAO;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarAlumno(@PathVariable Integer id, @RequestBody Persona alumno){
        Map<String,Object> mensaje = new HashMap<>();
        Persona alumnoUpdate = null;
        Optional<Persona> oAlumno = service.findById(id);
        if (!oAlumno.isPresent()) {
            //throw new BadRequestExecption(String.format("Alumno con id %d no existe", id));
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("Alumno con id %d no existe", id));
            return ResponseEntity.badRequest().body(mensaje);
        }
        alumnoUpdate = oAlumno.get();
        alumnoUpdate.setNombre(alumno.getNombre());
        alumnoUpdate.setApellido(alumno.getApellido());
        alumnoUpdate.setDireccion(alumno.getDireccion());
        mensaje.put("success", Boolean.TRUE);
        mensaje.put("datos", service.save(alumnoUpdate));
        return ResponseEntity.ok(mensaje);
    }

    @PutMapping("/{idAlumno}/carrera/{idCarrera}")
    public ResponseEntity<?> asignarCarreraAlumno(@PathVariable Integer idAlumno,@PathVariable Integer idCarrera){
        Map<String,Object> mensaje = new HashMap<>();
        Optional<Persona> oAlumno = service.findById(idAlumno);
        if (!oAlumno.isPresent()) {
            //throw new BadRequestExecption(String.format("Alumno con id %d no existe", idAlumno));
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("Alumno con id %d no existe", idAlumno));
            return ResponseEntity.badRequest().body(mensaje);
        }
        Optional<Carrera> oCarrera = carreraDAO.findById(idCarrera);
        if(!oCarrera.isPresent()){
            //throw new BadRequestExecption(String.format("Carrera con id %d no existe", idCarrera));
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("Alumno con id %d no existe", idAlumno));
            return ResponseEntity.badRequest().body(mensaje);
        }
        Persona alumno = oAlumno.get();
        Carrera carrera = oCarrera.get();
        ((Alumno)alumno).setCarrera(carrera);

        mensaje.put("success", Boolean.TRUE);
        mensaje.put("datos", service.save(alumno));
        return ResponseEntity.ok(mensaje);
    }
}
