package com.springsimplespasos.universidad.universidadbackend.servicios.implementaciones;

import com.springsimplespasos.universidad.universidadbackend.repositorios.ProfesorRepository;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.PabellonDAO;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.ProfesorDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
@SpringBootTest
class ProfesorDAOImplTest {

    @MockBean
    ProfesorDAOImpl profesorRepository;

    @Autowired
    ProfesorDAO profesorDAO;

    @Test
    void findProfesoresByCarrera() {
        profesorDAO.findProfesoresByCarrera(anyString());
        verify(profesorRepository).findProfesoresByCarrera(anyString());
    }
}