package com.springsimplespasos.universidad.universidadbackend.controlador;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/restapi")
public class PrimerRestController {

    Logger logger = LoggerFactory.getLogger(PrimerRestController.class);
    @GetMapping("/hola-mundo")
    public ResponseEntity<?> holaMundo(){
        Map<String, Object> mensajes = new HashMap<>();
        mensajes.put("saludos", "Hola Mundo");
        logger.trace("trace log");
        logger.debug("debag log");
        logger.info("info log");
        logger.warn("waring log");
        logger.error("error log");
        return new ResponseEntity<>(mensajes, HttpStatus.ACCEPTED);
    }

}
