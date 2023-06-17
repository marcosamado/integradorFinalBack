package com.dh.ClinicaOdontologica.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="odontologos")
public class Odontologo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column

    private String apellido;
    @Column

    private String nombre;
    @Column

    private Integer matricula;

}
