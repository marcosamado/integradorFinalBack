package com.dh.ClinicaOdontologica.service;

import com.dh.ClinicaOdontologica.dto.OdontologoDto;
import com.dh.ClinicaOdontologica.dto.PacienteDto;
import com.dh.ClinicaOdontologica.entity.Odontologo;
import com.dh.ClinicaOdontologica.entity.Paciente;
import com.dh.ClinicaOdontologica.exception.BadRequestException;
import com.dh.ClinicaOdontologica.exception.NotFoundException;
import com.dh.ClinicaOdontologica.repository.PacienteRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class PacienteServiceImp implements ClinicaOdontologicaService<Paciente, PacienteDto> {
    @Autowired
    private PacienteRepository repository;
    private final ObjectMapper mapper = new ObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false).configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);


    @Override
    public PacienteDto actualizar(Paciente paciente) throws Exception{
        if(paciente.getId() != null){
            Paciente p = repository.save(paciente);
            return mapper.convertValue(p, PacienteDto.class);
        } else{
            return null;
        }
    }

    @Override
    public PacienteDto guardar(Paciente paciente) throws Exception{

        if(paciente.getDomicilio() == null){
            throw new BadRequestException("codigo-200", "No puedes agregar un paciente sin Domicilio");
        }else{
            Paciente p = repository.save(paciente);
            return mapper.convertValue(p,PacienteDto.class);
        }
    }

    @Override
    public void borrarPorId(Integer id) throws Exception{
        if(repository.existsById(id)){
            repository.deleteById(id);
        }
        throw new NotFoundException("codigo-201", "El Turno con id: " + id + " no existe en la base de datos");
    }

    @Override
    public List<PacienteDto> listar() throws Exception{
        List<Paciente> listaPacientes = repository.findAll();
        if(!listaPacientes.isEmpty()){
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return listaPacientes
                .stream()
                .map(paciente -> mapper.convertValue(paciente,PacienteDto.class))
                .collect(Collectors.toList());
        }
        throw new NotFoundException("codigo-202", "No se encontraron pacientes registrados");
    }

    @Override
    public Optional<PacienteDto> buscarPorId(Integer id) throws Exception {
        Optional<Paciente> paciente = repository.findById(id);

        if(paciente.isPresent()){
            return paciente.stream().map(p->mapper.convertValue(p, PacienteDto.class)).findFirst();

        } else {
            throw new NotFoundException("codigo-201", "El Paciente con id " + id + " no Existe en la base de datos");

        }
    }
}
