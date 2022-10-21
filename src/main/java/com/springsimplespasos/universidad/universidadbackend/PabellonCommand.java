package com.springsimplespasos.universidad.universidadbackend;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Pabellon;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.PabellonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PabellonCommand implements CommandLineRunner {

    @Autowired
    private PabellonDAO pabellonDAO;

    @Override
    public void run(String... args) throws Exception {

        Iterable<Pabellon> florencio_varela = pabellonDAO.findPabellonByDireccionLocalidad("Florencio Varela");
        florencio_varela.forEach(pabellon -> pabellon.getAulas());
        florencio_varela.forEach(pabellon -> System.out.println(pabellon.toString()));

        Iterable<Pabellon> florencio_varela1 = pabellonDAO.findPabellonByDireccionLocalidad(null);
        florencio_varela1.forEach(pabellon -> pabellon.getAulas());
        florencio_varela1.forEach(pabellon -> System.out.println(pabellon.toString()));


        Iterable<Pabellon> pabellon1 = pabellonDAO.findPabellonByNombre("pabellon1");
        pabellon1.forEach(pabellon -> System.out.println(pabellon.toString()));


    }
}
