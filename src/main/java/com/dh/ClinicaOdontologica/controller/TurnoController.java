package com.dh.ClinicaOdontologica.controller;

import com.dh.ClinicaOdontologica.dto.TurnoDto;
import com.dh.ClinicaOdontologica.entity.Turno;
import com.dh.ClinicaOdontologica.service.ClinicaOdontologicaService;
import com.dh.ClinicaOdontologica.service.TurnoServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    @Autowired
    private ClinicaOdontologicaService<Turno, TurnoDto> service;
    @Autowired
    private TurnoServiceImp serviceTurno;


    @GetMapping
    public List<TurnoDto> obtenerTodos() {
        return service.listar();
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Turno turno){
        return ResponseEntity.ok(service.guardar(turno));
    }


//    @PutMapping
//    public ResponseEntity<?> actualizar(@RequestBody Turno turno){
//        return ResponseEntity.ok(service.actualizar(turno));
//    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Integer id){
        return ResponseEntity.ok(service.buscarPorId(id));
    }
}
