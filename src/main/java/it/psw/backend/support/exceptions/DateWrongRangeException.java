package it.psw.backend.support.exceptions;

public class DateWrongRangeException extends RuntimeException{
    private String message;

    public DateWrongRangeException(String message) {
            super(message);
        }
}
