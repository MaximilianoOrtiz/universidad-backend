package com.springsimplespasos.universidad.universidadbackend.controlador;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Persona;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.PersonaDAO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Deprecated
public class PersonaController extends GenericController<Persona, PersonaDAO>{

    public PersonaController(PersonaDAO service) {
        super(service);
    }

    /**
     * @RequesParam  recupera del reques los parametros a utilizar como argumento
     * sin necesidad de especificarlos en la url
     * */
    @GetMapping("/nombre-apellido")
    public ResponseEntity<?> buscarPersonaPorNombreYApellido(@RequestParam String nombre, @RequestParam String apellido){
        Map<String, Object> mensaje = new HashMap<>();
        Optional<Persona> oPersona = service.buscarPorNombreYApellido(nombre, apellido);
        if (!oPersona.isPresent()){
            //throw new BadRequestExecption(String.format("No se encontro Persona con nombre %s y apellido %s", nombre, apellido));
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No se encontro Persona con nombre %s y apellido %s", nombre, apellido));
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("success", Boolean.TRUE);
        mensaje.put("datos", oPersona);
        return ResponseEntity.ok(mensaje);
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity<?> buscarPorDni (@PathVariable String dni){
        Map<String, Object> mensaje = new HashMap<>();
        Optional<Persona> personaEncontrada = service.buscarPorDni(dni);
        if(!personaEncontrada.isPresent()){
            //throw new BadRequestExecption(String.format("La persona con dni %s no existe", dni));
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("La persona con dni %s no existe", dni));
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("success", Boolean.TRUE);
        mensaje.put("datos", personaEncontrada.get());
        return ResponseEntity.ok(mensaje);
    }

    @GetMapping("/apellido/{apellido}")
    public ResponseEntity<?> buscarPersonaPorApellido (@PathVariable() String apellido){
        Map<String, Object> mensaje = new HashMap<>();
        List<Persona> personasEncontradas = (List<Persona>) service.buscarPersonaPorApellido(apellido);
        if (personasEncontradas.isEmpty()){
            //throw new BadRequestExecption(String.format("Las personas con apellido %s no existen", apellido));
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("Las personas con apellido %s no existen", apellido));
            return ResponseEntity.badRequest().body(mensaje);
        }

        mensaje.put("success", Boolean.TRUE);
        mensaje.put("datos", personasEncontradas);
        return ResponseEntity.ok(mensaje);
    }
}






















