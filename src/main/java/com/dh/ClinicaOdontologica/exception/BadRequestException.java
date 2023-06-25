package com.dh.ClinicaOdontologica.exception;


import lombok.Getter;

@Getter
public class BadRequestException extends Exception {

    private String codigoError;

    public BadRequestException(String message, String codigoError) {
        super(message);
        this.codigoError = codigoError;
    }
}
