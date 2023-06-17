package com.dh.ClinicaOdontologica.controller;

import com.dh.ClinicaOdontologica.dto.TurnoDto;
import com.dh.ClinicaOdontologica.entity.Turno;
import com.dh.ClinicaOdontologica.service.ClinicaOdontologicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    @Autowired
    private ClinicaOdontologicaService<Turno, TurnoDto> service;


    @GetMapping
    public List<TurnoDto> obtenerTodos() {
        return service.listar();
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Turno turno){
        return ResponseEntity.ok(service.guardar(turno));
    }
}
