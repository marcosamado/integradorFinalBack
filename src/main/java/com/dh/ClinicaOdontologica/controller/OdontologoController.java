package com.dh.ClinicaOdontologica.controller;

import com.dh.ClinicaOdontologica.dto.OdontologoDto;
import com.dh.ClinicaOdontologica.entity.Odontologo;
import com.dh.ClinicaOdontologica.service.ClinicaOdontologicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    @Autowired
    private ClinicaOdontologicaService<Odontologo, OdontologoDto> service;


    @GetMapping
    public List<OdontologoDto> obtenerTodos() {
        return service.listar();
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(service.guardar(odontologo));
    }
}
