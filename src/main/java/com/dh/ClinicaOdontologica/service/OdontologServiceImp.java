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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OdontologServiceImp implements ClinicaOdontologicaService<Odontologo, OdontologoDto>{

    @Autowired
    private OdontologoRepository repository;
    private final ObjectMapper mapper = new ObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false).configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);

    @Override
    public OdontologoDto actualizar(Odontologo odontologo) {
        if(odontologo.getId() != null){
            Odontologo o = repository.save(odontologo);
            return mapper.convertValue(o, OdontologoDto.class);
        } else{
            return null; // DEBE TIRAR UNA EXCEPTION
        }
    }

    @Override
    public OdontologoDto guardar(Odontologo odontologo) {
        Odontologo o = repository.save(odontologo);
        return mapper.convertValue(o,OdontologoDto.class);
    }

    @Override
    public void borrarPorId(Integer id) {
        if(repository.existsById(id)){
            repository.deleteById(id);
        }
        // SI NO ENCUENTRA EL ID DEBE TIRAR UNA EXPECTION
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
    public Optional<OdontologoDto> buscarPorId(Integer id) {
        Optional odontologo = repository.findById(id);

        if(odontologo.isPresent()){
            return odontologo.stream().map(o->mapper.convertValue(o, OdontologoDto.class)).findFirst();
        } else {
            return null;
        }
    }
}
