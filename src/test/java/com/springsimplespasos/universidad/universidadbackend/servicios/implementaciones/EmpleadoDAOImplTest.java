package com.springsimplespasos.universidad.universidadbackend.servicios.implementaciones;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.enumeradores.TipoEmpleado;
import com.springsimplespasos.universidad.universidadbackend.repositorios.EmpleadoRepository;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.EmpeladoDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static com.springsimplespasos.universidad.universidadbackend.modelo.entidades.enumeradores.TipoEmpleado.MANTENIMIENTO;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringBootTest
class EmpleadoDAOImplTest {

    @MockBean
    EmpleadoRepository empleadoRepository;

    @Autowired
    EmpeladoDAO empeladoDAO;

    @Test
    void findEmpleadoByTipoEmpleado() {
        empeladoDAO.findEmpleadoByTipoEmpleado(MANTENIMIENTO);
        verify(empleadoRepository).findEmpleadoByTipoEmpleado(MANTENIMIENTO);
    }
}