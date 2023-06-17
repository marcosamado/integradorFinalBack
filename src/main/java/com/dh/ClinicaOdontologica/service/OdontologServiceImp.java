package com.dh.ClinicaOdontologica.service;

import com.dh.ClinicaOdontologica.dto.OdontologoDto;
import com.dh.ClinicaOdontologica.entity.Odontologo;
import com.dh.ClinicaOdontologica.repository.OdontologoRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OdontologServiceImp implements ClinicaOdontologicaService<Odontologo, OdontologoDto>{

    @Autowired
    private OdontologoRepository repository;

    @Override
    public Odontologo actualizar(Integer id, Odontologo odontologo) {
        return null;
    }

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        return repository.save(odontologo);
    }

    @Override
    public void borrarPorId(Integer id) {

    }

    @Override
    public List<OdontologoDto> listar() {
        List<Odontologo> listaOdontologo = repository.findAll();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return listaOdontologo
                .stream()
                .map(odontologo -> mapper.convertValue(odontologo, OdontologoDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public OdontologoDto buscarPorId(Integer id) {
        return null;
    }
}
