package com.springsimplespasos.universidad.universidadbackend.controlador.dto;

import com.springsimplespasos.universidad.universidadbackend.modelo.dto.AlumnoDTO;
import com.springsimplespasos.universidad.universidadbackend.modelo.dto.EmpleadoDTO;
import com.springsimplespasos.universidad.universidadbackend.modelo.dto.PersonaDTO;
import com.springsimplespasos.universidad.universidadbackend.modelo.dto.ProfesorDTO;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Alumno;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Empleado;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Persona;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Profesor;
import com.springsimplespasos.universidad.universidadbackend.modelo.mapper.mapstruct.AlumnoMapper;
import com.springsimplespasos.universidad.universidadbackend.modelo.mapper.mapstruct.EmpleadoMapper;
import com.springsimplespasos.universidad.universidadbackend.modelo.mapper.mapstruct.ProfesorMapper;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.AlumnoDAO;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.EmpeladoDAO;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.PersonaDAO;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.ProfesorDAO;
import com.springsimplespasos.universidad.universidadbackend.servicios.implementaciones.AlumnoDAOImpl;
import com.springsimplespasos.universidad.universidadbackend.servicios.implementaciones.EmpleadoDAOImpl;
import com.springsimplespasos.universidad.universidadbackend.servicios.implementaciones.PersonaDAOImpl;
import com.springsimplespasos.universidad.universidadbackend.servicios.implementaciones.ProfesorDAOImpl;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class PersonaDtoController extends GenericDtoController<Persona, PersonaDAO>{

    protected  final EmpleadoMapper empleadoMapper;

    protected final AlumnoMapper alumnoMapper;

    protected  final ProfesorMapper profesorMapper;

    
    public PersonaDtoController(PersonaDAO service, String nombre_entidad, EmpleadoMapper empleadoMapper, AlumnoMapper alumnoMapper, ProfesorMapper profesorMapper) {
        super(service, nombre_entidad);
        this.empleadoMapper = empleadoMapper;
        this.alumnoMapper = alumnoMapper;
        this.profesorMapper = profesorMapper;
    }

    @ApiOperation(value = "Alta de persona")
    @ApiResponses({
            @ApiResponse( code = 200, message = "Ejecutado satisfactoriamente") // Costumizamos los codigos de retornos
    })
    @PostMapping
    public ResponseEntity<?> altaPersona(@Valid @RequestBody @ApiParam(value = "persona a dar de alta") PersonaDTO personaDTO , BindingResult result) {
        Persona personaEntidad = null; /* super.altaEntidad(persona);*/
        PersonaDTO dto = null;
        Map<String, Object> mensaje = new HashMap<>();
        if(result.hasErrors()){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("validaciones", super.obtenerValidaciones(result));
            return ResponseEntity.badRequest().body(mensaje);
        }
        if (personaDTO instanceof AlumnoDTO) {
            personaEntidad = super.altaEntidad(alumnoMapper.mapAlumno((AlumnoDTO) personaDTO));
            dto = alumnoMapper.mapAlumno((Alumno) personaEntidad);
        } else if (personaDTO instanceof ProfesorDTO) {
            personaEntidad = super.altaEntidad(profesorMapper.mapProfesor((ProfesorDTO) personaDTO));
            dto = profesorMapper.mapProfesor((Profesor) personaEntidad);
        } else if (personaDTO instanceof EmpleadoDTO) {
            personaEntidad = (Empleado) super.altaEntidad(empleadoMapper.mapEmpleado((EmpleadoDTO) personaDTO));
            dto = empleadoMapper.mapEmpleado((Empleado) personaEntidad);
        }
        if (dto != null){
            mensaje.put("success", Boolean.TRUE);
            mensaje.put("data", dto);
        }else{
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No se a encontrado ninguna persona"));
            return ResponseEntity.badRequest().body(mensaje);
        }
        return ResponseEntity.ok(mensaje);
    }

    @ApiOperation(value = "Obtener personas")
    @ApiResponses({
            @ApiResponse( code = 200, message = "Ejecutado satisfactoriamente") // Costumizamos los codigos de retornos
    })
    @GetMapping()
    public ResponseEntity<?> obtenerPersonas(){
        Map<String, Object> mensaje = new HashMap<>();
        List<Persona> personas = super.obtenerTodos();
        List<PersonaDTO> dtos = null;
        if(personas.isEmpty()){
            mensaje.put("succsess", Boolean.FALSE);
            mensaje.put("data", String.format("No existen %s", nombre_entidad));
            return ResponseEntity.badRequest().body(mensaje);
        }
        if (service instanceof AlumnoDAO){
            dtos = personas
                    .stream()
                    .map((Persona alumno) -> alumnoMapper.mapAlumno((Alumno) alumno))
                    .collect(Collectors.toList());
        }
        else if (service instanceof ProfesorDAO){
            dtos =personas
                    .stream()
                    .map(persona -> profesorMapper.mapProfesor((Profesor) persona))
                    .collect(Collectors.toList());
        } else if (service instanceof EmpeladoDAO) {
            dtos = personas
                    .stream()
                    .map(persona -> empleadoMapper.mapEmpleado((Empleado) persona))
                    .collect(Collectors.toList());
        }
        mensaje.put("success", Boolean.TRUE);
        mensaje.put("data", dtos);
        return ResponseEntity.ok(mensaje);
    }

//    @GetMapping("/{id}")
//    public PersonaDTO buscarPorId(@PathVariable Integer id){
//        Optional<Persona> personaEntidad = super.obtenerPorId(id);
//        PersonaDTO dto = null;
//        if (personaEntidad.get() instanceof Alumno){
//            dto = alumnoMapper.mapAlumno((Alumno)personaEntidad.get());
//        }
//        if(personaEntidad.get() instanceof  Profesor){
//        dto = profesorMapper.mapProfesor((Profesor) personaEntidad.get());
//        }
//        if (personaEntidad.get() instanceof  Empleado){
//        dto = empleadoMapper.mapEmpleado((Empleado) personaEntidad.get());
//        }
//        return dto;
//    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtener persona por id")
    @ApiResponses({
            @ApiResponse( code = 200, message = "Ejecutado satisfactoriamente") // Costumizamos los codigos de retornos
    })
    public ResponseEntity<?> obtenerPersonaPorId(@ApiParam(value = "codigo de la persona") @PathVariable  Integer id) {
        Map<String, Object> mensaje = new HashMap<>();
        Optional<Persona> oPersona = service.findById(id);
        log.info(oPersona.toString());
        PersonaDTO dto = null;
        if (!oPersona.isPresent()) {
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format(" %s con id %d no encontrado", nombre_entidad ,id));
            return ResponseEntity.badRequest().body(mensaje);
        }
        log.info(oPersona.get().getClass().toString());
        if (oPersona.get() instanceof Alumno) {
            dto = alumnoMapper.mapAlumno((Alumno) oPersona.get());
        }
        if(oPersona.get() instanceof  Profesor){
            dto = profesorMapper.mapProfesor((Profesor) oPersona.get());
        }
        if (oPersona.get() instanceof  Empleado){
            dto = empleadoMapper.mapEmpleado((Empleado) oPersona.get());
        }
        mensaje.put("success", Boolean.TRUE);
        mensaje.put("data", dto);
        return ResponseEntity.ok(mensaje);
    }


