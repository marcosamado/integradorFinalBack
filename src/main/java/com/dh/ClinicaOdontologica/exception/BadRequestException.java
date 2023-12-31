package com.dh.ClinicaOdontologica.exception;


import lombok.Getter;

@Getter
public class BadRequestException extends Exception {

    private String codigoError;

    public BadRequestException(String codigoError, String message) {
        super(message);
        this.codigoError = codigoError;
    }
}
