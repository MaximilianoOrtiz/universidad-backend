package com.springsimplespasos.universidad.universidadbackend.modelo.entidades.enumeradores;

import com.springsimplespasos.universidad.universidadbackend.exception.BadRequestExecption;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EnumeradorConverterGeneric {

    public static <T extends Enum<T>> T getEnumFromString(Class<T> c, String string) {
        log.info("INIT - getEnumFromString");
        log.info("pizzarra: " + string);
        log.info("Class: " + c);
        if( c != null && string != null ) {
            try {
                return Enum.valueOf(c, string.toUpperCase());
            } catch(IllegalArgumentException ex) {
                throw new BadRequestExecption(String.format(" %s incorrecto, %s ",c , string));
            }
        }
        log.info("OUT - getEnumFromString");
        return null;
    }
}
