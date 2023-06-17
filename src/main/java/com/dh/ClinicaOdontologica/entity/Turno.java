package com.dh.ClinicaOdontologica.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="turnos")
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String fecha;
    @Column
    private String hora;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "odontologo_id",referencedColumnName = "id", nullable = false)
//    private Odontologo odontologo;

}
