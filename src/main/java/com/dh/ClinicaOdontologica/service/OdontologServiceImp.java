package com.dh.ClinicaOdontologica.service;

import com.dh.ClinicaOdontologica.dto.OdontologoDto;
import com.dh.ClinicaOdontologica.entity.Odontologo;
import com.dh.ClinicaOdontologica.exception.BadRequestException;
import com.dh.ClinicaOdontologica.exception.NotFoundException;
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
    public OdontologoDto actualizar(Odontologo odontologo) throws Exception{
        if(odontologo.getId() != null){
            Odontologo o = repository.save(odontologo);
            return mapper.convertValue(o, OdontologoDto.class);
        } else{
            return null; // DEBE TIRAR UNA EXCEPTION
        }
    }

    @Override
    public OdontologoDto guardar(Odontologo odontologo) throws Exception {

        if(odontologo.getMatricula() < 0 || odontologo.getMatricula() > 10000){
            throw new BadRequestException("codigo-100", "La matricula debe ser mayor a 0 y menos a 10.000");
        }else{
            Odontologo o = repository.save(odontologo);
            return mapper.convertValue(o,OdontologoDto.class);
        }
    }

    @Override
    public void borrarPorId(Integer id) throws Exception{
        if(repository.existsById(id)){
            repository.deleteById(id);
        }
        throw new NotFoundException("codigo-101", "El Odontontologo con id " + id + " no existe en la base de datos");
    }

    @Override
    public List<OdontologoDto> listar() throws Exception{
        List<Odontologo> listaOdontologo = repository.findAll();
        if(!listaOdontologo.isEmpty()){
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return listaOdontologo
                .stream()
                .map(odontologo -> mapper.convertValue(odontologo, OdontologoDto.class))
                .collect(Collectors.toList());
        }
        throw new NotFoundException("codigo-102", "No se encontraron odontologos registrados");
    }

    @Override
    public Optional<OdontologoDto> buscarPorId(Integer id) throws Exception {
        Optional<Odontologo> odontologo = repository.findById(id);

        if(odontologo.isPresent()){
            return odontologo.stream().map(o->mapper.convertValue(o, OdontologoDto.class)).findFirst();
        } else {
            throw new NotFoundException("codigo-101", "El Odontontologo con id " + id + " no existe en la base de datos");

        }
    }
}
