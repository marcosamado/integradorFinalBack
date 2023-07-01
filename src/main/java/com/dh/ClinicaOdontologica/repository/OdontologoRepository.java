package com.dh.ClinicaOdontologica.repository;

import com.dh.ClinicaOdontologica.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OdontologoRepository extends JpaRepository<Odontologo,Integer> {

    Optional<Odontologo> findByMatricula(Integer matricula);
}
