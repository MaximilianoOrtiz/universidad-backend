package com.springsimplespasos.universidad.universidadbackend.controlador.dto;

import com.springsimplespasos.universidad.universidadbackend.modelo.dto.CarreraDTO;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Carrera;
import com.springsimplespasos.universidad.universidadbackend.modelo.mapper.mapstruct.CarreraMapperMS;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.CarreraDAO;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@Slf4j
@RequestMapping("carreras")
@ConditionalOnProperty(prefix = "app", name = "controller.enable-dto", havingValue = "true")
@Api(value = "Acciones relacionadas con las carreras", tags = "Acciones sobre Carreras") // anotaciones para swagger, costumizando el titulo del controlador
public class CarreraDtoController extends GenericDtoController<Carrera, CarreraDAO>{

/*    @Autowired
    private CarreraDAO carreraDAO;
*/

    Logger logger = LoggerFactory.getLogger(CarreraDtoController.class);

    @Autowired
    private CarreraMapperMS mapperMS;

    public CarreraDtoController(CarreraDAO service) {
        super(service, "Carrera");
    }

    @GetMapping
    @ApiOperation(value = "Consultar todas las Carreras") // Modificamos el nombre de la operacion
    @ApiResponses({
            @ApiResponse( code = 200, message = "Ejecutado satisfactoriamente") // Costumizamos los codigos de retornos
    })
    public ResponseEntity<?> obtenerCarreras(){
        logger.info("Init - obtener Carreras");

        Map<String, Object> mensaje = new HashMap<>();
        List<Carrera> carreras = super.obtenerTodos();
        if (carreras.isEmpty()){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No se encontraron las %ss cargadas", nombre_entidad));
        }
        carreras.forEach(carrera -> log.info(carrera.toString()));
        List<CarreraDTO> carreraDTOS = carreras
                .stream()
                .map(mapperMS::mapCarrera)
                .collect(Collectors.toList());
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("data", carreraDTOS);
        logger.info("Out - obtener Carreras");
        return  ResponseEntity.ok(mensaje);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Actualizar carrera") // Modificamos el nombre de la operacion
    @ApiResponses({
            @ApiResponse( code = 200, message = "Ejecutado satisfactoriamente") // Costumizamos los codigos de retornos
    })
    public ResponseEntity<?> actualizarCarrera (@PathVariable Integer id, @Valid @RequestBody @ApiParam(value = "Carrera actualizada") CarreraDTO carrera, BindingResult result){
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
        logger.info("Out - obtener Carreras");
        return ResponseEntity.ok(mensaje);
    }

    @ApiOperation(value = "Buscar carreras por nombre")
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<?> findCarrerasByNombreContainsIgnoresCase(@PathVariable @ApiParam(value = "Nombre de la carrera") String nombre){
        logger.info("Init - find carrera by nombre");
        Map<String,Object> mensaje = new HashMap<>();
        List<Carrera> carreras = (List<Carrera>) service.findCarrerasByNombreContainsIgnoresCase(nombre);
        logger.info("carreras:");
        carreras.forEach(carrera -> logger.info(carrera.toString()));
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
        logger.info("Out - find carrera by nombre");
        return ResponseEntity.ok(mensaje);
    }

    @ApiOperation(value = "Buscar carreras por cantidad de años")
    @GetMapping("/cantidad-anios/{cantidadAnios}")
    public ResponseEntity<?> findCarrerasByCantidadAnios(@PathVariable @ApiParam(value = "Duraciòn total en años de la carrera") Integer cantidadAnios){
        logger.info("Init - find carrera by cantidad de años");
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
    @ApiOperation(value = "Buscar carreras por nombre y apellido del profesor")
    public ResponseEntity<?> buscarCarrerasPorProfesorNombreYApellido(@PathVariable @ApiParam(value = "Nombre del profesor") String nombre, @PathVariable @ApiParam(value = "Apellido del profesor") String apellido){
       log.info("Init - Buscar carreras por nombre y apellido del profesor");
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
        log.info("Out - Buscar carreras por nombre y apellido del profesor");
        return ResponseEntity.ok(mensaje);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Consultar carrera por codigo") // Modificamos el nombre de la operacion
    @ApiResponses({
            @ApiResponse( code = 200, message = "Ejecutado satisfactoriamente") // Costumizamos los codigos de retornos
    })
    public ResponseEntity<?> obtenerCarrreraPorId(@PathVariable @ApiParam(value = "codigo del sistema") Integer id){
        log.info("INIT - Obtener carrera por id");
        Map<String, Object> mensaje = new HashMap<>();
        Optional<Carrera> oCarrera = service.findById(id);
        if (!oCarrera.isPresent()){
            log.info("is Present: " + Boolean.FALSE);
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No existe la %s con id %d", nombre_entidad, id));
        }
        log.info("is Present: " + Boolean.TRUE);
        mensaje.put("success", Boolean.TRUE);
        mensaje.put("data", mapperMS.mapCarrera(oCarrera.get()));
        log.info("OUT - Obtener carrera por id");
        return ResponseEntity.ok(mensaje);
    }

    @PostMapping
    @ApiOperation(value = "Dar de alta una carrera")
    public ResponseEntity<?> altaCarrera(@RequestBody @ApiParam(name = "Carrera de la universidad") CarreraDTO carreraDTO){
        log.info("INIT - alta de carrera");
        Map<String, Object> mensaje = new HashMap<>();
        Carrera save = super.altaEntidad((mapperMS.mapCarrera(carreraDTO)));
        CarreraDTO dto = mapperMS.mapCarrera(save);
        log.info(dto.toString());
        mensaje.put("success", Boolean.TRUE);
        mensaje.put("data", dto);
        log.info("OUT - alta de carrera");
        return ResponseEntity.ok(dto);
    }
}
