package com.dh.ClinicaOdontologica.service;

import com.dh.ClinicaOdontologica.dto.PacienteDto;
import com.dh.ClinicaOdontologica.entity.Paciente;
import com.dh.ClinicaOdontologica.exception.BadRequestException;
import com.dh.ClinicaOdontologica.exception.NotFoundException;
import com.dh.ClinicaOdontologica.repository.PacienteRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class PacienteServiceImp implements ClinicaOdontologicaService<Paciente, PacienteDto> {
    @Autowired
    private PacienteRepository repository;
    private final ObjectMapper mapper = new ObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false).configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);


    @Override
    public PacienteDto actualizar(Paciente paciente) throws Exception{
        if(repository.findById(paciente.getId()).isPresent()){
            if(paciente.getDomicilio() == null) {
                throw new BadRequestException("Codigo 203", "No puedes agregar un paciente sin domicilio.");
            } else if(paciente.getNombre() == null && paciente.getNombre().equals("") && paciente.getApellido() == null && paciente.getApellido().equals("") && paciente.getDni() == null && paciente.getDni().equals("") && paciente.getFechaDeAlta() == null && paciente.getFechaDeAlta().toString().equals("") && paciente.getDomicilio().getLocalidad() == null && paciente.getDomicilio().getLocalidad().equals("") && paciente.getDomicilio().getProvincia() == null && paciente.getDomicilio().getProvincia().toString().equals("")){
                throw new BadRequestException("Codigo 204", "No puedes agregar un paciente con campos vacios o nulos");
            } else if(paciente.getDni().length() != 8) {
                throw new BadRequestException("Codigo 205", "El dni debe contener 8 digitos");
            }else if (repository.findByDni(paciente.getDni()).isPresent()){
                throw new BadRequestException("codigo 207", "El Dni ya existe en la base de datos");
            }else if(!paciente.getDni().matches("[0-9]+")){
                throw new BadRequestException("codigo 208", "El dni solo debe contener numeros");
            }
            else {
                Paciente p = repository.save(paciente);
                return mapper.convertValue(p, PacienteDto.class);
            }
        }else{
            throw new NotFoundException("codigo-203", "El Paciente con id " + paciente.getId() + " no Existe en la base de datos");
        }
    }

    @Override
    public PacienteDto guardar(Paciente paciente) throws Exception{

            if(paciente.getDomicilio() == null) {
                throw new BadRequestException("Codigo 203", "No puedes agregar un paciente sin domicilio.");
            } else if(paciente.getNombre() == null && paciente.getNombre().equals("") && paciente.getApellido() == null && paciente.getApellido().equals("") && paciente.getDni() == null && paciente.getDni().equals("") && paciente.getFechaDeAlta() == null && paciente.getFechaDeAlta().equals("") && paciente.getDomicilio().getLocalidad() == null && paciente.getDomicilio().getLocalidad().equals("") && paciente.getDomicilio().getProvincia() == null && paciente.getDomicilio().getProvincia().equals("")){
                throw new BadRequestException("Codigo 204", "No puedes agregar un paciente con campos vacios o nulos");
            } else if(paciente.getDni().length() != 8) {
                throw new BadRequestException("Codigo 205", "El dni debe contener 8 digitos");
            }else if (repository.findByDni(paciente.getDni()).isPresent()){
                throw new BadRequestException("codigo 207", "El dni ya se encuentra registrado en la base de datos");
            }else if(!paciente.getDni().matches("[0-9]+")){
                throw new BadRequestException("codigo 208", "El dni solo debe contener numeros");
            }
            else {
            Paciente p = repository.save(paciente);

            return mapper.convertValue(p,PacienteDto.class);
        }
    }

    @Override
    public void borrarPorId(Integer id) throws Exception{
        if(repository.existsById(id)){
            repository.deleteById(id);
        }else {
            throw new NotFoundException("codigo-201", "El Paciente con id: " + id + " no existe en la base de datos");
        }
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
        }else{
            throw new NotFoundException("codigo-202", "No se encontraron Pacientes registrados");
        }
    }

    @Override
    public Optional<PacienteDto> buscarPorId(Integer id) throws Exception {
        Optional<Paciente> paciente = repository.findById(id);

        if(paciente.isPresent()){
            return paciente.stream().map(p->mapper.convertValue(p, PacienteDto.class)).findFirst();

        } else {
            throw new NotFoundException("codigo-203", "El Paciente con id " + id + " no Existe en la base de datos");

        }
    }
}
