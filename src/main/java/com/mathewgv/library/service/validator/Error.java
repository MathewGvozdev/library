package com.mathewgv.library.service.validator;

public final class Error {

    private final String code;

    public Error(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
