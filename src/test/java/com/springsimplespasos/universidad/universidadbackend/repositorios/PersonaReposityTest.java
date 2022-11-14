package com.springsimplespasos.universidad.universidadbackend.repositorios;

import com.springsimplespasos.universidad.universidadbackend.datos.DatosDummy;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Empleado;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Persona;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Profesor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.springsimplespasos.universidad.universidadbackend.datos.DatosDummy.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PersonaReposityTest {

    @Autowired
    @Qualifier("repositoryAlumnos")
    private PersonaReposity alumnoRepository;

    @Autowired
    @Qualifier("repositoryEmpleado")
    private PersonaReposity empleadoRepository;

    @Autowired
    @Qualifier("repositoryProfesor")
    private PersonaReposity profesorRepository;

    @Test
    @DisplayName("TEST-01 -- Buscar por nombre y apellido")
    void buscarPorNombreYApellido() {
        //given
        Persona save = empleadoRepository.save(DatosDummy.empleado01());

        //when
        Optional<Persona> expected = empleadoRepository.buscarPorNombreYApellido(DatosDummy.empleado01().getNombre(), DatosDummy.empleado01().getApellido());

        //Then
        assertThat(expected.get()).isInstanceOf(Empleado.class);
        assertThat(expected.get()).isEqualTo(save);
    }

    @Test
    @DisplayName("TEST-02 -- Buscar por dni")
    void buscarPorDni() {

        //Given
        Persona save = profesorRepository.save(profesor01(false));

        //When
        Optional<Persona> expected = profesorRepository.buscarPorDni(profesor01(true).getDni());

        //Then
        assertThat(expected.get()).isInstanceOf(Profesor.class);
        assertThat(expected.get()).isEqualTo(save);
        assertThat(expected.get().getDni()).isEqualTo(save.getDni());
    }

    @Test
    @DisplayName("TEST-03 -- Buscar persona por apellido")
    void buscarPersonaPorApellido() {

        //Given
        Iterable<Persona> personas = alumnoRepository.saveAll(Arrays.asList(
                alumno01(),
                alumno02(),
                alumno03()));

        //Then
        String apellido = "Benitez";
        List<Persona> expected =(List<Persona>)alumnoRepository.buscarPersonaPorApellido(apellido);

        //Then
        assertThat(expected.size()== 2).isTrue();
    }
}