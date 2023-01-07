package com.dh.IntegradorBackend.exections;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptions {
    private static final Logger LOGGER=Logger.getLogger(GlobalExceptions.class);
    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<String> tratamietoResourceNotFoundException(
            ResourceNotFoundException rnfe
    ){
        LOGGER.error("ocurrio un error: "+rnfe.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rnfe.getMessage());
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<String> tratamietoBadRequestException(
            BadRequestException bre
    ){
        LOGGER.error("Ocurrio un error: " + bre.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bre.getMessage());
    }
}
