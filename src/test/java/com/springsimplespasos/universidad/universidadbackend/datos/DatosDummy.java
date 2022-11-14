package com.springsimplespasos.universidad.universidadbackend.datos;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.*;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.enumeradores.Pizarron;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.enumeradores.TipoEmpleado;

import java.math.BigDecimal;

import static com.springsimplespasos.universidad.universidadbackend.modelo.entidades.enumeradores.Pizarron.PIZARRA_BLANCA;
import static com.springsimplespasos.universidad.universidadbackend.modelo.entidades.enumeradores.Pizarron.PIZARRA_TIZA;
import static com.springsimplespasos.universidad.universidadbackend.modelo.entidades.enumeradores.TipoEmpleado.*;

public class DatosDummy {

    public static Carrera carrera01(boolean conId){
        Carrera carrera = (conId) ? new Carrera(1, "Ingenieria en Sistemas", 50, 5):
                new Carrera(null, "Ingenieria en Sistemas", 50, 5);
        return carrera;
    }

    public static Carrera carrera02(boolean conId) {
        Carrera carrera = (conId) ? new Carrera(2, "Licenciatura en Sistemas", 45, 4):
                new Carrera(null, "Licenciatura en Sistemas", 45, 4);
        return carrera;
    }

    public static Carrera carrera03(boolean conId){
        Carrera carrera = (conId) ? new Carrera(3, "Ingenieria Industrial", 45, 4):
                new Carrera(null, "Ingenieria Industrial", 45, 4);
        return carrera;
    }

    public static Persona empleado01(){
        return  new Empleado(null,"Lautaro", "Lopez", "25174036", new Direccion(), new BigDecimal("46750.70"), ADMINISTRATIVO);
    }

    public static Persona empleado02(){
        return  new Empleado(null,"Leandro", "Lopez", "25174630", new Direccion(), new BigDecimal("46750.70"), MANTENIMIENTO);
    }

    public static Persona profesor01(boolean conId){
        Persona profesor = (conId) ? new Profesor(1,"Martin", "Lugone", "65896587", new Direccion(), new BigDecimal("60000.00")):
                new Profesor(null,"Martin", "Lugone", "65896587", new Direccion(), new BigDecimal("60000.00"));
        return  profesor;
    }

    public static Persona alumno01(){
        return  new Alumno(null, "Jhon", "Benitez", "45233715", new Direccion());
    }

    public static Persona alumno02(){
        return  new Alumno(null, "Andres", "Benitez", "45233891", new Direccion());
    }
    public static Persona alumno03(){
        return  new Alumno(null, "Joaquin", "Leon", "45233012", new Direccion());
    }

    public static Persona profesor02(boolean conId){
        Persona profesor = (conId) ? new Profesor(2,"Pablo", "Paul", "33908586", new Direccion(), new BigDecimal("60000.00")):
            new Profesor(null,"Pablo", "Paul", "33908586", new Direccion(), new BigDecimal("60000.00"));
        return  profesor;
    }

    public static Aula aula01(boolean conId, int id){
        Aula aula = (conId) ?  new Aula(id, 1,"25mts2", 15, PIZARRA_BLANCA):
                new Aula(null, 1,"25mts2", 15, PIZARRA_BLANCA);
        return aula;
    }

    public static Aula aula02(boolean conId, int id){
        Aula aula = (conId) ?  new Aula(id, 2,"25mts2", 15, PIZARRA_TIZA):
                new Aula(null, 2,"25mts2", 15, PIZARRA_TIZA);
        return aula;
    }

    public static Aula aula03(boolean conId, int id){
        Aula aula = (conId) ?  new Aula(id, 3,"25mts2", 15, PIZARRA_TIZA):
                new Aula(null, 3,"25mts2", 15, PIZARRA_TIZA);
        return aula;
    }

    public  static Pabellon pabellon01(boolean conId) {
        Pabellon pabellon = (conId) ? new Pabellon(1, 95.2, "pabellon01", new Direccion(null,null,null,null,null, "Florencio Varela")):
                new Pabellon(null, 95.2, "pabellon01", new Direccion(null,null,null,null,null, "Florencio Varela"));
        return pabellon;
    }

    public  static Pabellon pabellon02(boolean conId) {
        Pabellon pabellon = (conId) ? new Pabellon(2, 95.2, "pabellon02", new Direccion(null,null,null,null,null, "Florencio Varela")):
                new Pabellon(null, 95.2, "pabellon02", new Direccion(null,null,null,null,null, "Florencio Varela"));
        return pabellon;
    }

    public  static Pabellon pabellon03(boolean conId) {
        Pabellon pabellon = (conId) ? new Pabellon(3, 95.2, "pabellon03", new Direccion(null,null,null,null,null, "Berazategui")):
                new Pabellon(null, 95.2, "pabellon03",  new Direccion(null,null,null,null,null, "Berazategui"));
        return pabellon;
    }

}
