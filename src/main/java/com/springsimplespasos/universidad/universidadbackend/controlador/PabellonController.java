package com.springsimplespasos.universidad.universidadbackend.controlador;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Pabellon;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.PabellonDAO;
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
@RequestMapping("/pabellones")
@ConditionalOnProperty(prefix = "app", name = "controller.enable-dto", havingValue = "false")
public class PabellonController extends GenericController<Pabellon, PabellonDAO> {

    public PabellonController(PabellonDAO service) {
        super(service);
        this.nombreEntidad = "Pabellon";
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> pabellonUpdate(@PathVariable Integer id, @Valid @RequestBody Pabellon pabellon, BindingResult result){
        Map<String,Object> mensaje = new HashMap<>();
        Map<String, Object> validaciones = new HashMap<>();

        if(result.hasErrors()){
            result.getFieldErrors().
                    forEach(error -> validaciones.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(validaciones);
        }

        Pabellon pabellonUpdate = null;
        Optional<Pabellon> oPabellon = service.findById(id);
        if (!oPabellon.isPresent()){
            //throw new BadRequestExecption(String.format("No existe pabellon con el id %d", id));
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("success", String.format("No existe pabellon con el id %d", id));
            return  ResponseEntity.badRequest().body(mensaje);
        }
        pabellonUpdate = oPabellon.get();
        pabellonUpdate.setAulas(pabellon.getAulas());
        pabellonUpdate.setNombre(pabellon.getNombre());
        pabellonUpdate.setMtr2(pabellon.getMtr2());
        pabellonUpdate.setDireccion(pabellon.getDireccion());

        mensaje.put("success", Boolean.TRUE);
        mensaje.put("datos", service.save(pabellonUpdate));
        return  ResponseEntity.ok(mensaje);
    }

    @GetMapping("/localidad/{localidad}")
    public ResponseEntity<?> findPabellonByDireccionLocalidad(@PathVariable String localidad){
        Map<String,Object> mensaje = new HashMap<>();
        List<Pabellon> pabellonByDireccionLocalidad = (List<Pabellon>) service.findPabellonByDireccionLocalidad(localidad);
        if (pabellonByDireccionLocalidad.isEmpty()){
            //throw new BadRequestExecption(String.format("No existen pabellones con la localidad ", localidad));
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No existen pabellones con la localidad ", localidad));
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("success", Boolean.FALSE);
        mensaje.put("datos", pabellonByDireccionLocalidad);
        return ResponseEntity.ok(mensaje);
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<?> findPabellonByNombre(@PathVariable String nombre){
        Map<String,Object> mensaje = new HashMap<>();
        List<Pabellon> pabellonByNombre = (List<Pabellon>) service.findPabellonByNombre(nombre);
        if (pabellonByNombre.isEmpty()){
            //throw new BadRequestExecption(String.format("No existen pabellones con el nombre¿", nombre));
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No existen pabellones con el nombre¿", nombre));
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("success", Boolean.FALSE);
        mensaje.put("datos", pabellonByNombre);
        return ResponseEntity.ok(mensaje);
    }
}
