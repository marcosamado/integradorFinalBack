package com.dh.ClinicaOdontologica.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends  Exception{

    private String codigoError;

    public NotFoundException(String codigoError, String message) {
        super(message);
        this.codigoError = codigoError;
    }
}
