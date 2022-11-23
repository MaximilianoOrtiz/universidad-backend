package com.springsimplespasos.universidad.universidadbackend.servicios.implementaciones;

import com.springsimplespasos.universidad.universidadbackend.repositorios.PabellonRepository;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.PabellonDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PabellonDAOImplTest {

    PabellonDAO pabellonDAO;
    @Mock
    PabellonRepository pabellonRepository;

    @BeforeEach
    void setUp() {
        pabellonDAO = new PabellonDAOImpl(pabellonRepository);
    }

    @Test
    void findPabellonByDireccionLocalidad() {
        //Nos salteamos el Given
        //When
        pabellonDAO.findPabellonByDireccionLocalidad(anyString());

        //Then
        verify(pabellonRepository).findPabellonByDireccionLocalidad(anyString());
    }

    @Test
    void findPabellonByNombre() {

        pabellonDAO.findPabellonByNombre(anyString());

        verify(pabellonRepository).findPabellonByNombre(anyString());
    }
}