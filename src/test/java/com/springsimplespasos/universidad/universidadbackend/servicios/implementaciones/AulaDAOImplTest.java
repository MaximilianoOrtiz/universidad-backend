package com.springsimplespasos.universidad.universidadbackend.servicios.implementaciones;

import com.springsimplespasos.universidad.universidadbackend.datos.DatosDummy;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Aula;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.enumeradores.Pizarron;
import com.springsimplespasos.universidad.universidadbackend.repositorios.AulaRepository;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.AulaDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.springsimplespasos.universidad.universidadbackend.datos.DatosDummy.*;
import static com.springsimplespasos.universidad.universidadbackend.modelo.entidades.enumeradores.Pizarron.PIZARRA_TIZA;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AulaDAOImplTest {

    @Autowired
    AulaDAO aulaDAO;

    @Autowired
    AulaRepository repository;

    @BeforeEach
    void setUp() {
        repository = mock(AulaRepository.class);
        aulaDAO = new AulaDAOImpl(repository);
    }

    @Test
    @DisplayName("TEST_01 -- Busacar Aulas por pizarron")
    void findAulasByPizarron() {
        //Given
        when(repository.findAulasByPizarron(PIZARRA_TIZA))
                .thenReturn(Arrays.asList(aula02(true,2),aula03(true, 3)));
        //When
        List<Aula> expected = (List<Aula>)aulaDAO.findAulasByPizarron(PIZARRA_TIZA);

        //Then
        assertThat(expected.size()==2).isTrue();
        verify(repository).findAulasByPizarron(PIZARRA_TIZA);
    }

    @Test
    @DisplayName("TEST_02 -- Buscar Aulas por nombre del pabellon")
    void findAulaByPabellonNombre() {
        //Given
        String nombrePabellon = "pabellon01";
        when(repository.findAulaByPabellonNombre(nombrePabellon))
                .thenReturn(Arrays.asList(aula02(true, 2), aula03(true, 3)));
        //When

        List<Aula> expected = (List<Aula>) aulaDAO.findAulaByPabellonNombre(nombrePabellon);

        //Then
        assertThat(expected.size() == 2).isTrue();
        verify(repository).findAulaByPabellonNombre(nombrePabellon);
    }

    @Test
    @DisplayName("TEST_03 -- Buscar aula por nro de aula")
    void findAulaByNroAula() {
        //Given
        when(repository.findAulaByNroAula(1)).thenReturn(Optional.of(aula01(true, 1)));

        //When
        Optional<Aula> expected = repository.findAulaByNroAula(1);

        //Then
        assertThat(expected.get().getId()==1).isTrue();
        verify(repository).findAulaByNroAula(1);

    }
}