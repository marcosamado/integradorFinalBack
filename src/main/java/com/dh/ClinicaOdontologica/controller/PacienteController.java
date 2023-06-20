package com.dh.ClinicaOdontologica.controller;

import com.dh.ClinicaOdontologica.dto.PacienteDto;
import com.dh.ClinicaOdontologica.entity.Odontologo;
import com.dh.ClinicaOdontologica.entity.Paciente;
import com.dh.ClinicaOdontologica.service.ClinicaOdontologicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private ClinicaOdontologicaService<Paciente, PacienteDto> service;



    @GetMapping
    public List<PacienteDto> obtenerTodos() {
        return service.listar();
    }
    @PutMapping
    public ResponseEntity<?> actualizar(@RequestBody Paciente paciente){
        return ResponseEntity.ok(service.actualizar(paciente));
    }
    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Paciente paciente){
        return ResponseEntity.ok(service.guardar(paciente));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Integer id){
        return ResponseEntity.ok(service.buscarPorId(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarPorId(@PathVariable Integer id){
        service.borrarPorId(id);

        return ResponseEntity.ok("Se elimino con exito");
    }

}
