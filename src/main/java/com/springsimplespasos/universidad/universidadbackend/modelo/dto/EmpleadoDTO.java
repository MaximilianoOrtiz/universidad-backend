package com.springsimplespasos.universidad.universidadbackend.modelo.dto;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Direccion;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.enumeradores.TipoEmpleado;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@ApiModel(description = "Empleado de la universidad", value = "Empleado", reference = "Empleado")
public class EmpleadoDTO extends PersonaDTO{

    @NotNull(message = "debe ingresar un valor")
    @ApiModelProperty(name ="Sueldo del empleado", example = "80000")
    private BigDecimal sueldo;
    @NotNull(message = "debe ingresar un valor")
    @ApiModelProperty(name ="Tipo de empleado", example = "ADMINISTRACION")
    private TipoEmpleado tipoEmpleado;

    public EmpleadoDTO(Integer id, String nombre, String apellido, String dni, Direccion direccion, BigDecimal sueldo, TipoEmpleado tipoEmpleado) {
        super(id, nombre, apellido, dni, direccion);
        this.sueldo = sueldo;
        this.tipoEmpleado = tipoEmpleado;
    }
}
