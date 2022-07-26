package com.mathewgv.library.service.exception;

public class ServiceException extends RuntimeException {

    private final String message;

    public ServiceException(Exception e) {
        message = e.getMessage();
    }

    public ServiceException(String e) {
        message = e;
    }

    public ServiceException() {
        message = "Error";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
