package com.dh.ClinicaOdontologica.service;


import com.dh.ClinicaOdontologica.dto.TurnoDto;
import com.dh.ClinicaOdontologica.entity.Turno;
import com.dh.ClinicaOdontologica.repository.TurnoRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TurnoServiceImp  implements ClinicaOdontologicaService<Turno, TurnoDto> {

    @Autowired
    private TurnoRepository repository;


    @Override
    public Turno actualizar(Integer id, Turno turno) {
        return null;
    }

    @Override
    public Turno guardar(Turno turno) {
        return repository.save(turno);
    }

    @Override
    public void borrarPorId(Integer id) {

    }

    @Override
    public List<TurnoDto> listar() {
        List<Turno> listaTurnos = repository.findAll();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return listaTurnos
                .stream()
                .map(turno -> mapper.convertValue(turno, TurnoDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public TurnoDto buscarPorId(Integer id) {
        return null;
    }
}
