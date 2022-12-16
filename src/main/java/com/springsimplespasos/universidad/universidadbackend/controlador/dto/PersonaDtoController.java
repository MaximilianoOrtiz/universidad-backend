package com.springsimplespasos.universidad.universidadbackend.controlador.dto;

import com.springsimplespasos.universidad.universidadbackend.modelo.dto.PersonaDTO;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Alumno;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Empleado;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Persona;
import com.springsimplespasos.universidad.universidadbackend.modelo.mapper.mapstruct.AlumnoMapper;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.PersonaDAO;

public class PersonaDtoController extends GenericDtoController<Persona, PersonaDAO>{

    protected final AlumnoMapper alumnoMapper;
    
    public PersonaDtoController(PersonaDAO service, String nombre_entidad, AlumnoMapper alumnoMapper) {
        super(service, nombre_entidad);
        this.alumnoMapper = alumnoMapper;
    }
    
    public PersonaDTO altaPersona(Persona persona) {
        Persona personaEntidad = super.altaEntidad(persona);
        PersonaDTO dto = null;
        if (personaEntidad instanceof Alumno) {
            dto = alumnoMapper.mapAlumno((Alumno) personaEntidad);
        } else if (personaEntidad instanceof Persona) {
            //aplicamos el mapper de profesor
        } else if (personaEntidad instanceof Empleado) {
            //aplicamos el mapper de profesor
        }
        return dto;
    }
}