//    public List<PersonaDTO> buscarTodos(){
//        List<Persona> personas = super.obtenerTodos();
//        List<PersonaDTO> dtos = null;
//        System.out.println("personas.get(0):" + personas.get(0));
//        if (personas.get(0) instanceof Alumno){
//                dtos = personas
//                    .stream()
//                    .map((Persona alumno) -> alumnoMapper.mapAlumno((Alumno) alumno))
//                    .collect(Collectors.toList());
//        }
//        return dtos;
//    }

    @ApiOperation(value = "Buscar persona por Nombre y Apellido")
    @ApiResponses({
            @ApiResponse( code = 200, message = "Ejecutado satisfactoriamente") // Costumizamos los codigos de retornos
    })
    @GetMapping("/nombre-apellido")
    public ResponseEntity<?> buscarPersonaPorNombreYApellido(@RequestParam @ApiParam(value = "Nombre de la persona") String nombre, @ApiParam(value = "Apellido de la persona")@RequestParam String apellido){
        Map<String, Object> mensaje = new HashMap<>();
        Optional<Persona> oPersona = service.buscarPorNombreYApellido(nombre, apellido);
        if (!oPersona.isPresent()){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No se encontro Persona con nombre %s y apellido %s", nombre, apellido));
            return ResponseEntity.badRequest().body(mensaje);
        }
        PersonaDTO dto = null;
        if (oPersona.get() instanceof Profesor){
            dto = profesorMapper.mapProfesor((Profesor) oPersona.get());
        }else if (oPersona.get() instanceof  Alumno){
            dto = alumnoMapper.mapAlumno((Alumno) oPersona.get());
        }else if (oPersona.get() instanceof  Empleado){
            dto = empleadoMapper.mapEmpleado((Empleado) oPersona.get());
        }

        mensaje.put("success", Boolean.TRUE);
        mensaje.put("datos", dto);
        return ResponseEntity.ok(mensaje);
    }

    @ApiOperation(value = "Buscar persona por dni")
    @ApiResponses({
            @ApiResponse( code = 200, message = "Ejecutado satisfactoriamente") // Costumizamos los codigos de retornos
    })
    @GetMapping("/dni/{dni}")
    public ResponseEntity<?> buscarPorDni (@PathVariable @ApiParam(value = "Dni de la persona") String dni){
        Map<String, Object> mensaje = new HashMap<>();
        Optional<Persona> personaEncontrada = service.buscarPorDni(dni);
        if(!personaEncontrada.isPresent()){
            //throw new BadRequestExecption(String.format("La persona con dni %s no existe", dni));
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("La persona con dni %s no existe", dni));
            return ResponseEntity.badRequest().body(mensaje);
        }
        PersonaDTO dto = null;
        if (personaEncontrada.get() instanceof Profesor){
            dto = profesorMapper.mapProfesor((Profesor) personaEncontrada.get());
        }else if (personaEncontrada.get() instanceof  Alumno){
            dto = alumnoMapper.mapAlumno((Alumno) personaEncontrada.get());
        }
        else if (personaEncontrada.get() instanceof  Empleado){
            dto = empleadoMapper.mapEmpleado((Empleado) personaEncontrada.get());
        }
        mensaje.put("success", Boolean.TRUE);
        mensaje.put("data", dto);
        return ResponseEntity.ok(mensaje);
    }


    @ApiOperation(value = "Buscar persona por apellido")
    @ApiResponses({
            @ApiResponse( code = 200, message = "Ejecutado satisfactoriamente") // Costumizamos los codigos de retornos
    })
    @GetMapping("/apellido/{apellido}")
    public ResponseEntity<?> buscarPersonaPorApellido (@PathVariable @ApiParam(value = "Apellido de la persona")  String apellido){
        log.info(apellido);
        Map<String, Object> mensaje = new HashMap<>();
        List<Persona> personasEncontradas = (List<Persona>) service.buscarPersonaPorApellido(apellido);
        List<Persona> personasFiltrados = new ArrayList<>();

        log.info("CANTIDAD DE PERSONAS ENCONTRADAS: " + personasEncontradas.size());
        if (personasEncontradas.isEmpty()){
            log.info("Is Empty: " + Boolean.TRUE);
            //throw new BadRequestExecption(String.format("Las personas con apellido %s no existen", apellido));
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("Las personas con apellido %s no existen", apellido));
            return ResponseEntity.badRequest().body(mensaje);
        }
        log.info("Is Empty: " + Boolean.FALSE);
        List<PersonaDTO> dtos = null;
        log.info("Instanceof: " + service.getClass());

        if (service instanceof ProfesorDAOImpl){
            log.info("entro en Persona");
            for (Persona persona: personasEncontradas){
                if (persona instanceof Profesor){
                    personasFiltrados.add(persona);
                }
            }
            if (personasFiltrados.isEmpty()){
                mensaje.put("success", Boolean.FALSE);
                mensaje.put("mensaje", String.format("No se encontro %s con apellido %s", nombre_entidad ,apellido));
                return ResponseEntity.badRequest().body(mensaje);
            }
            dtos = personasFiltrados.stream().map(persona -> profesorMapper.mapProfesor((Profesor) persona)).collect(Collectors.toList());
        }
        else if (service instanceof AlumnoDAOImpl){
            log.info("entro en Alumno");
            for (Persona persona: personasEncontradas){
                if (persona instanceof Alumno){
                    personasFiltrados.add(persona);
                }
            }
            if (personasFiltrados.isEmpty()){
                mensaje.put("success", Boolean.FALSE);
                mensaje.put("mensaje", String.format("No se encontro %s con apellido %s", nombre_entidad ,apellido));
                return ResponseEntity.badRequest().body(mensaje);
            }
            dtos = personasFiltrados.stream().map(persona -> alumnoMapper.mapAlumno((Alumno) persona)).collect(Collectors.toList());
        }
        else if (service instanceof EmpleadoDAOImpl){
            log.info("entro en Empleado");
            for (Persona persona: personasEncontradas){
                if (persona instanceof Empleado){
                    personasFiltrados.add(persona);
                }
            }
            if (personasFiltrados.isEmpty()){
                mensaje.put("success", Boolean.FALSE);
                mensaje.put("mensaje", String.format("No se encontro %s con apellido %s", nombre_entidad ,apellido));
                return ResponseEntity.badRequest().body(mensaje);
            }
            dtos = personasFiltrados.stream().map(persona -> empleadoMapper.mapEmpleado((Empleado) persona)).collect(Collectors.toList());
        }
        mensaje.put("success", Boolean.TRUE);
        mensaje.put("datos", dtos);
        return ResponseEntity.ok(mensaje);
    }
}
