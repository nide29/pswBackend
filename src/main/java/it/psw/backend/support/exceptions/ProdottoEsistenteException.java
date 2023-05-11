package it.psw.backend.support.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProdottoEsistenteException extends RuntimeException{
    private String message;

    public ProdottoEsistenteException(String message) {
        super(message);
    }
}
