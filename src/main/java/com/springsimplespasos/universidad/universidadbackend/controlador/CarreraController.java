package com.springsimplespasos.universidad.universidadbackend.controlador;

import com.springsimplespasos.universidad.universidadbackend.exception.BadRequestExecption;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Carrera;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.CarreraDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/carreras")
public class CarreraController  extends  GenericController<Carrera, CarreraDAO>{
    public CarreraController(CarreraDAO service) {
        super(service);
        nombreEntidad = "Carrera";
    }

//    private final CarreraDAO carreraDAO;

//    @Autowired
//    public CarreraController(CarreraDAO carreraDAO){
//        this.carreraDAO = carreraDAO;
//    }
//    @GetMapping
//    public List<Carrera> obtenerTodos(){
//        List<Carrera> carreras = (List<Carrera>) carreraDAO.findAll();
//        if (carreras.isEmpty()){
//            throw new BadRequestExecption("No existen carreras");
//        }
//        return  carreras;
//    }




    /**
    * @PathVariable mapea el codigo que viene en la url al argumento id
    * */
    @GetMapping("/{codigo}")
    public Carrera obtenerPorId(@PathVariable(value = "codigo",required = false) Integer id){
        Optional<Carrera> oCarrera = service.findById(id);
        if (!oCarrera.isPresent()){
            throw new BadRequestExecption(String.format("La carrera con id %d no existe", id));
        }
        return oCarrera.get();
    }

    /**
     * @RequestBody representa en el json un objeto de tipo carrera
     *
     */

    @PostMapping
    public Carrera altaCarrera(@RequestBody Carrera carrera){
        if (carrera.getCantidadAnios() < 0){
            throw new BadRequestExecption(String.format("El campo cantidad de años no puede ser negativo"));
        }
        if (carrera.getCantidadDeMaterias() < 0){
            throw new BadRequestExecption(String.format("El campo cantidad de materias no puede ser negativo"));
        }
        return service.save(carrera);
    }

    @PutMapping("/{id}")
    public Carrera actualizarCarrera (@PathVariable Integer id, @RequestBody Carrera carrera){
        Carrera carreraUpdate = null;
        Optional<Carrera> oCarrera = service.findById(id);
        if(!oCarrera.isPresent()){
            throw new BadRequestExecption(String.format("La carrera con id %d no existe", id));
        }
        carreraUpdate = oCarrera.get();
        carreraUpdate.setCantidadAnios(carrera.getCantidadAnios());
        carreraUpdate.setCantidadDeMaterias(carrera.getCantidadDeMaterias());
        return service.save(carreraUpdate);
    }

    @DeleteMapping("/{id}")
    public void eliminarCarrera(@PathVariable Integer id){
        service.deleteId(id);
    }
}
