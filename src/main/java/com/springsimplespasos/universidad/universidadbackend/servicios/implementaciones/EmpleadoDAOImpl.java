package com.springsimplespasos.universidad.universidadbackend.servicios.implementaciones;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Persona;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.enumeradores.TipoEmpleado;
import com.springsimplespasos.universidad.universidadbackend.repositorios.EmpleadoRepository;
import com.springsimplespasos.universidad.universidadbackend.repositorios.PersonaReposity;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.EmpeladoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmpleadoDAOImpl extends PersonaDAOImpl implements EmpeladoDAO {

    @Autowired
    public EmpleadoDAOImpl(@Qualifier("repositoryEmpleado") PersonaReposity repository) {
        super(repository);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Persona> findEmpleadoByTipoEmpleado(TipoEmpleado tipoEmpleado) {
        return ((EmpleadoRepository)repository).findEmpleadoByTipoEmpleado(tipoEmpleado);
    }

    @Override
    public Iterable<Persona> buscarTodos() {
        return ((EmpleadoRepository)repository).buscarTodos();
    }
}
