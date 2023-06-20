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

    private ObjectMapper mapper = new ObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false).configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);


    @Override
    public TurnoDto actualizar(Turno turno) {
        return null;
    }

    @Override
    public Turno guardar(Turno turno) {
        if(pacienteService.buscarPorId(turno.getPaciente().getId()).isPresent() && odontologoService.buscarPorId(turno.getOdontologo().getId()).isPresent()){
            return repository.save(turno);
        }else{
            return null; //ACA HAY QUE PONER EXCEPTION
        }
    }

    @Override
    public void borrarPorId(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<TurnoDto> listar() {
        List<Turno> listaTurnos = repository.findAll();
        if(!listaTurnos.isEmpty()){
            return listaTurnos
                    .stream()
                    .map(turno -> mapper.convertValue(turno, TurnoDto.class))
                    .collect(Collectors.toList());
        };
        return null; // ACA VA OTRA EXCEPTION
    }

    @Override
    public Optional<TurnoDto> buscarPorId(Integer id) {
        Optional turno = repository.findById(id);

        if(turno.isPresent()){
            return turno.stream().map(t->mapper.convertValue(t, TurnoDto.class)).findFirst();

        } else {
            return null; // ACA VA UNA EXCEPTION
        }
    }
}
