package com.springsimplespasos.universidad.universidadbackend.controlador.dto;

import com.springsimplespasos.universidad.universidadbackend.modelo.dto.EmpleadoDTO;
import com.springsimplespasos.universidad.universidadbackend.modelo.dto.PersonaDTO;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Empleado;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Pabellon;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Persona;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.enumeradores.EnumeradorConverterGeneric;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.enumeradores.TipoEmpleado;
import com.springsimplespasos.universidad.universidadbackend.modelo.mapper.mapstruct.EmpleadoMapper;
import com.springsimplespasos.universidad.universidadbackend.modelo.mapper.mapstruct.PabellonMapperMS;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.EmpeladoDAO;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.PabellonDAO;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.PersonaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
@RequestMapping("/empleados")
@ConditionalOnProperty(prefix = "app", name = "controller.enable-dto", havingValue = "true")
public class EmpleadoDtoController extends PersonaDtoController{

    @Autowired
    private PabellonDAO pabellonDAO;
    @Autowired
    private PabellonMapperMS mapperMS;

    public EmpleadoDtoController(@Qualifier("empleadoDAOImpl") PersonaDAO service, EmpleadoMapper empleadoMapper) {
        super(service, "Empleado", empleadoMapper, null, null);
        System.out.println("entro en empleados");
    }

    @GetMapping(value = "/tipo/{tipoEmpleado}")
    public ResponseEntity<?> findEmpleadoByTipoEmpleado(@PathVariable(required = false) String tipoEmpleado){
        Map<String, Object> mensaje = new HashMap<>();
        TipoEmpleado cTipoEmpleado = EnumeradorConverterGeneric.getEnumFromString(TipoEmpleado.class, tipoEmpleado);
        List<Persona> empleadoByTipoEmpleado = (List<Persona>) ((EmpeladoDAO) service).findEmpleadoByTipoEmpleado((cTipoEmpleado));
        if (empleadoByTipoEmpleado.isEmpty()){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No se encontraron %s del tipo, %s ", nombre_entidad ,tipoEmpleado));
            return  ResponseEntity.badRequest().body(mensaje);
        }
        List<PersonaDTO> dtos = empleadoByTipoEmpleado.stream().map(persona -> empleadoMapper.mapEmpleado((Empleado) persona)).collect(Collectors.toList());
        mensaje.put("success", Boolean.TRUE);
        mensaje.put("datos", dtos);
        return  ResponseEntity.ok(mensaje);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateEmpleado(@PathVariable Integer id, @Valid @RequestBody EmpleadoDTO empleado, BindingResult result){
        Map<String, Object> mensaje = new HashMap<>();
        Map<String, Object> validaciones = new HashMap<>();
        if(result.hasErrors()){
            mensaje.put("succsess", Boolean.FALSE);
            mensaje.put("validaciones", super.obtenerValidaciones(result));
            return ResponseEntity.badRequest().body(mensaje);
        }
        Persona empleadoUpdate = null;
        Optional<Persona> oEmpleado= service.findById(id);
        if (!oEmpleado.isPresent()){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("success", String.format("No empleado con id %d no existe", id));
            return  ResponseEntity.badRequest().body(mensaje);
        }
        empleadoUpdate = oEmpleado.get();
        empleadoUpdate.setNombre(empleado.getNombre());
        empleadoUpdate.setApellido((empleado.getApellido()));
        empleadoUpdate.setDni(empleado.getDni());
        empleadoUpdate.setDireccion(empleado.getDireccion());
        ((Empleado)empleadoUpdate).setTipoEmpleado(((EmpleadoDTO)empleado).getTipoEmpleado());
        ((Empleado)empleadoUpdate).setSueldo(((EmpleadoDTO)empleado).getSueldo());

        mensaje.put("success", Boolean.TRUE);
        mensaje.put("datos", empleadoMapper.mapEmpleado((Empleado) service.save(empleadoUpdate)));
        return  ResponseEntity.badRequest().body(mensaje);
    }

    @PutMapping("/{idEmpleado}/pabellon/{idPabellon}")
    public ResponseEntity<?> relacionEmpleadoConPabellon(@PathVariable Integer idEmpleado,@PathVariable Integer idPabellon){
        Map<String, Object> mensaje = new HashMap<>();
        Optional<Persona> oEmpleado = service.findById(idEmpleado);
        if (!oEmpleado.isPresent()){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("success", String.format("Empleado con id %d no existe", idEmpleado));
            return  ResponseEntity.badRequest().body(mensaje);
        }
        Optional<Pabellon> oPabellon = pabellonDAO.findById(idPabellon);
        if (!oPabellon.isPresent()){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("success", String.format("Pabellon con id %d no existe", idPabellon));
            return  ResponseEntity.badRequest().body(mensaje);
        }
        Persona empleado = oEmpleado.get();
        Pabellon pabellon = oPabellon.get();
        ((Empleado)empleado).setPabellon(pabellon);

        Map<String, Object> relacionEmpleadoPabellon = new HashMap<>();
        relacionEmpleadoPabellon.put("empleado", empleadoMapper.mapEmpleado((Empleado)service.save(empleado)));
        relacionEmpleadoPabellon.put("pabellon", mapperMS.mapPabellon(pabellon));

        mensaje.put("success", Boolean.TRUE);
        mensaje.put("datos", relacionEmpleadoPabellon);
        return  ResponseEntity.badRequest().body(mensaje);
    }

}
