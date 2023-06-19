package com.dh.ClinicaOdontologica.service;

import com.dh.ClinicaOdontologica.dto.PacienteDto;
import com.dh.ClinicaOdontologica.entity.Paciente;
import com.dh.ClinicaOdontologica.repository.PacienteRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class PacienteServiceImp implements ClinicaOdontologicaService<Paciente, PacienteDto> {
    @Autowired
    private PacienteRepository repository;
    private ObjectMapper mapper = new ObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false).configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);


    @Override
    public PacienteDto actualizar(Paciente paciente) {
        return null;
    }

    @Override
    public Paciente guardar(Paciente paciente) {
        return repository.save(paciente);
    }

    @Override
    public void borrarPorId(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<PacienteDto> listar() {
        List<Paciente> listaPacientes = repository.findAll();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return listaPacientes
                .stream()
                .map(paciente -> mapper.convertValue(paciente,PacienteDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PacienteDto> buscarPorId(Integer id) {
        return null;
    }
}
