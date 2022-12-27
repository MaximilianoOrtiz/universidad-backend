package com.springsimplespasos.universidad.universidadbackend.modelo.mapper.mapstruct;

import com.springsimplespasos.universidad.universidadbackend.modelo.dto.AulaDTO;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Aula;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AulaMapperMS {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "nroAula", target = "nroAula"),
            @Mapping(source = "medidas", target = "medidas"),
            @Mapping(source = "cantidadPupitres", target = "cantidadPupitres")
    })
    AulaDTO mapAula(Aula aula);

    Aula mapAula(AulaDTO aulaDTO);
}



