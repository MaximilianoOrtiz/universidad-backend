package com.springsimplespasos.universidad.universidadbackend.modelo.dto;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Direccion;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PabellonDTO {

    private Integer id;
    @Positive(message = "El valor debe de ser positivo")
    private Double mtr2;
    @Pattern(regexp = "[a-zA-Z ]{2,254}", message = "El nombre no es valido")
    private String nombre;
    private Direccion direccion;

}
