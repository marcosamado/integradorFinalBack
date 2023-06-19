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
    private ObjectMapper mapper = new ObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false).configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);

    @Override
    public OdontologoDto actualizar(Odontologo odontologo) {
        if(odontologo.getId() != null){
            Odontologo o = repository.save(odontologo);
            return mapper.convertValue(o, OdontologoDto.class);
        } else{
            return null;
        }
    }

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        return repository.save(odontologo);
    }

    @Override
    public void borrarPorId(Integer id) {
        repository.deleteById(id);
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

//        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
//        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        if(odontologo.isPresent()){
            return odontologo.stream().map(o->mapper.convertValue(o, OdontologoDto.class)).findFirst();
        } else {
            return null;
        }
    }
}
