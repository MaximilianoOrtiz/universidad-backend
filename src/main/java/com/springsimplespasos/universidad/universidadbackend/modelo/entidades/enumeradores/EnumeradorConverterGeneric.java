package com.springsimplespasos.universidad.universidadbackend.modelo.entidades.enumeradores;

import com.springsimplespasos.universidad.universidadbackend.exception.BadRequestExecption;

public class EnumeradorConverterGeneric {

    public static <T extends Enum<T>> T getEnumFromString(Class<T> c, String string) {
        if( c != null && string != null ) {
            try {
                return Enum.valueOf(c, string.toUpperCase());
            } catch(IllegalArgumentException ex) {
                throw new BadRequestExecption(String.format(" %c incorrecto, %s ", c,string));
            }
        }
        return null;
    }
}
