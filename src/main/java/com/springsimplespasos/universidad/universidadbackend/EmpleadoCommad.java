package com.springsimplespasos.universidad.universidadbackend;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Direccion;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Empleado;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Persona;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.enumeradores.TipoEmpleado;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.EmpeladoDAO;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.PersonaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class EmpleadoCommad implements CommandLineRunner {

    @Autowired(required = true)
    @Qualifier("empleadoDAOImpl")
    private PersonaDAO personaDAO;

    @Override
    public void run(String... args) throws Exception {

/*
        Direccion direccion = new Direccion("Pergamino", "448", "1888",null, null, "Florencio Varela");

        //Guardamos empleados
        Empleado empleado = new Empleado(null, "Miguel", "Ortiz", " 89568740", direccion, new BigDecimal(45000), TipoEmpleado.MANTENIMIENTO);
        Empleado empleado1 = new Empleado(null, "Mauricio", "Macri", " 89568741", direccion, new BigDecimal(45000), TipoEmpleado.MANTENIMIENTO);
        Empleado empleado2 = new Empleado(null, "Susana", "Portillo", " 89568742", direccion, new BigDecimal(65000), TipoEmpleado.ADMINISTRATIVO);
        Empleado empleado3 = new Empleado(null, "Ramon", "Velazques", " 89568743", direccion, new BigDecimal(65000), TipoEmpleado.ADMINISTRATIVO);
        Empleado empleado4 = new Empleado(null, "Alonso", "Roja", " 89568744", direccion, new BigDecimal(65000), TipoEmpleado.ADMINISTRATIVO);

        Persona persona = personaDAO.save(empleado);
        Persona persona1 = personaDAO.save(empleado1);
        Persona persona2 = personaDAO.save(empleado2);
        Persona persona3 = personaDAO.save(empleado3);
        Persona persona4 = personaDAO.save(empleado4);

        System.out.println(persona);
        System.out.println(persona1);
        System.out.println(persona2);
        System.out.println(persona3);
        System.out.println(persona4);*/
/*

        System.out.println("Tipos de empleados ADMINISTRATIVO");
        Iterable<Persona> empleadoByTipoEmpleadoAdministrativo = ((EmpeladoDAO) personaDAO).findEmpleadoByTipoEmpleado(TipoEmpleado.ADMINISTRATIVO);
        empleadoByTipoEmpleadoAdministrativo.forEach(persona -> System.out.println(persona.toString()));

        System.out.println("Tipos de empleados MANTENIMIENTO");
        Iterable<Persona> empleadoByTipoEmpleadoMantenimiento = ((EmpeladoDAO) personaDAO).findEmpleadoByTipoEmpleado(TipoEmpleado.MANTENIMIENTO);
        empleadoByTipoEmpleadoMantenimiento.forEach(persona -> System.out.println(persona.toString()));



*/

    }
}
