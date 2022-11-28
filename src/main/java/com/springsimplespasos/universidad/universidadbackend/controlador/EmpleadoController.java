package com.springsimplespasos.universidad.universidadbackend.controlador;

import com.springsimplespasos.universidad.universidadbackend.exception.BadRequestExecption;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Empleado;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Pabellon;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Persona;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.enumeradores.TipoEmpleado;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.enumeradoresConverter.TipoEmpleadoConverter;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.EmpeladoDAO;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.PabellonDAO;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.PersonaDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController extends PersonaController{
    
    private final PabellonDAO pabellonDAO;

    public EmpleadoController(@Qualifier("empleadoDAOImpl") PersonaDAO service, PabellonDAO pabellonDAO) {
        super(service);
        this.pabellonDAO = pabellonDAO;
        this.nombreEntidad = "Empleado";
    }

    @GetMapping(value = "/tipo/{tipoEmpleado}")
    public List<Persona> findEmpleadoByTipoEmpleado(@PathVariable(required = false) TipoEmpleado tipoEmpleado){

        TipoEmpleadoConverter tipoEmpleadoConverter = new TipoEmpleadoConverter();
        if (tipoEmpleadoConverter.convert(String.valueOf(tipoEmpleado)) == null ){
            throw new BadRequestExecption(String.format("Tipo de empleados incorrecto, %s ", tipoEmpleado));
        }
        else{
            List<Persona> empleadoByTipoEmpleado = (List<Persona>) ((EmpeladoDAO) service).findEmpleadoByTipoEmpleado((tipoEmpleado));
            if (empleadoByTipoEmpleado.isEmpty()){
                throw new BadRequestExecption(String.format("No existe el tipo de empleado,  ", tipoEmpleado));
            }else
                return  empleadoByTipoEmpleado;
        }
    }

    @PutMapping("{id}")
    public Persona updateEmpleado(@PathVariable Integer id,@RequestBody Persona empleado){
        Persona empleadoUpdate = null;
        Optional<Persona> oEmpleado= service.findById(id);
        if (!oEmpleado.isPresent()){
            throw new BadRequestExecption(String.format("No empleado con id %d no existe", id));
        }
        empleadoUpdate = oEmpleado.get();
        empleadoUpdate.setNombre(empleado.getNombre());
        empleadoUpdate.setApellido((empleado.getApellido()));
        empleadoUpdate.setDni(empleado.getDni());
        empleadoUpdate.setDireccion(empleado.getDireccion());
        ((Empleado)empleadoUpdate).setSueldo(((Empleado)empleado).getSueldo());
        return service.save(empleadoUpdate);
    }

    @PutMapping("/{idEmpleado}/pabellon/{idPabellon}")
    public Persona relacionEmpleadoConPabellon(@PathVariable Integer idEmpleado,@PathVariable Integer idPabellon){
        Optional<Persona> oEmpleado = service.findById(idEmpleado);
        if (!oEmpleado.isPresent()){
            throw new BadRequestExecption(String.format("Empleado con id %d no existe", idEmpleado));
        }
        Optional<Pabellon> oPabellon = pabellonDAO.findById(idPabellon);
        if (!oPabellon.isPresent()){
            throw new BadRequestExecption(String.format("Pabellon con id %d no existe", idPabellon));
        }
        Persona empleado = oEmpleado.get();
        Pabellon pabellon = oPabellon.get();
        ((Empleado)empleado).setPabellon(pabellon);
        return  service.save(empleado);
    }
}













































