package com.springsimplespasos.universidad.universidadbackend.controlador;

import com.springsimplespasos.universidad.universidadbackend.exception.BadRequestExecption;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Aula;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.enumeradores.Pizarron;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.enumeradores.EnumeradorConverterGeneric;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.AulaDAO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("aulas")
public class AulaController extends GenericController<Aula, AulaDAO> {

    public AulaController(AulaDAO service) {
        super(service);
        this.nombreEntidad = "Aula";
    }

    @PutMapping("/{idAula}")
    public Aula actualizarAula(@PathVariable Integer idAula, @RequestBody Aula aula){
        Aula aulaUpdate = null;
        System.out.println("LOG - Init actualizrAula");
        Optional<Aula> oAula = service.findById(idAula);
        System.out.println("LOG - oAula es:" + oAula.toString());
        if (!oAula.isPresent()){
            throw new BadRequestExecption(String.format("No existen aula con el id %d. ", idAula));
        }
        System.out.println("LOG - aula es: " + aula.toString());
        aulaUpdate = oAula.get();
        aulaUpdate.setCantidadPupitres(aula.getCantidadPupitres());
        aulaUpdate.setMedidas(aula.getMedidas());
        aulaUpdate.setPabellon(aula.getPabellon());
        return service.save(aulaUpdate);
    }

    @GetMapping(value = ("/pizarron/{pizarron}"))
    public List<Aula> findAulasByPizarron(@PathVariable String pizarron) {
        Pizarron cPizarron = EnumeradorConverterGeneric.getEnumFromString(Pizarron.class, pizarron);
        List<Aula> aulasByPizarron = (List<Aula>) service.findAulasByPizarron(cPizarron);
        if (aulasByPizarron.isEmpty()) {
            throw new BadRequestExecption(String.format("No existen aulas con el tipo de pizzarra,  ", pizarron));
        } else
            return aulasByPizarron;
    }

    @GetMapping("/por-pabellon/{nombrePabellon}")
    public List<Aula> findAulaByPabellonNombre(@PathVariable String nombrePabellon){
        List<Aula> aulaByPabellonNombre = (List<Aula>) service.findAulaByPabellonNombre(nombrePabellon);
        if (aulaByPabellonNombre.isEmpty()){
            throw new BadRequestExecption(String.format("No existen aulas en el pabellon, %s   ", nombrePabellon));
        }
        return aulaByPabellonNombre;
    }

    @GetMapping("nro-Aula/nroAula")
    public Optional<Aula> findAulaByNroAula(@PathVariable Integer nroAula){
        Optional<Aula> aulaByNroAula = service.findAulaByNroAula(nroAula);
        if (!aulaByNroAula.isPresent()){
            throw new BadRequestExecption(String.format("No existen aulas con el nro de Aula: %s,  ", nroAula));
        }
        return aulaByNroAula;
    }
}
