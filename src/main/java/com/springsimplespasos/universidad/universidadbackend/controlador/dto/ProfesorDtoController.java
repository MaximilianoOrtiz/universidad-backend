package com.springsimplespasos.universidad.universidadbackend.controlador.dto;

import com.springsimplespasos.universidad.universidadbackend.modelo.dto.PersonaDTO;
import com.springsimplespasos.universidad.universidadbackend.modelo.dto.ProfesorDTO;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Carrera;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Persona;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Profesor;
import com.springsimplespasos.universidad.universidadbackend.modelo.mapper.mapstruct.CarreraMapperMS;
import com.springsimplespasos.universidad.universidadbackend.modelo.mapper.mapstruct.ProfesorMapper;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.CarreraDAO;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.PersonaDAO;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.ProfesorDAO;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("profesores")
@ConditionalOnProperty(prefix = "app", name = "controller.enable-dto", havingValue = "true")
@Api(value = "Acciones relacionadas con los profesores", tags = "Acciones de Profesores")
public class ProfesorDtoController extends PersonaDtoController{

    private final CarreraDAO carreraDAO;

    @Autowired
    private CarreraMapperMS carreraMapperMS;


    public ProfesorDtoController(@Qualifier("profesorDAOImpl") PersonaDAO service, ProfesorMapper profesorMapper, CarreraDAO carreraDAO) {
        super(service, "Profesor", null, null, profesorMapper);
        this.carreraDAO = carreraDAO;
    }

//    @GetMapping
//    public ResponseEntity<?> obtenerProfesores(){
//        Map<String, Object> mensaje = new HashMap<>();
//        List<Persona> personas = super.obtenerTodos();
//        List<PersonaDTO> dtos = null;
//        if(personas.isEmpty()){
//            mensaje.put("succsess", Boolean.FALSE);
//            mensaje.put("data", String.format("No existen %s", nombre_entidad));
//            return ResponseEntity.badRequest().body(mensaje);
//        }
//        dtos = personas
//                .stream()
//                .map((Persona profesor) -> profesorMapper.mapProfesor((Profesor) profesor))
//                .collect(Collectors.toList());
//
//        mensaje.put("success", Boolean.TRUE);
//        mensaje.put("data", dtos);
//        return ResponseEntity.ok(mensaje);
//    }

//    @PostMapping
//    public ResponseEntity<?> altaPersona(@Valid @RequestBody PersonaDTO personaDTO, BindingResult result){
//        Map<String, Object> mensaje = new HashMap<>();
//        if(result.hasErrors()){
//            mensaje.put("success", Boolean.FALSE);
//            mensaje.put("validaciones", super.obtenerValidaciones(result));
//            return ResponseEntity.badRequest().body(mensaje);
//        }
//        //Persona save = personaDAO.save(mapper.mapAlumno((AlumnoDTO) personaDTO));
//        PersonaDTO save =  super.altaPersona(profesorMapper.mapProfesor((ProfesorDTO) personaDTO));
//        mensaje.put("success", Boolean.TRUE);
//        mensaje.put("data", save);
//        return ResponseEntity.ok(mensaje);
//    }

