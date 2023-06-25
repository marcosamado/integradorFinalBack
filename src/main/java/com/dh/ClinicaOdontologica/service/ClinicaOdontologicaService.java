package com.dh.ClinicaOdontologica.service;

import com.dh.ClinicaOdontologica.exception.BadRequestException;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClinicaOdontologicaService <T,E> {
    public E actualizar(T t);
    public E guardar (T t) throws BadRequestException;
    public void borrarPorId(Integer id);
    public List<E> listar();
    public Optional<E> buscarPorId(Integer id);
}
