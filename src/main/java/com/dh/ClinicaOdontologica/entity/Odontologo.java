package com.dh.ClinicaOdontologica.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
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
