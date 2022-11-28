package com.springsimplespasos.universidad.universidadbackend.controlador;

import com.springsimplespasos.universidad.universidadbackend.exception.BadRequestExecption;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Carrera;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.CarreraDAO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carreras")
public class CarreraController  extends  GenericController<Carrera, CarreraDAO>{
    public CarreraController(CarreraDAO service) {
        super(service);
        nombreEntidad = "Carrera";
    }

    /**
    * @PathVariable mapea el codigo que viene en la url al argumento id
    * */
    @GetMapping("/{codigo}")
    public Carrera obtenerPorId(@PathVariable(value = "codigo",required = false) Integer id){
        Optional<Carrera> oCarrera = service.findById(id);
        if (!oCarrera.isPresent()){
            throw new BadRequestExecption(String.format("La carrera con id %d no existe", id));
        }
        return oCarrera.get();
    }

    /**
     *
     * @RequestBody representa en el json un objeto de tipo carrera
     */

    @PutMapping("/{id}")
    public Carrera actualizarCarrera (@PathVariable Integer id, @RequestBody Carrera carrera){
        Carrera carreraUpdate = null;
        Optional<Carrera> oCarrera = service.findById(id);
        if(!oCarrera.isPresent()){
            throw new BadRequestExecption(String.format("La carrera con id %d no existe", id));
        }
        carreraUpdate = oCarrera.get();
        carreraUpdate.setCantidadAnios(carrera.getCantidadAnios());
        carreraUpdate.setCantidadDeMaterias(carrera.getCantidadDeMaterias());
        return service.save(carreraUpdate);
    }

    @GetMapping("/nombre/{nombre}")
    public Iterable<Carrera> findCarrerasByNombreContainsIgnoresCase(@PathVariable String nombre){
        List<Carrera> carreras = (List<Carrera>) service.findCarrerasByNombreContainsIgnoresCase(nombre);
        if (carreras.isEmpty()){
            throw new BadRequestExecption(String.format("La carrera con nombre %s no existe", nombre));
        }
        return carreras;
    }

    @GetMapping("/cantidad-anios/{cantidadAnios}")
    public List<Carrera> findCarrerasByCantidadAnios(@PathVariable Integer cantidadAnios){
        List<Carrera> carrerasPorAnios = (List<Carrera>) service.findCarrerasByCantidadAnios(cantidadAnios);
        if (carrerasPorAnios.isEmpty()){
            throw new BadRequestExecption(String.format("La carrera con cantidad de años %d no existe", cantidadAnios));
        }
        return carrerasPorAnios;
    }

    @GetMapping("/profesor/{nombre}/apellido/{apellido}")
    public List<Carrera> buscarCarrerasPorProfesorNombreYApellido(@PathVariable String nombre, @PathVariable String apellido){
        List<Carrera> carrerasPorProfesor = (List<Carrera>) service.buscarCarrerasPorProfesorNombreYApellido(nombre, apellido);
        if (carrerasPorProfesor.isEmpty()){
            throw new BadRequestExecption(String.format("Las carreras con el profesor nombre %s, apellido %s, no existe", nombre, apellido));
        }
        return carrerasPorProfesor;
    }
}
