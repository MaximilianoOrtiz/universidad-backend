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
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.PersonaDAO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @PostMapping
    public ResponseEntity<?> altaPersona(@Valid @RequestBody PersonaDTO personaDTO , BindingResult result) {
        Persona personaEntidad = null;/* super.altaEntidad(persona);*/
        PersonaDTO dto = null;
        //PersonaDTO dto = null;
        System.out.println("Log - tipo de persona" + personaDTO.getClass());
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
            System.out.println("--------------------------------------------------------------------");
            System.out.println("Log - entro en Empleado");
            //aplicamos el mapper de profesor
            personaEntidad = super.altaEntidad(profesorMapper.mapProfesor((ProfesorDTO) personaDTO));
            dto = profesorMapper.mapProfesor((Profesor) personaEntidad);
            //mensaje.put("data", profesorMapper.mapProfesor((Profesor) personaEntidad));
        } else if (personaDTO instanceof EmpleadoDTO) {
            System.out.println("--------------------------------------------------------------------");
            //Empleado empleadoMApeado = empleadoMapper.mapEmpleado((EmpleadoDTO) personaDTO);
            //System.out.println("EMPLEADO "+empleadoMApeado.toString());
            //Empleado empleado = (Empleado) super.altaEntidad(empleadoMapper.mapEmpleado((EmpleadoDTO) personaDTO));
           // System.out.println(empleado.toString());
           // System.out.println("Log - entro en Empleado");
            personaEntidad = (Empleado) super.altaEntidad(empleadoMapper.mapEmpleado((EmpleadoDTO) personaDTO));
            dto = empleadoMapper.mapEmpleado((Empleado) personaEntidad);
            //System.out.println("Empleado encontrado EEEEEEEEE");
            //System.out.println(dto.toString());
        }
        if (dto != null){
            mensaje.put("success", Boolean.TRUE);
            mensaje.put("data", dto);
        }else{
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("data", dto);
            return ResponseEntity.badRequest().body(mensaje);
        }
        return ResponseEntity.ok(mensaje);
    }


    public PersonaDTO buscarPorId(Integer id){
        Optional<Persona> personaEntidad = super.obtenerPorId(id);
        PersonaDTO dto = null;
        if (personaEntidad.get() instanceof Alumno){
            dto = alumnoMapper.mapAlumno((Alumno)personaEntidad.get());
        }
        return dto;
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


    @GetMapping("/nombre-apellido")
    public ResponseEntity<?> buscarPersonaPorNombreYApellido(@RequestParam String nombre, @RequestParam String apellido){
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

    @GetMapping("/dni/{dni}")
    public ResponseEntity<?> buscarPorDni (@PathVariable String dni){
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

    @GetMapping("/apellido/{apellido}")
    public ResponseEntity<?> buscarPersonaPorApellido (@PathVariable() String apellido){
        Map<String, Object> mensaje = new HashMap<>();
        List<Persona> personasEncontradas = (List<Persona>) service.buscarPersonaPorApellido(apellido);
        if (personasEncontradas.isEmpty()){
            //throw new BadRequestExecption(String.format("Las personas con apellido %s no existen", apellido));
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("Las personas con apellido %s no existen", apellido));
            return ResponseEntity.badRequest().body(mensaje);
        }
        List<PersonaDTO> dtos = null;
        if (service instanceof PersonaDAO){
            dtos = personasEncontradas.stream().map(persona -> profesorMapper.mapProfesor((Profesor) persona)).collect(Collectors.toList());
        } else if (service instanceof AlumnoDAO){
            dtos = personasEncontradas.stream().map(persona -> alumnoMapper.mapAlumno((Alumno) persona)).collect(Collectors.toList());
        }
        else if (service instanceof EmpleadoDTO){
            dtos = personasEncontradas.stream().map(persona -> empleadoMapper.mapEmpleado((Empleado) persona)).collect(Collectors.toList());
        }

        mensaje.put("success", Boolean.TRUE);
        mensaje.put("datos", dtos);
        return ResponseEntity.ok(mensaje);
    }
}
