package com.springsimplespasos.universidad.universidadbackend.modelo.entidades.enumeradoresConverter;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.enumeradores.TipoEmpleado;
import org.springframework.core.convert.converter.Converter;

public class TipoEmpleadoConverter implements Converter<String, TipoEmpleado>{

    @Override
    public TipoEmpleado convert(String source){
        try{
            return TipoEmpleado.valueOf(source.toUpperCase());
        }
        catch (IllegalArgumentException e){
            return null;
        }
    }
}
