package com.springsimplespasos.universidad.universidadbackend;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Aula;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Direccion;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Pabellon;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.enumeradores.Pizarron;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.AulaDAO;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.PabellonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class AulaCommand implements CommandLineRunner {

    @Autowired
    private AulaDAO aulaDAO;

    @Autowired
    private PabellonDAO pabellonDAO;

    @Override
    public void run(String... args) throws Exception {

        Aula aula = new Aula(14, 1, "30mt2", 25, Pizarron.PIZARRA_BLANCA);
        Aula aula1 = new Aula(15, 2, "30mt2", 25, Pizarron.PIZARRA_BLANCA);
        Aula aula2 = new Aula(16, 3, "25mt2", 15, Pizarron.PIZARRA_TIZA);
        Aula aula3 = new Aula(17, 4, "30mt2", 25, Pizarron.PIZARRA_BLANCA);

        Direccion calle1 = new Direccion("Calle1", "45", null, null, null, null);
        Pabellon pabellon1 = new Pabellon(10, 56.0, "pabellon1", calle1);

   /*     aula.setPabellon(pabellon1);
        aula1.setPabellon(pabellon1);
        aula2.setPabellon(pabellon1);
        aula3.setPabellon(pabellon1);

        Aula save = aulaDAO.save(aula);
        Aula save1 = aulaDAO.save(aula1);
        Aula save2 = aulaDAO.save(aula2);
        Aula save3 = aulaDAO.save(aula3);
*/
        /*Set<Aula> aulasPabellos1 = new HashSet<Aula>();
        aulasPabellos1.add(aula);
        aulasPabellos1.add(aula1);
        aulasPabellos1.add(aula2);
        aulasPabellos1.add(aula3);

        pabellon1.setAulas(aulasPabellos1);
        Pabellon save4 = pabellonDAO.save(pabellon1);*/


        Aula aula4 = new Aula(18, 5, "30mt2", 25, Pizarron.PIZARRA_BLANCA);
        Aula aula5 = new Aula(19, 6, "25mt2", 18, Pizarron.PIZARRA_BLANCA);
        Aula aula6 = new Aula(20, 7, "25mt2", 15, Pizarron.PIZARRA_TIZA);
        Aula aula7 = new Aula(21, 8, "30mt2", 25, Pizarron.PIZARRA_BLANCA);

        Direccion calle2 = new Direccion("Calle2", "45", null, null, null, null);
        Pabellon pabellon2 = new Pabellon(13, 56.0, "pabellon2", calle2);

       /* Aula save5 = aulaDAO.save(aula4);
        Aula save6 = aulaDAO.save(aula5);
        Aula save7 = aulaDAO.save(aula6);
        Aula save8 = aulaDAO.save(aula7);*/


        // Pabellon save9 = pabellonDAO.save(pabellon2);

       /* Set<Aula> aulasPabellos2 = new HashSet<Aula>();
        aulasPabellos2.add(aula4);
        aulasPabellos2.add(aula5);
        aulasPabellos2.add(aula6);
        aulasPabellos2.add(aula7);

        Pabellon save9 = pabellonDAO.save(pabellon2);*/

       /* aula4.setPabellon(pabellon2);
        aula5.setPabellon(pabellon2);
        aula6.setPabellon(pabellon2);
        aula7.setPabellon(pabellon2);

        Aula save5 = aulaDAO.save(aula4);
        Aula save6 = aulaDAO.save(aula5);
        Aula save7 = aulaDAO.save(aula6);
        Aula save8 = aulaDAO.save(aula7);

*/

        //Pruebas
     /*   System.out.println("PIZZARRAS BLANCAS");
        Iterable<Aula> aulasByPizarronContainsPizarraBlanca = aulaDAO.findAulasByPizarron(Pizarron.PIZARRA_BLANCA);
        aulasByPizarronContainsPizarraBlanca.forEach(aula8 -> System.out.println(aula8.toString()));

        System.out.println("PIZZARRAS TIZAS");
        Iterable<Aula> aulasByPizarronContainsPizarraTiza = aulaDAO.findAulasByPizarron(Pizarron.PIZARRA_TIZA);
        aulasByPizarronContainsPizarraTiza.forEach(aula8 -> System.out.println(aula8.toString()));

        System.out.println("AULAS CORRESPONDIENTE A CIERTO PABELLON");

        Iterable<Aula> pabellon11 = aulaDAO.findAulaByPabellonNombre("pabellon2");
        pabellon11.forEach(aula8 -> System.out.println(aula8.toString()));
*/
        System.out.println("AULA CORRESPONDIENTE A NRO DE AULA");

        Optional<Aula> aulaByNroAula = aulaDAO.findAulaByNroAula(3);
        System.out.println(aulaByNroAula.get().toString());

    }
}

