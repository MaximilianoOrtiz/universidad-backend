package com.springsimplespasos.universidad.universidadbackend.controlador.dto;

import com.springsimplespasos.universidad.universidadbackend.modelo.dto.AulaDTO;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Aula;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.enumeradores.EnumeradorConverterGeneric;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.enumeradores.Pizarron;
import com.springsimplespasos.universidad.universidadbackend.modelo.mapper.mapstruct.AulaMapperMS;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.AulaDAO;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@RestController
@RequestMapping("/aulas")
@ConditionalOnProperty(prefix = "app", name = "controller.enable-dto", havingValue = "true")
@Api(value = "Acciones relacionadas con las aulas de la universidad", tags = "Acciones de Aulas")
public class AulaDtoController extends GenericDtoController<Aula, AulaDAO> {

    @Autowired
    private AulaMapperMS aulaMapperMS;

    public AulaDtoController(AulaDAO service){
        super(service, "Aula");
    }

    @ApiOperation(value = "Alta de aula")
    @ApiResponses({
            @ApiResponse( code = 200, message = "Ejecutado satisfactoriamente") // Costumizamos los codigos de retornos
    })
    @PostMapping
    public ResponseEntity<?> altaAula(@Valid @RequestBody @ApiParam("Aula nueva") AulaDTO aulaDTO, BindingResult result){
        Map<String, Object> mensajes = new HashMap<>();
        if (result.hasErrors()){
            mensajes.put("success", Boolean.FALSE);
            mensajes.put("validaciones", super.obtenerValidaciones(result));
        }
        mensajes.put("success", Boolean.TRUE);
        mensajes.put("data", aulaMapperMS.mapAula(super.altaEntidad(aulaMapperMS.mapAula(aulaDTO))));
        return ResponseEntity.ok(mensajes);
    }

    @ApiOperation(value = "Buscar aulas por nombre del pabellon")
    @ApiResponses({
            @ApiResponse( code = 200, message = "Ejecutado satisfactoriamente") // Costumizamos los codigos de retornos
    })
    @GetMapping("/por-pabellon/{nombrePabellon}")
    public ResponseEntity<?> findAulaByPabellonNombre(@PathVariable @ApiParam(value = "Nombre del pabellon") String nombrePabellon){
        Map<String, Object> mensajes = new HashMap<>();
        List<Aula> aulaByPabellonNombre = (List<Aula>) service.findAulaByPabellonNombre(nombrePabellon);
        if (aulaByPabellonNombre.isEmpty()){
            mensajes.put("success", Boolean.FALSE);
            mensajes.put("mensaje",String.format("No existen aulas en el pabellon, %s   ", nombrePabellon));
            return ResponseEntity.badRequest().body(mensajes);
        }
        List<AulaDTO> dtos = aulaByPabellonNombre.stream().map(aulaMapperMS::mapAula).collect(Collectors.toList());
        mensajes.put("success", Boolean.TRUE);
        mensajes.put("datos", dtos);
        return ResponseEntity.ok(mensajes);
    }

    @ApiOperation(value = "Actualizar Aula")
    @ApiResponses({
            @ApiResponse( code = 200, message = "Ejecutado satisfactoriamente") // Costumizamos los codigos de retornos
    })
    @PutMapping("/{idAula}")
    public ResponseEntity<?> actualizarAula(@PathVariable @ApiParam("Codigo del aula") Integer idAula, @Valid @RequestBody @ApiParam("Datos del aula actualizados") AulaDTO aulaDTO, BindingResult result){
        Map<String, Object> mensajes = new HashMap<>();
        Map<String, Object> validaciones = new HashMap<>();
        if (result.hasErrors()){
            result.getFieldErrors()
                    .forEach(error -> validaciones.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(validaciones);
        }
        Aula aulaUpdate = null;
        Optional<Aula> oAula = service.findById(idAula);
        if (!oAula.isPresent()){
            mensajes.put("success", Boolean.FALSE);
            mensajes.put("mensaje", String.format("No existen aula con el id %d. ", idAula));
            return ResponseEntity.badRequest().body(mensajes);
        }
//        System.out.println("LOG - aula es: " + aulaDTO.toString());
        aulaUpdate = oAula.get();
        aulaUpdate.setCantidadPupitres(aulaDTO.getCantidadPupitres());
        aulaUpdate.setMedidas(aulaDTO.getMedidas());
        //aulaUpdate.setPabellon(aulaDTO.getPabellon());

        mensajes.put("success", Boolean.TRUE);
        mensajes.put("datos", aulaMapperMS.mapAula(service.save(aulaUpdate)));
        return ResponseEntity.ok(mensajes);
    }


    @ApiOperation(value = "Buscar Aulas por pizarron")
    @ApiResponses({
            @ApiResponse( code = 200, message = "Ejecutado satisfactoriamente") // Costumizamos los codigos de retornos
    })
    @GetMapping(value = ("/pizarron/{pizarron}"))
    public ResponseEntity<?> findAulasByPizarron(@PathVariable @ApiParam( name = "pizarron",value = "Tipo de Pizzarron",required = true) String pizarron) {
        log.info(pizarron);
        Map<String, Object> mensajes = new HashMap<>();
        Pizarron cPizarron = EnumeradorConverterGeneric.getEnumFromString(Pizarron.class, pizarron);
        log.info(cPizarron.toString());
        List<Aula> aulasByPizarron = (List<Aula>) service.findAulasByPizarron(cPizarron);
        if (aulasByPizarron.isEmpty()) {
            mensajes.put("success", Boolean.FALSE);
            mensajes.put("mensaje", String.format("No existen aulas con el tipo de pizzarra, %s ", pizarron));
            return ResponseEntity.badRequest().body(mensajes);
        }
        List<AulaDTO> dtos = aulasByPizarron.stream().map(aulaMapperMS::mapAula).collect(Collectors.toList());
        mensajes.put("success", Boolean.TRUE);
        mensajes.put("datos", dtos);
        return ResponseEntity.ok(mensajes);
    }

    @ApiOperation(value = "Buscar por nro de aula")
//    @ApiResponses({
//            @ApiResponse( code = 200, message = "Ejecutado satisfactoriamente") // Costumizamos los codigos de retornos
//    })
    @GetMapping("/nro-Aula/{nroAula}")
    public ResponseEntity<?> findAulaByNroAula(@ApiParam(value = "Nro de Aula") @PathVariable Integer nroAula){
        Map<String, Object> mensajes = new HashMap<>();
        Optional<Aula> aulaByNroAula = service.findAulaByNroAula(nroAula);
        if (!aulaByNroAula.isPresent()){
            mensajes.put("success", Boolean.FALSE);
            mensajes.put("mensaje",String.format("No existen aulas con el nro de Aula: %s,  ", nroAula));
            return ResponseEntity.badRequest().body(mensajes);
        }
        mensajes.put("success", Boolean.TRUE);
        mensajes.put("datos", aulaMapperMS.mapAula(aulaByNroAula.get()));
        return ResponseEntity.ok(mensajes);
    }
}
