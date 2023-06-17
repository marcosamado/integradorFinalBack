package com.dh.ClinicaOdontologica.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="domicilios")
public class Domicilio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column
    private String direccion;
    @Column
    private String localidad;
    @Column
    private String provincia;

}
