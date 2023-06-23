package com.dh.ClinicaOdontologica.service;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClinicaOdontologicaService <T,E> {
    public E actualizar(T t);
    public E guardar (T t);
    public void borrarPorId(Integer id);
    public List<E> listar();
    public Optional<E> buscarPorId(Integer id);
}
