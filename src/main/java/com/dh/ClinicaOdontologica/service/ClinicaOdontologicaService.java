package com.dh.ClinicaOdontologica.service;

import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ClinicaOdontologicaService <T,E> {
    public T actualizar(Integer id,T t);
    public T guardar (T t);
    public void borrarPorId(Integer id);
    public List<E> listar();
    public E buscarPorId(Integer id);
}
