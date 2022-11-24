package com.springsimplespasos.universidad.universidadbackend.controlador;

import com.springsimplespasos.universidad.universidadbackend.exception.BadRequestExecption;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Persona;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.PersonaDAO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public class PersonaController extends GenericController<Persona, PersonaDAO>{

    public PersonaController(PersonaDAO service) {
        super(service);
    }

    /**
     * @RequesParam  recupera del reques los parametros a utilizar como argumento
     * sin necesidad de especificarlos en la url
     * */
    @GetMapping("/nombre-apellido")
    public Persona buscarPersonaPorNombreYApellido(@RequestParam String nombre,@RequestParam String apellido){
        Optional<Persona> oPersona = service.buscarPorNombreYApellido(nombre, apellido);
        if (!oPersona.isPresent()){
            throw new BadRequestExecption(String.format("No se encontro Persona con nombre %s y apellido %s", nombre, apellido));
        }
        return oPersona.get();
    }

    @GetMapping("/dni/{dni}")
    public Persona buscarPorDni (@PathVariable String dni){
        Optional<Persona> personaEncontrada = service.buscarPorDni(dni);
        if(!personaEncontrada.isPresent()){
                throw new BadRequestExecption(String.format("La persona con dni %s no existe", dni));
        }
        return personaEncontrada.get();
    }

    @GetMapping("/apellido/{apellido}")
    public List<Persona> buscarPersonaPorApellido (@PathVariable() String apellido){
        List<Persona> personasEncontradas = (List<Persona>) service.buscarPersonaPorApellido(apellido);
        if (personasEncontradas.isEmpty()){
            throw new BadRequestExecption(String.format("Las personas con apellido %s no existen", apellido));
        }
        return personasEncontradas;
    }
}






















