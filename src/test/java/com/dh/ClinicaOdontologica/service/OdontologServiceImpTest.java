package com.dh.ClinicaOdontologica.service;

import com.dh.ClinicaOdontologica.dto.OdontologoDto;
import com.dh.ClinicaOdontologica.entity.Odontologo;
import com.dh.ClinicaOdontologica.repository.OdontologoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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

    @Test
    public void listarTodosLosOdontologos() throws Exception {
        //ARRANGE
        Odontologo odontologoTest1 = new Odontologo(1,"Herrera","Walter",123);
        Odontologo odontologoTest2 = new Odontologo(2,"Amado","Marcos",33);
        List<Odontologo> listado = new ArrayList<>();
        listado.add(odontologoTest1);
        listado.add(odontologoTest2);
        when(odontologoRepositoryTest.findAll()).thenReturn(listado);

        //ACT
        List<OdontologoDto> resultado = odontologServiceImpTest.listar();
        //ASSERT

        Assertions.assertEquals(2, resultado.size());
        Assertions.assertTrue(resultado.stream().anyMatch(oDto -> oDto.getNombre().equals("Marcos") && oDto.getApellido().equals("Amado")));
    }
//    @Test
//    void borrarPorId_OdontologoExistente_OdontologoEliminado() throws Exception {
//        // Arrange
//        Odontologo odontologo = new Odontologo(1, "Arce", "Alberto", 1000);
//        when(odontologoRepositoryTest.findById(odontologo.getId())).thenReturn(Optional.of(odontologo));
//
//        // Act
//        odontologServiceImpTest.borrarPorId(odontologo.getId());
//
//        // Assert
//        verify(odontologoRepositoryTest, times(1)).deleteById(odontologo.getId());
//    }

}