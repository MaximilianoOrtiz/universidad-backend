package com.springsimplespasos.universidad.universidadbackend.repositorios;

import com.springsimplespasos.universidad.universidadbackend.datos.DatosDummy;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Carrera;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Persona;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Profesor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.springsimplespasos.universidad.universidadbackend.datos.DatosDummy.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class ProfesorRepositoryTest {

    @Autowired
    @Qualifier("repositoryProfesor")
    private PersonaReposity personaReposity;

    @Autowired
    private CarreraRepository carreraRepository;

    @Test
    void findProfesoresByCarrera() {

        //Given
        personaReposity.save(DatosDummy.profesor01(false));
        personaReposity.save(DatosDummy.profesor02(false));

        Set<Profesor> profesores = new HashSet<Profesor>();
        Persona p1 = profesor01(true);
        profesores.add((Profesor) p1);
        Persona p2 = profesor02(true);
        profesores.add((Profesor) p2);


        carreraRepository.save(carrera01(false));
        Carrera carrera = carrera01(true);
        carrera.setProfesores(profesores);
        Carrera save = carreraRepository.save(carrera);

        Set<Carrera> carreras = new HashSet<Carrera>();
        carreras.add(carrera01(true));

//        ((Profesor) profesor01(true)).setCarreras(carreras);
//        ((Profesor) profesor02(true)).setCarreras(carreras);

        ((Profesor) p1).setCarreras(carreras);
        ((Profesor) p2).setCarreras(carreras);

        personaReposity.saveAll(Arrays.asList(p1,p2));


        //When
        List<Profesor> expected =(List<Profesor>)((ProfesorRepository) personaReposity).findProfesoresByCarrera("Ingenieria en Sistemas");

        //Then
        assertThat(expected.size() == 2).isTrue();
        assertThat(expected.get(0)).isInstanceOf(Profesor.class);
        assertThat(expected.get(1)).isInstanceOf(Profesor.class);

    }
}