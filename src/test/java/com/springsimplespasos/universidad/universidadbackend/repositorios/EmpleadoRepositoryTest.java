package com.springsimplespasos.universidad.universidadbackend.repositorios;

import com.springsimplespasos.universidad.universidadbackend.datos.DatosDummy;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Persona;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.enumeradores.TipoEmpleado;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static com.springsimplespasos.universidad.universidadbackend.datos.DatosDummy.*;
import static com.springsimplespasos.universidad.universidadbackend.modelo.entidades.enumeradores.TipoEmpleado.ADMINISTRATIVO;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class EmpleadoRepositoryTest {

    @Autowired
    @Qualifier("repositoryEmpleado")
    private EmpleadoRepository empleadoRepository;

    @Test
    @DisplayName("TEST-01-- Buscar empleados por tipo de empleado")
    void findEmpleadoByTipoEmpleado() {

        //Given
        empleadoRepository.saveAll(Arrays.asList(empleado01(), empleado02()));

        //When
        List<Persona> expected = (List<Persona>)empleadoRepository.findEmpleadoByTipoEmpleado(ADMINISTRATIVO);

        //Then
        assertThat(expected.size() == 1).isTrue();
    }
}