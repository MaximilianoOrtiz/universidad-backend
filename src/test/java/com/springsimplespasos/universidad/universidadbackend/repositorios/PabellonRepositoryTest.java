package com.springsimplespasos.universidad.universidadbackend.repositorios;

import com.springsimplespasos.universidad.universidadbackend.datos.DatosDummy;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Pabellon;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static com.springsimplespasos.universidad.universidadbackend.datos.DatosDummy.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class PabellonRepositoryTest {

    @Autowired
    private PabellonRepository pabellonRepository;

    @Test
    @DisplayName("TEST-01 -- Buscar pabellon por nombre")
    void findPabellonByNombre() {
        //Givem
        pabellonRepository.saveAll(Arrays.asList(pabellon01(false), pabellon02(false),pabellon03(false)));

        //When
        List<Pabellon> expected =(List<Pabellon>)pabellonRepository.findPabellonByNombre("pabellon01");

        //Then
        assertThat(expected.size() == 1).isTrue();
        assertThat(expected.get(0).getNombre() == "pabellon01").isTrue();
    }

    @Test
    @DisplayName("TEST-02 -- Buscar pabellon por localidad")
    void findPabellonByDireccionLocalidad() {

        //Given
        pabellonRepository.saveAll(Arrays.asList(pabellon01(false), pabellon02(false),pabellon03(false)));

        //When
        List<Pabellon> expected =(List<Pabellon>)pabellonRepository.findPabellonByDireccionLocalidad("Florencio Varela");

        //Then
        assertThat(expected.size() == 2).isTrue();
        assertThat(expected.get(0).getDireccion().getLocalidad() == "Florencio Varela").isTrue();
        assertThat(expected.get(1).getDireccion().getLocalidad() == "Florencio Varela").isTrue();
    }
}