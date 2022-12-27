package com.springsimplespasos.universidad.universidadbackend.modelo.mapper.mapstruct;


import com.springsimplespasos.universidad.universidadbackend.modelo.dto.PabellonDTO;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Pabellon;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PabellonMapperMS {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "mtr2", target = "mtr2"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "direccion", target = "direccion")
    })
    PabellonDTO mapPabellon(Pabellon pabellon);


//    @Mappings({
//            @Mapping(source = "id", target = "id"),
//            @Mapping(source = "mtr2", target = "mtr2"),
//            @Mapping(source = "nombre", target = "nombre"),
//            @Mapping(source = "direccion", target = "direccion")
//    })
    Pabellon mapPabellon(PabellonDTO pabellonDTO);



}
