package com.dh.ClinicaOdontologica.service;


import com.dh.ClinicaOdontologica.dto.OdontologoDto;
import com.dh.ClinicaOdontologica.dto.PacienteDto;
import com.dh.ClinicaOdontologica.dto.TurnoDto;
import com.dh.ClinicaOdontologica.entity.Turno;
import com.dh.ClinicaOdontologica.exception.BadRequestException;
import com.dh.ClinicaOdontologica.exception.NotFoundException;
import com.dh.ClinicaOdontologica.repository.TurnoRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TurnoServiceImp  implements ClinicaOdontologicaService<Turno, TurnoDto> {

    @Autowired
    private TurnoRepository repository;
    @Autowired
    private PacienteServiceImp pacienteService;

    @Autowired
    private OdontologServiceImp odontologoService;

    private final ObjectMapper mapper = new ObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false).configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);


    @Override
    public TurnoDto actualizar(Turno turno) throws Exception{
        if(turno.getId() != null){
            Turno t = repository.save(turno);
            return mapper.convertValue(t, TurnoDto.class);
        } else{
            return null;
        }
    }

    @Override
    public TurnoDto guardar(Turno turno) throws Exception {

        if(turno.getHora() != null && turno.getFecha() != null){
            Optional<PacienteDto> paciente = pacienteService.buscarPorId(turno.getPaciente().getId());
            Optional<OdontologoDto> odontologo = odontologoService.buscarPorId(turno.getOdontologo().getId());
            Turno turnoReponse = repository.save(turno);
            TurnoDto turnoDto = mapper.convertValue(turnoReponse, TurnoDto.class);

            turnoDto.getPaciente().setApellido(paciente.get().getApellido());
            turnoDto.getPaciente().setNombre(paciente.get().getNombre());
            turnoDto.getOdontologo().setApellido(odontologo.get().getApellido());
            turnoDto.getOdontologo().setNombre(odontologo.get().getNombre());
            return turnoDto;
        }else{
            throw new BadRequestException("codigo-300","Debes ingresar una fecha y hora valida");

        }
    }

    @Override
    public void borrarPorId(Integer id) throws Exception{
        if(repository.existsById(id)){
            repository.deleteById(id);
        }else{
            throw new NotFoundException("codigo-301", "El Turno con id: " + id + " no existe en la base de datos");
        }
    }

    @Override
    public List<TurnoDto> listar() throws Exception {
        List<Turno> listaTurnos = repository.findAll();
        if(!listaTurnos.isEmpty()){
            return listaTurnos
                    .stream()
                    .map(turno -> mapper.convertValue(turno, TurnoDto.class))
                    .collect(Collectors.toList());
        }else{
            throw new NotFoundException("codigo-302", "No se encontraron turnos registrados");
        }
    }

    @Override
    public Optional<TurnoDto> buscarPorId(Integer id) throws Exception {
        Optional<Turno> turno = repository.findById(id);

        if(turno.isPresent()){
            return turno.stream().map(t->mapper.convertValue(t, TurnoDto.class)).findFirst();

        } else {
            throw new NotFoundException("codigo-301", "El Turno con id: " + id + " no existe en la base de datos");
        }
    }
}
