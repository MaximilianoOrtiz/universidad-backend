package com.springsimplespasos.universidad.universidadbackend.controlador.dto;

import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.AlumnoDAO;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.EmpeladoDAO;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.GenericoDAO;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.ProfesorDAO;
import lombok.AllArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
public class GenericDtoController <E, S extends GenericoDAO<E>>{

    protected final S service;
    protected  final String nombre_entidad;

    public List<E> obtenerTodos(){
        List<E> entidades = null;
        if (service instanceof AlumnoDAO){
           entidades = (List<E>) ((AlumnoDAO)service).buscarTodos();
        }else if (service instanceof ProfesorDAO){
           entidades = (List<E>) ((ProfesorDAO)service).buscarTodos();
        }
        else if (service instanceof EmpeladoDAO){
            entidades = (List<E>) ((EmpeladoDAO)service).buscarTodos();
        }
        else
            entidades = (List<E>) service.findAll();

        return entidades;
    }

    public Optional<E> obtenerPorId(Integer id){
        return service.findById(id);
    }

    public E altaEntidad(E entidad){
        return service.save(entidad);
    }

    @DeleteMapping("/{id}")
    public void deletedEntidad (@PathVariable Integer id){
        service.deleteId(id);
    }

    protected Map<String, Object> obtenerValidaciones(BindingResult result){
        Map<String, Object> validaciones = new HashMap<>();
        result.getFieldErrors()
                .forEach(error -> validaciones.put(error.getField(), error.getDefaultMessage()));
        return  validaciones;
    }
}
