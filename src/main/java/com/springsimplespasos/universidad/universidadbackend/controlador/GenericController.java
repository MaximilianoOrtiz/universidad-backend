package com.springsimplespasos.universidad.universidadbackend.controlador;

import com.springsimplespasos.universidad.universidadbackend.exception.BadRequestExecption;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Carrera;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.AlumnoDAO;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.EmpeladoDAO;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.GenericoDAO;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.ProfesorDAO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public class GenericController <E,S extends GenericoDAO<E>> {

    protected final S service;
    protected String nombreEntidad;

    public GenericController(S service) {
        this.service = service;
    }

    @GetMapping
    public List<E> obtenerTodos(){
        List<E> listado = null;
        if(nombreEntidad == "Alumno"){
            listado = (List<E>) ((AlumnoDAO)service).buscarTodos();
        }
        if(nombreEntidad == "Profesor") {
            listado = (List<E>) ((ProfesorDAO) service).buscarTodos();
        }

        if(nombreEntidad == "Empleado") {
            listado = (List<E>) ((EmpeladoDAO)service).buscarTodos();
        }
        else{
            listado = (List<E>) service.findAll();
        }
        if(listado.isEmpty()) {
            throw new BadRequestExecption(String.format("No se han encontrado %ss", nombreEntidad));
        }
        return  listado;
    }

    //obtenerPorId(id)
    @GetMapping("/{id}")
    public E obtenerPorId(@PathVariable Integer id){
        Optional<E> entidad = service.findById(id);
        if(!entidad.isPresent())
            throw new BadRequestExecption(String.format("%s con id %d no encontrado", nombreEntidad, id ));
        return entidad.get();
    }

    //borrarEntidadPorId(id)
    @DeleteMapping("/{id}")
    public void borrarPorId(@PathVariable Integer id){
        service.deleteId(id);
    }

    //altaEntidad(Entidad)
    @PostMapping
    public E altaEntidad(@RequestBody E entidad){
        if (entidad.getClass() == Carrera.class )
        {
            if (((Carrera)entidad).getCantidadAnios() < 0){
                throw new BadRequestExecption(String.format("El campo cantidad de años no puede ser negativo"));
            }
            if (((Carrera)entidad).getCantidadDeMaterias() < 0){
                throw new BadRequestExecption(String.format("El campo cantidad de materias no puede ser negativo"));
            }
            return  service.save(entidad);
        }
        else return  service.save(entidad);
    }
}
