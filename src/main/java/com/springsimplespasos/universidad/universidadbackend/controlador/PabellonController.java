package com.springsimplespasos.universidad.universidadbackend.controlador;

import com.springsimplespasos.universidad.universidadbackend.exception.BadRequestExecption;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Pabellon;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.PabellonDAO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pabellones")
public class PabellonController extends GenericController<Pabellon, PabellonDAO> {

    public PabellonController(PabellonDAO service) {
        super(service);
        this.nombreEntidad = "Pabellon";
    }

    @PutMapping("/{id}")
    public Pabellon pabellonUpdate(@PathVariable Integer id,@RequestBody Pabellon pabellon){
        Pabellon pabellonUpdate = null;
        Optional<Pabellon> oPabellon = service.findById(id);
        if (!oPabellon.isPresent()){
            throw new BadRequestExecption(String.format("No existe pabellon con el id %d", id));
        }
        pabellonUpdate = oPabellon.get();
        pabellonUpdate.setAulas(pabellon.getAulas());
        pabellonUpdate.setNombre(pabellon.getNombre());
        pabellonUpdate.setMtr2(pabellon.getMtr2());
        pabellonUpdate.setDireccion(pabellon.getDireccion());
        return service.save(pabellonUpdate);
    }


    @GetMapping("/localidad/{localidad}")
    public List<Pabellon> findPabellonByDireccionLocalidad(@PathVariable String localidad){
        List<Pabellon> pabellonByDireccionLocalidad = (List<Pabellon>) service.findPabellonByDireccionLocalidad(localidad);
        if (pabellonByDireccionLocalidad.isEmpty()){
            throw new BadRequestExecption(String.format("No existen pabellones con la localidad ", localidad));
        }
        return pabellonByDireccionLocalidad;
    }

    @GetMapping("/nombre/{nombre}")
    public List<Pabellon> findPabellonByNombre(@PathVariable String nombre){
        List<Pabellon> pabellonByNombre = (List<Pabellon>) service.findPabellonByNombre(nombre);
        if (pabellonByNombre.isEmpty()){
            throw new BadRequestExecption(String.format("No existen pabellones con el nombre¿", nombre));
        }
            return pabellonByNombre;
    }

}
