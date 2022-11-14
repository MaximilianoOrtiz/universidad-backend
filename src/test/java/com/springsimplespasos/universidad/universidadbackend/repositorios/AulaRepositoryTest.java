package com.springsimplespasos.universidad.universidadbackend.repositorios;

import com.springsimplespasos.universidad.universidadbackend.datos.DatosDummy;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.*;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.enumeradores.Pizarron;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.*;

import static com.springsimplespasos.universidad.universidadbackend.datos.DatosDummy.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class AulaRepositoryTest {

    @Autowired
    private  AulaRepository repository;

    @Autowired
    private  PabellonRepository pabellonRepository;

    @BeforeEach
    void setUp() {
        Iterable<Aula> aulas = repository.saveAll(Arrays.asList(aula01(true, 1), aula02(true, 2), aula03(true, 3)));
        aulas.forEach(aula -> System.out.println(aula.toString()));
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
        pabellonRepository.deleteAll();
    }

    @Test
    @DisplayName("buscar aulas por pizzarron")
    void findAulasByPizarron() {

        //When
        List<Aula> expected = (List<Aula>)repository.findAulasByPizarron(Pizarron.PIZARRA_BLANCA);
        expected.forEach(aula -> System.out.println(aula.toString()));

        //Then
        assertThat(expected.size() == 1).isTrue();
        assertThat(expected.get(0)).isInstanceOf(Aula.class);
    }

    @Test
    @DisplayName("buscar aulas por nombre de pabellon")
    void findAulaByPabellonNombre(){
        //Given

        Iterable<Pabellon> pabellons = pabellonRepository.saveAll(Arrays.asList(pabellon01(true), pabellon02(true), pabellon03(true)));
        pabellons.forEach(pabellon -> System.out.println(pabellon.toString()));

        Aula aulaPabellon01_1 = aula03(true, 6);
        aulaPabellon01_1.setPabellon(pabellon01(true));
        Aula aulaPabellon01_2  = aula02(true, 5);
        aulaPabellon01_2.setPabellon(pabellon01(true));

        repository.save(aulaPabellon01_1);
        repository.save(aulaPabellon01_2);

        Set<Aula> aulas01 = new HashSet<Aula>();
        aulas01.add(aulaPabellon01_1);
        aulas01.add(aulaPabellon01_2);
        Pabellon pabellon01_1 = pabellon01(true);
        pabellon01_1.setAulas(aulas01);

        pabellonRepository.save(pabellon01_1);

        //WHEN
        List<Aula> expected = (List<Aula>)repository.findAulaByPabellonNombre("pabellon01");
        expected.forEach(aula -> System.out.println(aula.toString()));

        //THEN
        assertThat(expected.size() == 2).isTrue();
    }

    @Test
    @DisplayName("Buscar Aulas por Numero de aula")
    void findAulaByNroAula() {
        //Given

        repository.save(DatosDummy.aula01(true, 1));

        //When

        Optional<Aula> expected = repository.findAulaByNroAula(1);

        //Then

        assertThat(expected.get().getId()).isEqualTo(1);

    }
}