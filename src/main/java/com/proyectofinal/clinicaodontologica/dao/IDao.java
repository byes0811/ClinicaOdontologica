package com.proyectofinal.clinicaodontologica.dao;

import java.sql.SQLException;
import java.util.List;

public interface IDao<T> {

    T guardar(T t) throws SQLException, Exception;
    T actualizar(T t) throws SQLException, Exception;
    T buscar(Integer id) throws SQLException, Exception;
    void eliminar(Integer id) throws SQLException, Exception;
    List<T> buscarTodos() throws SQLException, Exception;

}
