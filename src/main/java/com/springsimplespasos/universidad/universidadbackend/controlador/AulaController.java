package com.springsimplespasos.universidad.universidadbackend.controlador;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Aula;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.enumeradores.Pizarron;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.enumeradores.EnumeradorConverterGeneric;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.AulaDAO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("aulas")
public class AulaController extends GenericController<Aula, AulaDAO> {

    public AulaController(AulaDAO service) {
        super(service);
        this.nombreEntidad = "Aula";
    }

    @PutMapping("/{idAula}")
    public ResponseEntity<?> actualizarAula(@PathVariable Integer idAula, @Valid @RequestBody Aula aula, BindingResult result){
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
            //throw new BadRequestExecption(String.format("No existen aula con el id %d. ", idAula));
            mensajes.put("success", Boolean.FALSE);
            mensajes.put("mensaje", String.format("No existen aula con el id %d. ", idAula));
            return ResponseEntity.badRequest().body(mensajes);
        }
        System.out.println("LOG - aula es: " + aula.toString());
        aulaUpdate = oAula.get();
        aulaUpdate.setCantidadPupitres(aula.getCantidadPupitres());
        aulaUpdate.setMedidas(aula.getMedidas());
        aulaUpdate.setPabellon(aula.getPabellon());

        mensajes.put("success", Boolean.TRUE);
        mensajes.put("datos", service.save(aulaUpdate) );
        return ResponseEntity.ok(mensajes);
    }

    @GetMapping(value = ("/pizarron/{pizarron}"))
    public ResponseEntity<?> findAulasByPizarron(@PathVariable String pizarron) {
        Map<String, Object> mensajes = new HashMap<>();
        Pizarron cPizarron = EnumeradorConverterGeneric.getEnumFromString(Pizarron.class, pizarron);
        List<Aula> aulasByPizarron = (List<Aula>) service.findAulasByPizarron(cPizarron);
        if (aulasByPizarron.isEmpty()) {
            //throw new BadRequestExecption(String.format("No existen aulas con el tipo de pizzarra,  ", pizarron));
            mensajes.put("success", Boolean.FALSE);
            mensajes.put("mensaje", String.format("No existen aulas con el tipo de pizzarra, %s ", pizarron));
            return ResponseEntity.badRequest().body(mensajes);
        }
        mensajes.put("success", Boolean.TRUE);
        mensajes.put("datos", aulasByPizarron);
        return ResponseEntity.ok(mensajes);
    }

    @GetMapping("/por-pabellon/{nombrePabellon}")
    public ResponseEntity<?> findAulaByPabellonNombre(@PathVariable String nombrePabellon){
        Map<String, Object> mensajes = new HashMap<>();
        List<Aula> aulaByPabellonNombre = (List<Aula>) service.findAulaByPabellonNombre(nombrePabellon);
        if (aulaByPabellonNombre.isEmpty()){
            //throw new BadRequestExecption(String.format("No existen aulas en el pabellon, %s   ", nombrePabellon));
            mensajes.put("success", Boolean.FALSE);
            mensajes.put("mensaje",String.format("No existen aulas en el pabellon, %s   ", nombrePabellon));
            return ResponseEntity.badRequest().body(mensajes);
        }
        mensajes.put("success", Boolean.TRUE);
        mensajes.put("datos", aulaByPabellonNombre);
        return ResponseEntity.ok(mensajes);
    }

    @GetMapping("nro-Aula/nroAula")
    public ResponseEntity<?> findAulaByNroAula(@PathVariable Integer nroAula){
        Map<String, Object> mensajes = new HashMap<>();
        Optional<Aula> aulaByNroAula = service.findAulaByNroAula(nroAula);
        if (!aulaByNroAula.isPresent()){
            //throw new BadRequestExecption(String.format("No existen aulas con el nro de Aula: %s,  ", nroAula));
            mensajes.put("success", Boolean.FALSE);
            mensajes.put("mensaje",String.format("No existen aulas con el nro de Aula: %s,  ", nroAula));
            return ResponseEntity.badRequest().body(mensajes);
        }
        mensajes.put("success", Boolean.TRUE);
        mensajes.put("datos", aulaByNroAula);
        return ResponseEntity.ok(mensajes);
    }
}
