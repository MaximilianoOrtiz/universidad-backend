package com.springsimplespasos.universidad.universidadbackend.modelo.dto;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Direccion;
import io.swagger.annotations.ApiModel;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@ApiModel(description = "Alumno  de la universidad", value = "Alumno", reference = "Alumno")
public class AlumnoDTO extends PersonaDTO{

    public AlumnoDTO(Integer id, String nombre, String apellido, String dni, Direccion direccion) {
        super(id, nombre, apellido, dni, direccion);
    }

}
