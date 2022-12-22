package com.springsimplespasos.universidad.universidadbackend.controlador.dto;


import com.springsimplespasos.universidad.universidadbackend.modelo.dto.PersonaDTO;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Alumno;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Carrera;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Persona;
import com.springsimplespasos.universidad.universidadbackend.modelo.mapper.mapstruct.AlumnoMapper;
import com.springsimplespasos.universidad.universidadbackend.modelo.mapper.mapstruct.CarreraMapperMS;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.CarreraDAO;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.PersonaDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/alumnos")
@ConditionalOnProperty(prefix = "app", name = "controller.enable-dto", havingValue = "true")
public class AlumnoDtoController extends PersonaDtoController{

    private final CarreraDAO carreraDAO;

    private final CarreraMapperMS carreraMapperMS;

    public AlumnoDtoController(@Qualifier("alumnoDAOImpl") PersonaDAO service, AlumnoMapper alumnoMapper, CarreraDAO carreraDAO, CarreraMapperMS carreraMapperMS) {
        super(service, "Alumno", null, alumnoMapper , null);
        this.carreraDAO = carreraDAO;
        this.carreraMapperMS = carreraMapperMS;
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<?> obtenerAlumnoPorId(@PathVariable  Integer id){
//        Map<String, Object> mensaje = new HashMap<>();
//        Optional<Persona> oPersona = service.findById(id);
//        if (!oPersona.isPresent()){
//            mensaje.put("success", Boolean.FALSE);
//            mensaje.put("mensaje", String.format(" Alumno con id %d no encontrado", id ));
//            return ResponseEntity.badRequest().body(mensaje);
//        }
//        mensaje.put("success", Boolean.TRUE);
//        mensaje.put("data", super.buscarPorId(id));
//        return ResponseEntity.ok(mensaje);
//    }

//    @PostMapping
//    public ResponseEntity<?> altaAlumno(@Valid @RequestBody PersonaDTO personaDTO, BindingResult result){
//        Map<String, Object> mensaje = new HashMap<>();
//
//        if(result.hasErrors()){
//            mensaje.put("success", Boolean.FALSE);
//            mensaje.put("validaciones", super.obtenerValidaciones(result));
//            return ResponseEntity.badRequest().body(mensaje);
//        }
//        //Persona save = personaDAO.save(mapper.mapAlumno((AlumnoDTO) personaDTO));
//        PersonaDTO save =  super.altaPersona(alumnoMapper.mapAlumno((AlumnoDTO) personaDTO));
//        mensaje.put("success", Boolean.TRUE);
//        mensaje.put("data", save);
//        return ResponseEntity.ok(mensaje);
//    }

//    @GetMapping()
//    public ResponseEntity<?> obtenerAlumnos(){
//        Map<String, Object> mensaje = new HashMap<>();
//        //List<Persona> personas = (List<Persona>) ((AlumnoDAO)service).buscarTodos();
//        List<Persona> personas = super.obtenerTodos();
//        List<PersonaDTO> dtos = null;
//        if(personas.isEmpty()){
//            mensaje.put("succsess", Boolean.FALSE);
//            mensaje.put("data", String.format("No existen %s", nombre_entidad));
//            return ResponseEntity.badRequest().body(mensaje);
//        }
//        dtos = personas
//                .stream()
//                .map((Persona alumno) -> alumnoMapper.mapAlumno((Alumno) alumno))
//                .collect(Collectors.toList());
//
//        mensaje.put("success", Boolean.TRUE);
//        mensaje.put("data", dtos);
//        return ResponseEntity.ok(mensaje);
//    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarAlumno(@PathVariable Integer id, @Valid @RequestBody PersonaDTO alumno, BindingResult result){
        Map<String,Object> mensaje = new HashMap<>();
        Map<String, Object> validaciones = new HashMap<>();
        if (result.hasErrors()){
           mensaje.put("success", Boolean.FALSE);
           mensaje.put("validaciones", super.obtenerValidaciones(result));
            return ResponseEntity.badRequest().body(mensaje);
        }
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
        mensaje.put("datos", super.altaEntidad(oAlumno.get()));
        return ResponseEntity.ok(mensaje);
    }


    @PutMapping("/{idAlumno}/carrera/{idCarrera}")
    public ResponseEntity<?> asignarCarreraAlumno(@PathVariable Integer idAlumno,@PathVariable Integer idCarrera){
        Map<String,Object> mensaje = new HashMap<>();
        Optional<Persona> oAlumno = service.findById(idAlumno);
        if (!oAlumno.isPresent()) {
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("Alumno con id %d no existe", idAlumno));
            return ResponseEntity.badRequest().body(mensaje);
        }
        Optional<Carrera> oCarrera = carreraDAO.findById(idCarrera);
        if(!oCarrera.isPresent()){
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
