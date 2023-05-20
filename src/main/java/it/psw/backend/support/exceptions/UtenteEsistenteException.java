package it.psw.backend.support.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UtenteEsistenteException extends RuntimeException {

    private String message;

    public UtenteEsistenteException(String message) {
        super(message);
    }//Constructor

}//UtenteEsistenteException
