package com.dh.ClinicaOdontologica.service;

import com.dh.ClinicaOdontologica.exception.BadRequestException;
import com.dh.ClinicaOdontologica.exception.NotFoundException;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClinicaOdontologicaService <T,E> {
    public E actualizar(T t) throws Exception;
    public E guardar (T t) throws Exception;
    public void borrarPorId(Integer id) throws Exception;
    public List<E> listar() throws Exception;
    public Optional<E> buscarPorId(Integer id) throws Exception;
}
