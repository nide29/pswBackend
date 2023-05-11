package it.psw.backend.support.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProdottoNotFoundException extends RuntimeException {
    private String message;

    public ProdottoNotFoundException(String message){
        super(message);
    }
}
