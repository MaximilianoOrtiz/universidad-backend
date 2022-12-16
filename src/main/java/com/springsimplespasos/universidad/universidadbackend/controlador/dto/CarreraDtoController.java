package com.springsimplespasos.universidad.universidadbackend.controlador.dto;

import com.springsimplespasos.universidad.universidadbackend.modelo.dto.CarreraDTO;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Carrera;
import com.springsimplespasos.universidad.universidadbackend.modelo.mapper.mapstruct.CarreraMapperMS;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.CarreraDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("carreras")
@ConditionalOnProperty(prefix = "app", name = "controller.enable-dto", havingValue = "true")
public class CarreraDtoController extends GenericDtoController<Carrera, CarreraDAO>{

/*    @Autowired
    private CarreraDAO carreraDAO;
*/
    @Autowired
    private CarreraMapperMS mapperMS;

    public CarreraDtoController(CarreraDAO service) {
        super(service, "Carrera");
    }

    @GetMapping
    public ResponseEntity<?> obtenerCarreras(){
        Map<String, Object> mensaje = new HashMap<>();
        List<Carrera> carreras = super.obtenerTodos();
        if (carreras.isEmpty()){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No se encontraron las %ss cargadas", nombre_entidad));
        }

        List<CarreraDTO> carreraDTOS = carreras
                .stream()
                .map(mapperMS::mapCarrera)
                .collect(Collectors.toList());

        mensaje.put("success",Boolean.TRUE);
        mensaje.put("data", carreraDTOS);

        return  ResponseEntity.ok(mensaje);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCarrera (@PathVariable Integer id, @Valid @RequestBody CarreraDTO carrera, BindingResult result){
        Map<String,Object> mensaje = new HashMap<>();
        System.out.println("Init- ActualizarCarrera");

        if (result.hasErrors()){
            System.out.println("Init- hasError");
            mensaje.put("success", Boolean.TRUE);
            mensaje.put("validaciones", super.obtenerValidaciones(result));
            return ResponseEntity.badRequest().body(mensaje);
        }
        Carrera carreraUpdate = null;
        Optional<Carrera> oCarrera = service.findById(id);
        if(!oCarrera.isPresent()){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("%s con ID %d no existe", nombre_entidad, id));
            return ResponseEntity.badRequest().body(mensaje);
        }
        carreraUpdate = oCarrera.get();
        carreraUpdate.setCantidadAnios(carrera.getCantidad_anios());
        carreraUpdate.setCantidadDeMaterias(carrera.getCantidad_materias());
        Carrera save = service.save(carreraUpdate);
        mensaje.put("success", Boolean.TRUE);
        mensaje.put("datos",mapperMS.mapCarrera(save));
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
        List<CarreraDTO> carreraDTOS = carreras
                        .stream()
                        .map(mapperMS::mapCarrera)
                        .collect(Collectors.toList());

        mensaje.put("success", Boolean.TRUE);
        mensaje.put("datos",carreraDTOS);
        return ResponseEntity.ok(mensaje);
    }

    @GetMapping("/cantidad-anios/{cantidadAnios}")
    public ResponseEntity<?> findCarrerasByCantidadAnios(@PathVariable Integer cantidadAnios){
        Map<String,Object> mensaje = new HashMap<>();
        List<Carrera> carrerasPorAnios = (List<Carrera>) service.findCarrerasByCantidadAnios(cantidadAnios);
        if (carrerasPorAnios.isEmpty()){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("La carrera con cantidad de años %d no existe", cantidadAnios));
            return ResponseEntity.badRequest().body(mensaje);
        }
        List<CarreraDTO> carreraDTOS = carrerasPorAnios
                .stream()
                .map(mapperMS::mapCarrera)
                .collect(Collectors.toList());

        mensaje.put("success", Boolean.TRUE);
        mensaje.put("datos",carreraDTOS);
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
        List<CarreraDTO> carreraDTOS = carrerasPorProfesor
                .stream()
                .map(mapperMS::mapCarrera)
                .collect(Collectors.toList());

        mensaje.put("success", Boolean.TRUE);
        mensaje.put("datos",carreraDTOS);
        return ResponseEntity.ok(mensaje);
    }
}
