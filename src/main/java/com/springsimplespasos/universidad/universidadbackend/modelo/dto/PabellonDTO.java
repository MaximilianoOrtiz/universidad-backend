package com.springsimplespasos.universidad.universidadbackend.modelo.dto;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Direccion;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ApiModel(description = "Pabellon de la universidad", value = "Pabellon", reference = "Pabellon")
public class PabellonDTO {
    @ApiModelProperty(name ="Codigo del aula", example = "8")
    private Integer id;
    @ApiModelProperty(name ="Dimenciones del pabellon", example = "60mt2")
    @Positive(message = "El valor debe de ser positivo")
    private Double mtr2;
    @Pattern(regexp = "[a-zA-Z ]{2,254}", message = "El nombre no es valido")
    @ApiModelProperty(name ="Nombre del pabellon", example = "pabellonX")
    private String nombre;
    @ApiModelProperty(name ="Direccion del pabellon")
    private Direccion direccion;

}
