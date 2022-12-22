package com.springsimplespasos.universidad.universidadbackend.controlador;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Empleado;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Pabellon;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Persona;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.enumeradores.TipoEmpleado;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.enumeradores.EnumeradorConverterGeneric;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.EmpeladoDAO;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.PabellonDAO;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.PersonaDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@Deprecated
@RestController
@RequestMapping("/empleados")
@ConditionalOnProperty(prefix = "app", name = "controller.enable-dto", havingValue = "false")
public class EmpleadoController extends PersonaController{
    
    private final PabellonDAO pabellonDAO;

    public EmpleadoController(@Qualifier("empleadoDAOImpl") PersonaDAO service, PabellonDAO pabellonDAO) {
        super(service);
        this.pabellonDAO = pabellonDAO;
        this.nombreEntidad = "Empleado";
    }

    @GetMapping(value = "/tipo/{tipoEmpleado}")
    public ResponseEntity<?> findEmpleadoByTipoEmpleado(@PathVariable(required = false) String tipoEmpleado){
        Map<String, Object> mensaje = new HashMap<>();
        TipoEmpleado cTipoEmpleado = EnumeradorConverterGeneric.getEnumFromString(TipoEmpleado.class, tipoEmpleado);
        List<Persona> empleadoByTipoEmpleado = (List<Persona>) ((EmpeladoDAO) service).findEmpleadoByTipoEmpleado((cTipoEmpleado));
        if (empleadoByTipoEmpleado.isEmpty()){
            //throw new BadRequestExecption(String.format("No existe el tipo de empleado,  ", tipoEmpleado));
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("success", String.format("No existe el tipo de empleado, %s ", tipoEmpleado));
            return  ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("success", Boolean.TRUE);
        mensaje.put("datos", empleadoByTipoEmpleado);
        return  ResponseEntity.ok(mensaje);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateEmpleado(@PathVariable Integer id, @Valid @RequestBody Persona empleado, BindingResult result){
        Map<String, Object> mensaje = new HashMap<>();
        Map<String, Object> validaciones = new HashMap<>();
        if(result.hasErrors()){
            result.getFieldErrors()
                    .forEach(error -> validaciones.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(validaciones);
        }
        Persona empleadoUpdate = null;
        Optional<Persona> oEmpleado= service.findById(id);
        if (!oEmpleado.isPresent()){
            //throw new BadRequestExecption(String.format("No empleado con id %d no existe", id));
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("success", String.format("No empleado con id %d no existe", id));
            return  ResponseEntity.badRequest().body(mensaje);
        }
        empleadoUpdate = oEmpleado.get();
        empleadoUpdate.setNombre(empleado.getNombre());
        empleadoUpdate.setApellido((empleado.getApellido()));
        empleadoUpdate.setDni(empleado.getDni());
        empleadoUpdate.setDireccion(empleado.getDireccion());
        ((Empleado)empleadoUpdate).setTipoEmpleado(((Empleado)empleado).getTipoEmpleado());
        ((Empleado)empleadoUpdate).setSueldo(((Empleado)empleado).getSueldo());

        mensaje.put("success", Boolean.TRUE);
        mensaje.put("datos", service.save(empleadoUpdate));
        return  ResponseEntity.badRequest().body(mensaje);
    }

    @PutMapping("/{idEmpleado}/pabellon/{idPabellon}")
    public ResponseEntity<?> relacionEmpleadoConPabellon(@PathVariable Integer idEmpleado,@PathVariable Integer idPabellon){
        Map<String, Object> mensaje = new HashMap<>();
        Optional<Persona> oEmpleado = service.findById(idEmpleado);
        if (!oEmpleado.isPresent()){
            //throw new BadRequestExecption(String.format("Empleado con id %d no existe", idEmpleado));
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("success", String.format("Empleado con id %d no existe", idEmpleado));
            return  ResponseEntity.badRequest().body(mensaje);
        }
        Optional<Pabellon> oPabellon = pabellonDAO.findById(idPabellon);
        if (!oPabellon.isPresent()){
            //throw new BadRequestExecption(String.format("Pabellon con id %d no existe", idPabellon));
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("success", String.format("Pabellon con id %d no existe", idPabellon));
            return  ResponseEntity.badRequest().body(mensaje);
        }
        Persona empleado = oEmpleado.get();
        Pabellon pabellon = oPabellon.get();
        ((Empleado)empleado).setPabellon(pabellon);

        mensaje.put("success", Boolean.TRUE);
        mensaje.put("datos", service.save(empleado));
        return  ResponseEntity.badRequest().body(mensaje);
    }
}













































