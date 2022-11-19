package com.proyectofinal.clinicaodontologica.dao.impl;

import com.proyectofinal.clinicaodontologica.dao.IDao;
import com.proyectofinal.clinicaodontologica.models.Domicilio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DomicilioDaoH2 implements IDao<Domicilio> {
    private Connection connection;
    private PreparedStatement preparedStatement;
    //con la instruccion INIT=RUNSCRIPT cuando se conecta a la base ejecuta el script de sql que esta en dicho archivo
    private static final String DB_URL = "jdbc:h2:~/db_clinica;INIT=RUNSCRIPT FROM 'create.sql'";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "12345";


    @Override
    public Domicilio guardar(Domicilio domicilio) throws SQLException {

        try {
            //1 Levantar el driver y Conectarnos
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            //2 Crear una sentencia especificando que el ID lo auto incrementa la base de datos y que nos devuelva esa Key es decir ID
            preparedStatement = connection.prepareStatement("INSERT INTO DOMICILIOS(CALLE,NUMERO,LOCALIDAD,PROVINCIA) VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            //No le vamos a pasar el ID ya que hicimos que fuera autoincremental en la base de datos
            preparedStatement.setString(1, domicilio.getCalle());
            preparedStatement.setString(2, domicilio.getNumero());
            preparedStatement.setString(3, domicilio.getLocalidad());
            preparedStatement.setString(4, domicilio.getProvincia());

            //3 Ejecutar una sentencia SQL y obtener los ID que se autogeneraron en la base de datos
            preparedStatement.executeUpdate();
            ResultSet keys = preparedStatement.getGeneratedKeys();
            if (keys.next())
                domicilio.setId(keys.getInt(1));

        } catch (SQLException | NullPointerException throwables) {
            throwables.printStackTrace();
        }finally {
            preparedStatement.close();
            connection.close();
        }
        return domicilio;
    }

    @Override
    public void eliminar(Integer id) throws SQLException {

        try {
            //1 Levantar el driver y Conectarnos
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            //2 Crear una sentencia
            preparedStatement = connection.prepareStatement("DELETE FROM DOMICILIOS WHERE ID_DOMICILIO = ?");
            preparedStatement.setInt(1, id);

            //3 Ejecutar una sentencia SQL
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            preparedStatement.close();
            connection.close();
        }
    }

    @Override
    public Domicilio buscar(Integer id) throws SQLException {

        Domicilio domicilio = null;

        try {
            //1 Levantar el driver y Conectarnos
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            //2 Crear una sentencia
            preparedStatement = connection.prepareStatement("SELECT * FROM DOMICILIOS WHERE ID_DOMICILIO = ?");
            preparedStatement.setInt(1, id);

            //3 Ejecutar una sentencia SQL
            ResultSet result = preparedStatement.executeQuery();

            //4 Obtener resultados
            while (result.next()) {
                int idDomicilio = result.getInt("ID_DOMICILIO");
                String calle = result.getString("CALLE");
                String numero = result.getString("NUMERO");
                String localidad = result.getString("LOCALIDAD");
                String provincia = result.getString("PROVINCIA");

                domicilio = new Domicilio(idDomicilio, calle, numero, localidad, provincia);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            preparedStatement.close();
            connection.close();
        }

        return domicilio;
    }

    @Override
    public List<Domicilio> buscarTodos() throws SQLException {

        List<Domicilio> domicilios = new ArrayList<>();

        try {
            //1 Levantar el driver y Conectarnos
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            //2 Crear una sentencia
            preparedStatement = connection.prepareStatement("SELECT * FROM DOMICILIOS");

            //3 Ejecutar una sentencia SQL
            ResultSet result = preparedStatement.executeQuery();

            //4 Obtener resultados
            while (result.next()) {

                int idDomicilio = result.getInt("ID_DOMICILIO");
                String calle = result.getString("CALLE");
                String numero = result.getString("NUMERO");
                String localidad = result.getString("LOCALIDAD");
                String provincia = result.getString("PROVINCIA");

                Domicilio domicilio = new Domicilio(idDomicilio, calle, numero, localidad, provincia);
                domicilios.add(domicilio);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            preparedStatement.close();
            connection.close();
        }
        return domicilios;
    }

    @Override
    public Domicilio actualizar(Domicilio domicilio) throws SQLException {
        try {
            //1 Levantar el driver y Conectarnos
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement = connection.prepareStatement("UPDATE DOMICILIOS SET CALLE = ?, NUMERO = ? , LOCALIDAD = ?, PROVINCIA = ?  WHERE ID_DOMICILIO = ?");

            //No le vamos a pasar el ID ya que hicimos que fuera autoincremental en la base de datos
            preparedStatement.setString(1, domicilio.getCalle());
            preparedStatement.setString(2, domicilio.getNumero());
            preparedStatement.setString(3, domicilio.getLocalidad());
            preparedStatement.setString(4, domicilio.getProvincia());
            preparedStatement.setInt(5, domicilio.getId());

            //3 Ejecutar una sentencia SQL y obtener los ID que se autogeneraron en la base de datos
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            preparedStatement.close();
            connection.close();
        }
        return domicilio;
    }
}