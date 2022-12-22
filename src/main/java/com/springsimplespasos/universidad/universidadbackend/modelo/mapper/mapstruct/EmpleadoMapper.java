package com.springsimplespasos.universidad.universidadbackend.modelo.mapper.mapstruct;

import com.springsimplespasos.universidad.universidadbackend.modelo.dto.EmpleadoDTO;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Empleado;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = EmpleadoMapperConfig.class)
public abstract class EmpleadoMapper {

    public abstract EmpleadoDTO mapEmpleado(Empleado empleado);

    public  abstract Empleado mapEmpleado(EmpleadoDTO empleadoDTO);

}
