package dev.ghanshyam.productservice.exceptions;

import dev.ghanshyam.productservice.dto.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvisors {

    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<ExceptionDto> notfoundexception(NotFoundException n){
        ResponseEntity<ExceptionDto> response = new ResponseEntity(
                new ExceptionDto(HttpStatus.BAD_REQUEST, n.getMessage()), HttpStatus.BAD_REQUEST
        );

        return response;
    }

}
