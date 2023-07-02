package com.dh.ClinicaOdontologica.dto;

import com.dh.ClinicaOdontologica.entity.Paciente;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TurnoDto {
    private Integer id;
    private String fecha;
    private String hora;
    private PacienteDto paciente;
    private OdontologoDto odontologo;

}
