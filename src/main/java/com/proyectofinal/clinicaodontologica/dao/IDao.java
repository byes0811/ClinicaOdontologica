package com.proyectofinal.clinicaodontologica.dao;

import java.util.List;

public interface IDao<T> {

    T guardar(T t);
    T actualizar(T t);
    T buscar(Integer id);
    void eliminar(Integer id);
    List<T> buscarTodos();

}
