package com.dh.ClinicaOdontologica.repository;

import com.dh.ClinicaOdontologica.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public interface TurnoRepository extends JpaRepository<Turno, Integer> {

}
