package com.springsimplespasos.universidad.universidadbackend.controlador;

import com.springsimplespasos.universidad.universidadbackend.exception.BadRequestExecption;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.GenericoDAO;
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
        List<E> listado = (List<E>) service.findAll();
        if(listado.isEmpty()){
            throw new BadRequestExecption(String.format("No se han encontrado %ss", nombreEntidad));
        }
        return  listado;
    }

    //obtenerPorId(id)
    @GetMapping("/{id}")
    public E obtenerPorId(@PathVariable Integer id){
        Optional<E> entidad = service.findById(id);
        if(!entidad.isPresent())
            throw new BadRequestExecption(String.format(nombreEntidad, " con id %d no encontrado"));
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
        return  service.save(entidad);
    }

}
