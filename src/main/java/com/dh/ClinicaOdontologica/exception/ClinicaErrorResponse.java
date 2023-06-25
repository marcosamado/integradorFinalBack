package com.dh.ClinicaOdontologica.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ClinicaErrorResponse {

    private String codigoError;
    private String mensaje;
}
