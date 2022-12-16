package com.springsimplespasos.universidad.universidadbackend.controlador;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Carrera;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.CarreraDAO;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Deprecated
@RestController
@RequestMapping("/carreras")
@ConditionalOnProperty(prefix = "app", name = "controller.enable-dto", havingValue = "false")
public class CarreraController  extends  GenericController<Carrera, CarreraDAO>{
    public CarreraController(CarreraDAO service) {
        super(service);
        nombreEntidad = "Carrera";
    }

    /**
    * @PathVariable mapea el codigo que viene en la url al argumento id
    * */
/*
    @GetMapping("/{codigo}")
    public Carrera obtenerPorId(@PathVariable(value = "codigo",required = false) Integer id){
        Optional<Carrera> oCarrera = service.findById(id);
        if (!oCarrera.isPresent()){
            throw new BadRequestExecption(String.format("La carrera con id %d no existe", id));
        }
        return oCarrera.get();
    }
*/

    /**
     *
     * @RequestBody representa en el json un objeto de tipo carrera
     */

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCarrera (@PathVariable Integer id, @Valid @RequestBody Carrera carrera, BindingResult result){
        Map<String,Object> mensaje = new HashMap<>();
        Map<String, Object> validaciones = new HashMap<>();
        if (result.hasErrors()){
            result.getFieldErrors()
                    .forEach(error -> validaciones.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(validaciones);
        }
        Carrera carreraUpdate = null;
        Optional<Carrera> oCarrera = service.findById(id);
        if(!oCarrera.isPresent()){
            //throw new BadRequestExecption(String.format("La carrera con id %d no existe", id));
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("%s con ID %d no existe", nombreEntidad, id));
            return ResponseEntity.badRequest().body(mensaje);
        }
        carreraUpdate = oCarrera.get();
        carreraUpdate.setCantidadAnios(carrera.getCantidadAnios());
        carreraUpdate.setCantidadDeMaterias(carrera.getCantidadDeMaterias());

        mensaje.put("success", Boolean.TRUE);
        mensaje.put("datos",service.save(carreraUpdate));
        return ResponseEntity.ok(mensaje);
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<?> findCarrerasByNombreContainsIgnoresCase(@PathVariable String nombre){
        Map<String,Object> mensaje = new HashMap<>();
        List<Carrera> carreras = (List<Carrera>) service.findCarrerasByNombreContainsIgnoresCase(nombre);
        if (carreras.isEmpty()){
            //throw new BadRequestExecption(String.format("La carrera con nombre %s no existe", nombre));
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("La carrera con nombre %s no existe", nombre));
            return ResponseEntity.badRequest().body(mensaje);

        }
        mensaje.put("success", Boolean.TRUE);
        mensaje.put("datos",carreras);
        return ResponseEntity.ok(mensaje);
    }

    @GetMapping("/cantidad-anios/{cantidadAnios}")
    public ResponseEntity<?> findCarrerasByCantidadAnios(@PathVariable Integer cantidadAnios){
        Map<String,Object> mensaje = new HashMap<>();
        List<Carrera> carrerasPorAnios = (List<Carrera>) service.findCarrerasByCantidadAnios(cantidadAnios);
        if (carrerasPorAnios.isEmpty()){
            //throw new BadRequestExecption(String.format("La carrera con cantidad de años %d no existe", cantidadAnios));
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("La carrera con cantidad de años %d no existe", cantidadAnios));
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("success", Boolean.TRUE);
        mensaje.put("datos",cantidadAnios);
        return ResponseEntity.ok(mensaje);
    }

    @GetMapping("/profesor/{nombre}/apellido/{apellido}")
    public ResponseEntity<?> buscarCarrerasPorProfesorNombreYApellido(@PathVariable String nombre, @PathVariable String apellido){
        Map<String,Object> mensaje = new HashMap<>();
        List<Carrera> carrerasPorProfesor = (List<Carrera>) service.buscarCarrerasPorProfesorNombreYApellido(nombre, apellido);
        if (carrerasPorProfesor.isEmpty()){
            //throw new BadRequestExecption(String.format("Las carreras con el profesor nombre %s, apellido %s, no existe", nombre, apellido));
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("Las carreras con el profesor nombre %s, apellido %s, no existe", nombre, apellido));
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("success", Boolean.TRUE);
        mensaje.put("datos",carrerasPorProfesor);
        return ResponseEntity.ok(mensaje);
    }
}
