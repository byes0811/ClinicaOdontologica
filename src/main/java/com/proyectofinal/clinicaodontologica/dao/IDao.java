package com.proyectofinal.clinicaodontologica.dao;

import java.sql.SQLException;
import java.util.List;

public interface IDao<T> {

    T guardar(T t) throws SQLException;
    T actualizar(T t);
    T buscar(Integer id);
    void eliminar(Integer id);
    List<T> buscarTodos();

}
