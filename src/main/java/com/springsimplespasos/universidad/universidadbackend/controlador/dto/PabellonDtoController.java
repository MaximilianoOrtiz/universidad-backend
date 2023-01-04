package com.springsimplespasos.universidad.universidadbackend.controlador.dto;

import com.springsimplespasos.universidad.universidadbackend.modelo.dto.PabellonDTO;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Pabellon;
import com.springsimplespasos.universidad.universidadbackend.modelo.mapper.mapstruct.PabellonMapperMS;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.PabellonDAO;
import io.swagger.annotations.*;
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
@RequestMapping("/pabellones")
@ConditionalOnProperty(prefix = "app", name = "controller.enable-dto", havingValue = "true")
@Api(value = "Operaciones sobre Pabellon", tags = "Acciones de Pabellon")
public class PabellonDtoController extends GenericDtoController<Pabellon, PabellonDAO> {

    Logger logger = LoggerFactory.getLogger(PabellonDtoController.class);
    @Autowired
    private PabellonMapperMS pabellonMapperMS;

    public PabellonDtoController(PabellonDAO service) {
        super(service, "Pabellon");
    }

    @ApiOperation(value = "Alta pabellones ")
    @ApiResponses({
            @ApiResponse( code = 200, message = "Ejecutado satisfactoriamente") // Costumizamos los codigos de retornos
    })
    @PostMapping
    public ResponseEntity<?> altaPabellon (@RequestBody @Valid @ApiParam(value = "Datos de alta") PabellonDTO pabellonDTO, BindingResult result){
        logger.info("in - alta Pabellon");
        Map<String,Object> mensaje = new HashMap<>();
        Map<String, Object> validaciones = new HashMap<>();
        if(result.hasErrors()){
            mensaje.put("validaciones", super.obtenerValidaciones(result));
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("success", Boolean.TRUE);
        mensaje.put("data", pabellonMapperMS.mapPabellon(super.service.save(pabellonMapperMS.mapPabellon(pabellonDTO))));
        logger.info("out - alta Pabellon");
        return ResponseEntity.ok(mensaje);
    }

    @ApiOperation(value = "Actualizar pabellon ")
    @ApiResponses({
            @ApiResponse( code = 200, message = "Ejecutado satisfactoriamente") // Costumizamos los codigos de retornos
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> pabellonUpdate(@PathVariable @ApiParam(value = "Id del pabellon") Integer id, @Valid @RequestBody @ApiParam("Datos actualizados") PabellonDTO pabellonDto, BindingResult result){
        Map<String,Object> mensaje = new HashMap<>();
        Map<String, Object> validaciones = new HashMap<>();

        if(result.hasErrors()){
           mensaje.put("validaciones", super.obtenerValidaciones(result));
            return ResponseEntity.badRequest().body(mensaje);
        }

        Pabellon pabellonUpdate = null;
        Optional<Pabellon> oPabellon = service.findById(id);
        if (!oPabellon.isPresent()){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("success", String.format("No existe pabellon con el id %d", id));
            return  ResponseEntity.badRequest().body(mensaje);
        }
        pabellonUpdate = oPabellon.get();
        //pabellonUpdate.setAulas(pabellonDto.getAulas());
        pabellonUpdate.setNombre(pabellonDto.getNombre());
        pabellonUpdate.setMtr2(pabellonDto.getMtr2());
        pabellonUpdate.setDireccion(pabellonDto.getDireccion());

        mensaje.put("success", Boolean.TRUE);
        mensaje.put("datos", pabellonMapperMS.mapPabellon(service.save(pabellonUpdate)));
        return  ResponseEntity.ok(mensaje);
    }


    @ApiOperation(value = "Buscar pabellones por localidad")
    @ApiResponses({
            @ApiResponse( code = 200, message = "Ejecutado satisfactoriamente") // Costumizamos los codigos de retornos
    })
    @GetMapping("/localidad/{localidad}")
    public ResponseEntity<?> findPabellonByDireccionLocalidad(@PathVariable @ApiParam(example = "Florencio Varela") String localidad){
        logger.info("in - buscar pabellon por localidad");
        Map<String,Object> mensaje = new HashMap<>();
        List<Pabellon> pabellonByDireccionLocalidad = (List<Pabellon>) service.findPabellonByDireccionLocalidad(localidad);
        if (pabellonByDireccionLocalidad.isEmpty()){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No existen pabellones con la localidad ", localidad));
            return ResponseEntity.badRequest().body(mensaje);
        }
        List<PabellonDTO> dtos = (List<PabellonDTO>) pabellonByDireccionLocalidad
                .stream()
                .map(pabellonMapperMS::mapPabellon)
                .collect(Collectors.toList());
        mensaje.put("success", Boolean.TRUE);
        mensaje.put("datos", dtos);
        logger.info("out - buscar pabellon por localidad");
        return ResponseEntity.ok(mensaje);
    }
}
