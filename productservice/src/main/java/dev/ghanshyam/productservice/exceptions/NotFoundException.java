package dev.ghanshyam.productservice.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;


public class NotFoundException extends Exception{

    public NotFoundException(String msg){
    super(msg);
    }
}
