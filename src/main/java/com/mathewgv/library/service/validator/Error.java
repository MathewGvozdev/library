package com.mathewgv.library.service.validator;

public final class Error {

    private final String code;
//    private final String message;

    public Error(String code) {
        this.code = code;
//        this.message = message;
    }

    public String getCode() {
        return this.code;
    }

//    public String getMessage() {
//        return this.message;
//    }
}
