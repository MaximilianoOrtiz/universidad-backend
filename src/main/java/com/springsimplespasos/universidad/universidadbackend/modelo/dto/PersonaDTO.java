package com.springsimplespasos.universidad.universidadbackend.modelo.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Direccion;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "tipo"
)
/**
 *
 * */
@JsonSubTypes({
        @JsonSubTypes.Type(value = AlumnoDTO.class, name = "alumno"),
        @JsonSubTypes.Type(value = ProfesorDTO.class, name = "profesor"),
        @JsonSubTypes.Type(value = EmpleadoDTO.class, name = "empleado")
})

public abstract class PersonaDTO {

    @ApiModelProperty(name = "Codigo del sistema", example = "5")
    private Integer id;
    @Pattern(regexp = "[a-zA-Z ]{2,254}", message = "El nombre no es valido")
    @NotEmpty(message = "Debe de ingresar un valor")
    @ApiModelProperty(name = "Nombre de la persona", example = "Maximiliano")
    private String nombre;
    @Pattern(regexp = "[a-zA-Z ]{2,254}", message = "El nombre no es valido")
    @NotEmpty(message = "Debe de ingresar un valor")
    @ApiModelProperty(name = "Apellido de la persona", example = "Ortiz")
    private String apellido;
    @Pattern(regexp = "[0-9]+")
    @Size(min = 8, max = 8, message = "Longitud del Dni invalido")
    @NotNull(message = "No se aceptan valores nulos")
    @ApiModelProperty(name = "Dni de la persona", example = "38950548")
    private String dni;
    private Direccion direccion;
}
