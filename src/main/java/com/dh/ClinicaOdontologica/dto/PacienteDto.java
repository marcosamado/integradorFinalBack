package com.dh.ClinicaOdontologica.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDate;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PacienteDto {
    private Integer id;
    private String apellido;
    private String nombre;
    private DomicilioDto domicilio;

}