    @ApiOperation(value = "Actualizar profesor")
    @ApiResponses({
            @ApiResponse( code = 200, message = "Ejecutado satisfactoriamente") // Costumizamos los codigos de retornos
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarProfesor(@PathVariable @ApiParam(value = "Codigo del profesor") Integer id,@ApiParam(value = "Datos del profesor actualizados") @Valid @RequestBody PersonaDTO profesor, BindingResult result){
        Map<String, Object> mensajes = new HashMap<>();
        Persona  updateProfesor = null;
        if(result.hasErrors()){
            mensajes.put("success", Boolean.FALSE);
            mensajes.put("validaciones", super.obtenerValidaciones(result));
            return ResponseEntity.badRequest().body(mensajes);
        }
        Optional<Persona> oProfesor = service.findById(id);
        if (!oProfesor.isPresent()){
            mensajes.put("success", Boolean.FALSE);
            mensajes.put("mensajes", String.format("No se encontro %s con id %d", nombre_entidad, id));
            return ResponseEntity.badRequest().body(mensajes);
        }
        updateProfesor = oProfesor.get();
        updateProfesor.setNombre(profesor.getNombre());
        updateProfesor.setApellido(profesor.getApellido());
        updateProfesor.setDni(profesor.getDni());
        ((Profesor)updateProfesor).setSueldo(((ProfesorDTO)profesor).getSueldo());

        mensajes.put("success", Boolean.TRUE);
        mensajes.put("data", profesorMapper.mapProfesor((Profesor) super.altaEntidad(updateProfesor)));
        return ResponseEntity.ok(mensajes);
    }

    @ApiOperation(value = "Buscar profesores por carreras")
    @ApiResponses({
            @ApiResponse( code = 200, message = "Ejecutado satisfactoriamente") // Costumizamos los codigos de retornos
    })
    @GetMapping("/profesores-carreras/{carrera}")
    public ResponseEntity<?> findProfesoresByCarrera(@ApiParam(value = "Nombre de la carrera", example = "Ingenieria en Alimentos") @PathVariable String carrera) {
        Map<String, Object> mensaje = new HashMap<>();
        List<Profesor> profesoresByCarrera = (List<Profesor>) ((ProfesorDAO) service).findProfesoresByCarrera(carrera);
        if (profesoresByCarrera.isEmpty()) {
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("success", String.format("No existen profesores asignados a la carrera: %s", carrera));
            return ResponseEntity.badRequest().body(mensaje);
        }
        List<ProfesorDTO> dtos = profesoresByCarrera
                .stream()
                .map(profesorMapper::mapProfesor)
                .collect(Collectors.toList());

        mensaje.put("success", Boolean.TRUE);
        mensaje.put("datos", dtos);
        return ResponseEntity.ok(mensaje);
    }

    @ApiOperation(value = "Asignar carrera a profesor")
    @ApiResponses({
            @ApiResponse( code = 200, message = "Ejecutado satisfactoriamente") // Costumizamos los codigos de retornos
    })
    @PutMapping("/{idProfesor}/carrera/{idCarrera}")
    public ResponseEntity<?> asignarCarreraProfesor(@PathVariable @ApiParam(value = "Codigo del profesor") Integer idProfesor, @ApiParam(value = "Codigo de la carrera") @PathVariable Integer idCarrera) {
        Map<String, Object> mensaje = new HashMap<>();
        Optional<Persona> oProfesor = service.findById(idProfesor);
        Optional<Carrera> oCarrera = carreraDAO.findById(idCarrera);
        if (!oProfesor.isPresent()) {
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("success", String.format("No existe el profesor con id %d", idProfesor));
            return ResponseEntity.badRequest().body(mensaje);
        }
        if (!oCarrera.isPresent()) {
            //throw new BadRequestExecption(String.format("No existe la carrera con id %d", idCarrera));
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("success", String.format("No existe la carrera con id %d", idCarrera));
            return ResponseEntity.badRequest().body(mensaje);
        }
        Persona profesor = oProfesor.get();
        Carrera carrera = oCarrera.get();
        Set<Carrera> carrerasAsignadas = ((Profesor) profesor).getCarreras();
        carrerasAsignadas.add(carrera);
        ((Profesor) profesor).setCarreras(carrerasAsignadas);

        Map<String, Object> asignacionPersonaACarrera = new HashMap<>();
        asignacionPersonaACarrera.put("profesor", profesorMapper.mapProfesor((Profesor) super.altaEntidad(profesor)));
        asignacionPersonaACarrera.put("carrera-asignada", carreraMapperMS.mapCarrera(carrera));


        mensaje.put("success", Boolean.TRUE);
        mensaje.put("datos",asignacionPersonaACarrera);
       // mensaje.put("carrera-asignada", carreraMapperMS.mapCarrera(carrera));

        return ResponseEntity.ok(mensaje);
    }
}
