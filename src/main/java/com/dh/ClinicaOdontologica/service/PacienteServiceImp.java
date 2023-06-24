package com.dh.ClinicaOdontologica.service;

import com.dh.ClinicaOdontologica.dto.PacienteDto;
import com.dh.ClinicaOdontologica.dto.TurnoDto;
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
        if(paciente.getId() != null){
            Paciente p = repository.save(paciente);
            return mapper.convertValue(p, PacienteDto.class);
        } else{
            return null;
        }
    }

    @Override
    public PacienteDto guardar(Paciente paciente) {
        Paciente p = repository.save(paciente);
        return mapper.convertValue(p,PacienteDto.class);
    }

    @Override
    public void borrarPorId(Integer id) {
        if(repository.existsById(id)){
            repository.deleteById(id);
        }
        // SI NO ENCUENTRA EL ID TIRAR UNA EXCEPTION
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

        Optional<Paciente> paciente = repository.findById(id);

        if(paciente.isPresent()){
            return paciente.stream().map(p->mapper.convertValue(p, PacienteDto.class)).findFirst();

        } else {
            return null; // ACA VA UNA EXCEPTION
        }
    }
}
