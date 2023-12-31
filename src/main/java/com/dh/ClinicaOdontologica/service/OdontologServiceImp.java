package com.dh.ClinicaOdontologica.service;

import com.dh.ClinicaOdontologica.dto.OdontologoDto;
import com.dh.ClinicaOdontologica.entity.Odontologo;
import com.dh.ClinicaOdontologica.exception.BadRequestException;
import com.dh.ClinicaOdontologica.exception.GlobalExceptionHandler;
import com.dh.ClinicaOdontologica.exception.NotFoundException;
import com.dh.ClinicaOdontologica.repository.OdontologoRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OdontologServiceImp implements ClinicaOdontologicaService<Odontologo, OdontologoDto>{

    @Autowired
    private OdontologoRepository repository;
    private final ObjectMapper mapper = new ObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false).configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);

    private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);


    @Override
    public OdontologoDto actualizar(Odontologo odontologo) throws Exception{
        if(repository.findById(odontologo.getId()).isPresent()){
            if(odontologo.getNombre() == null || odontologo.getMatricula() == null || odontologo.getApellido() == null || odontologo.getNombre().length() < 3 || odontologo.getApellido().length() < 3 || Objects.equals(odontologo.getNombre(), "") || odontologo.getMatricula().toString().equals("") || Objects.equals(odontologo.getApellido(), "")){
                throw new BadRequestException("codigo-104", "Alguno de los datos son erroneos - (los datos del odontologo no pueden estar vacios o ser nulos)");
            }else if(odontologo.getMatricula() < 0 || odontologo.getMatricula() > 10000) {
                throw new BadRequestException("codigo-100", "La matricula debe ser mayor a 0 y menos a 10.000");
            }else{
                Odontologo o = repository.save(odontologo);
                logger.info("Se Modifico el odontologo con id "+ odontologo.getId() + " en la base de datos");
                return mapper.convertValue(o, OdontologoDto.class);
            }
        } else{
            throw new NotFoundException("codigo-101", "El Odontontologo con id " + odontologo.getId() + " no existe en la base de datos");
        }
    }

    @Override
    public OdontologoDto guardar(Odontologo odontologo) throws Exception {

        if(odontologo.getNombre() == null || odontologo.getMatricula() == null || odontologo.getApellido() == null || odontologo.getNombre().length() < 3 || odontologo.getApellido().length() < 3 || Objects.equals(odontologo.getNombre(), "") || odontologo.getMatricula().toString().equals("") || Objects.equals(odontologo.getApellido(), "")){
            throw new BadRequestException("codigo-104", "Alguno de los datos son erroneos - (los datos del odontologo no pueden estar vacios o ser nulos y los mismos deben ser mayor a 3 caracteres)");
        }else if(odontologo.getMatricula() < 0 || odontologo.getMatricula() > 10000){
            throw new BadRequestException("codigo-100", "La matricula debe ser mayor a 0 y menos a 10.000");
        }else{
            Odontologo o = repository.save(odontologo);
            logger.info("Se agrego un nuevo odontologo a la base de datos");
            return mapper.convertValue(o,OdontologoDto.class);
        }
    }

    @Override
    public void borrarPorId(Integer id) throws Exception{
        if(repository.existsById(id)){
            repository.deleteById(id);
            logger.info("Se Elimino el odontologo con id "+ id + " de la base de datos");
        }else{
            throw new NotFoundException("codigo-101", "El Odontontologo con id " + id + " no existe en la base de datos");
        }
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
        }else{
            throw new NotFoundException("codigo-102", "No se encontraron odontologos registrados");
        }
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
