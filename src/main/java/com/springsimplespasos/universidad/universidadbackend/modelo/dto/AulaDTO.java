package com.springsimplespasos.universidad.universidadbackend.modelo.dto;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.enumeradores.Pizarron;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ApiModel(description = "Aula de la universidad", value = "Aula", reference = "Aula")
public class AulaDTO {
    @ApiModelProperty(name ="Codigo del aula", example = "8")
    private  Integer id;
    @ApiModelProperty(name ="Nro de aula", example = "10", required = true)
    private Integer nroAula;
    @ApiModelProperty(name ="Medidads", example = "25mt2", required = true )
    private String medidas;
    @ApiModelProperty(name ="Cantidad de pupitres", example = "15")
    private Integer cantidadPupitres;
    @ApiModelProperty(name ="Tipo de pizzarron", example = "PIZARRA_TIZA")
    private Pizarron pizarron;


}
