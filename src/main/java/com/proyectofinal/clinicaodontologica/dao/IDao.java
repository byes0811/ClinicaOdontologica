package com.proyectofinal.clinicaodontologica.dao;

import java.sql.SQLException;
import java.util.List;

public interface IDao<T> {

    T guardar(T t) throws SQLException;
    T actualizar(T t) throws SQLException;
    T buscar(Integer id) throws SQLException;
    void eliminar(Integer id) throws SQLException;
    List<T> buscarTodos() throws SQLException;

}
