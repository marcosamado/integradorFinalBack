package com.dh.ClinicaOdontologica.service;

import com.dh.ClinicaOdontologica.dto.OdontologoDto;
import com.dh.ClinicaOdontologica.entity.Odontologo;
import com.dh.ClinicaOdontologica.repository.OdontologoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class OdontologServiceImpTest {

    @InjectMocks
    OdontologServiceImp odontologServiceImpTest;
    @Mock
    OdontologoRepository odontologoRepositoryTest;

    @Test
    void buscarOdontologoExistentePorId() throws Exception {
        //ARRANGE
        Odontologo odontologoTest = new Odontologo(1,"vidal","pablo",123);

        //LE DIGO AL REPOSITORY MOCK QUE CUANDO SE EJECUTE EL FIND BY ID SIEMPRE ME RETORNE LO QUE YO QUIERO
        when(odontologoRepositoryTest.findById(any())).thenReturn(Optional.of(odontologoTest));


        //ACT
        Optional<OdontologoDto> odontologoDto = odontologServiceImpTest.buscarPorId(1);
        //ASSERT
        Assertions.assertEquals("pablo", odontologoDto.get().getNombre());
    }

}