package com.dh.ClinicaOdontologica.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
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

    private Integer dni;

    @Column
    private String fechaDeAlta;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "domicilio_id",referencedColumnName = "id")
    private Domicilio domicilio;

}
