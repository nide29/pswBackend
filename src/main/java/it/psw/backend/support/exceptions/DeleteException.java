package it.psw.backend.support.exceptions;

import org.hibernate.sql.Delete;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DeleteException extends RuntimeException{
    private String message;

    public DeleteException(String message) {
        super(message);
    }

}//DeleteException
