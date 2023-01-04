package com.springsimplespasos.universidad.universidadbackend.modelo.dto;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.enumeradores.Pizarron;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AulaDTO {

    private  Integer id;
    private Integer nroAula;
    private String medidas;
    private Integer cantidadPupitres;
    private Pizarron pizarron;


}
