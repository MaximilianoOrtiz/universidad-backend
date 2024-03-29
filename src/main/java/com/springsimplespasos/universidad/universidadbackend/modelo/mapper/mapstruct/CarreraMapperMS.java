package com.springsimplespasos.universidad.universidadbackend.modelo.mapper.mapstruct;

import com.springsimplespasos.universidad.universidadbackend.modelo.dto.CarreraDTO;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Carrera;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CarreraMapperMS {

    @Mappings({
            @Mapping(source = "id", target = "codigo"),
            @Mapping(source = "cantidadDeMaterias", target = "cantidad_materias"),
            @Mapping(source = "cantidadAnios", target = "cantidad_anios"),
    })
    CarreraDTO mapCarrera (Carrera carrera);

    @Mappings({
            @Mapping(source = "codigo", target = "id"),
            @Mapping(source = "cantidad_materias", target = "cantidadDeMaterias"),
            @Mapping(source = "cantidad_anios", target = "cantidadAnios"),
    })
    Carrera mapCarrera ( CarreraDTO carreraDTO);
}
