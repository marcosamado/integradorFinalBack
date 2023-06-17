package com.dh.ClinicaOdontologica.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PacienteDto {
    private String apellido;
    private String nombre;
    private DomicilioDto domicilio;

}
