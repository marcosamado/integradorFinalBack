package com.dh.ClinicaOdontologica.service;

import com.dh.ClinicaOdontologica.dto.OdontologoDto;
import com.dh.ClinicaOdontologica.entity.Odontologo;
import com.dh.ClinicaOdontologica.exception.BadRequestException;
import com.dh.ClinicaOdontologica.exception.NotFoundException;
import com.dh.ClinicaOdontologica.repository.OdontologoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class OdontologServiceImpTest {

    @InjectMocks
    OdontologServiceImp odontologServiceImpTest;
    @Mock
    OdontologoRepository odontologoRepositoryTest;

    @Test
    void buscarOdontologoExistentePorIdPositivo() throws Exception {
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
    public void listarTodosLosOdontologosPositivo() throws Exception {
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
    @Test
    public void testBorrarPorId_Existente() throws Exception {
        //ARRANGE
        Odontologo odontologo = new Odontologo(1,"Amado","Marcos",123);
        when(odontologoRepositoryTest.existsById(odontologo.getId())).thenReturn(true);

        // ACT
        odontologServiceImpTest.borrarPorId(odontologo.getId());

        // ASSERT
        verify(odontologoRepositoryTest, times(1)).deleteById(odontologo.getId());
    }

    @Test
    public void testBorrarPorId_NoExistente() throws Exception {
        //ARRANGE
        Odontologo odontologo = new Odontologo(1,"Amado","Marcos",123);
        when(odontologoRepositoryTest.existsById(odontologo.getId())).thenReturn(false);

        // ACT
        NotFoundException exceptionReponse = assertThrows(NotFoundException.class,() -> {odontologServiceImpTest.borrarPorId(odontologo.getId());});

        // ASSERT
        Assertions.assertEquals("El Odontontologo con id 1 no existe en la base de datos", exceptionReponse.getMessage());
    }

    @Test
    public void guardarOdontologoTestPositivo() throws Exception {
        //ARRANGE
        Odontologo odontologo = new Odontologo(1,"Amado","Marcos",123);
        when(odontologoRepositoryTest.save(odontologo)).thenReturn(odontologo);

        // ACT
        OdontologoDto dtoResponse = odontologServiceImpTest.guardar(odontologo);

        // ASSERT
        Assertions.assertNotNull(dtoResponse);
        Assertions.assertEquals(odontologo.getNombre(), dtoResponse.getNombre());
        Assertions.assertEquals(odontologo.getApellido(), dtoResponse.getApellido());
    }

    @Test
    public void actualizarOdontologoTestPositivo() throws Exception {
        //ARRANGE
        Odontologo odontologo = new Odontologo(1,"Amado","Marcos",123);
        when(odontologoRepositoryTest.findById(odontologo.getId())).thenReturn(Optional.of(odontologo));
        when(odontologoRepositoryTest.save(odontologo)).thenReturn(odontologo);


        // ACT
        OdontologoDto dtoResponse = odontologServiceImpTest.actualizar(odontologo);

        // ASSERT
        Assertions.assertNotNull(dtoResponse);
        Assertions.assertEquals(odontologo.getNombre(), dtoResponse.getNombre());
        Assertions.assertEquals(odontologo.getApellido(), dtoResponse.getApellido());
    }

}