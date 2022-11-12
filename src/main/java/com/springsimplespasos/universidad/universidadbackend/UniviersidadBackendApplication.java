package com.springsimplespasos.universidad.universidadbackend;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Alumno;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Direccion;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Persona;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.enumeradores.Pizarron;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.enumeradores.TipoEmpleado;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.AlumnoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.security.spec.RSAOtherPrimeInfo;
import java.util.List;

@SpringBootApplication
public class UniviersidadBackendApplication {
/*

	@Autowired
	private AlumnoDAO servicio;
*/

	public static void main(String[] args) {
		String[] beanDefinitionNames = SpringApplication.run(UniviersidadBackendApplication.class, args).getBeanDefinitionNames();
		/*for (String str : beanDefinitionNames ){
			System.out.println(str);
		}*/
	}
/*
	@Bean
	public CommandLineRunner runner(){
		return args -> {
		*//*Direccion direccion =  new Direccion("calle nueva", "321", "1623", "", "","");
		Persona alumno = new Alumno(null, "Jorge", "Mero", "22222222", direccion);

		Persona save = servicio.save(alumno);
		System.out.println(save.toString());

		List<Persona> alumnos = (List<Persona>) servicio.findAll();
		alumnos.forEach(System.out::println);*//*

			Direccion direccion = new Direccion("Pergamino", "123", "1623", "", "", "Junin");
			Persona alumno = new Alumno(null,"RAul", "Perez", "11121212", direccion);
			Persona save = servicio.save(alumno);
			System.out.println(save.toString());

		};
	}*/
}
