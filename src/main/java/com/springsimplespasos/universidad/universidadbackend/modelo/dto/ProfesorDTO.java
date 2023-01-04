package com.springsimplespasos.universidad.universidadbackend.modelo.dto;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Direccion;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@ApiModel(description = "Profesor  de la universidad", value = "Profesor", reference = "Profesor")
public class ProfesorDTO extends PersonaDTO{

    @Positive(message = "El valor debe ser positivo")
    @NotNull(message = "No se permiten valores nulos")
    @ApiModelProperty(name ="sueldo del profesor", example = "80000")
    private BigDecimal sueldo;

    public ProfesorDTO(Integer id, String nombre, String apellido, String dni, Direccion direccion, BigDecimal sueldo){
        super(id, nombre, apellido, dni, direccion);
        this.sueldo = sueldo;
    }
}
