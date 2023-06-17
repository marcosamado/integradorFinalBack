package com.dh.ClinicaOdontologica.dto;

import com.dh.ClinicaOdontologica.entity.Paciente;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TurnoDto {
    private String fecha;
    private String hora;

}
