package com.dh.ClinicaOdontologica.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="pacientes")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column

    private String apellido;
    @Column

    private String nombre;
    @Column

    private String dni;

    @Column
    private String fechaDeAlta;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "domicilio_id",referencedColumnName = "id")
    private Domicilio domicilio;



}
