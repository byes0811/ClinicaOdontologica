package com.proyectofinal.clinicaodontologica.dao.impl;

import com.proyectofinal.clinicaodontologica.dao.IDao;
import com.proyectofinal.clinicaodontologica.models.Odontologo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoH2 implements IDao<Odontologo> {

    //final static Logger log = Logger.getLogger(OdontologoDaoH2.class);
    private Connection connection;
    private PreparedStatement preparedStatement;

    //con la instruccion INIT=RUNSCRIPT cuando se conecta a la base ejecuta el script de sql que esta en dicho archivo
    private final static String DB_URL = "jdbc:h2:~/db_clinica;INIT=RUNSCRIPT FROM 'create.sql'";
    private final static String DB_USER ="sa";
    private final static String DB_PASSWORD = "12345";

    @Override
    public Odontologo guardar(Odontologo odontologo) throws Exception {
        //log.debug("guardando un nuevo odontologo");

        try {
            //1 Levantar el driver y Conectarnos
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            //2 Crear una sentencia especificando que el ID lo auto incrementa la base de datos y que nos devuelva esa Key es decir ID
            preparedStatement = connection.prepareStatement("INSERT INTO ODONTOLOGOS(NOMBRE,APELLIDO,MATRICULA) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);
            //No le vamos a pasar el ID ya que hicimos que fuera autoincremental en la base de datos
            //preparedStatement.setInt(1,odontologo.getId());
            preparedStatement.setString(1, odontologo.getNombre());
            preparedStatement.setString(2, odontologo.getApellido());
            preparedStatement.setInt(3, odontologo.getMatricula());

            //3 Ejecutar una sentencia SQL y obtener los ID que se autogeneraron en la base de datos
            preparedStatement.executeUpdate();
            ResultSet keys = preparedStatement.getGeneratedKeys();
            if(keys.next())
                odontologo.setId(keys.getInt(1));

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            preparedStatement.close();
            connection.close();
        }
        return odontologo;
    }

    @Override
    public void eliminar(Integer id) throws Exception {
        //log.debug("Borrando odontologo con id : "+id);
        try {
            //1 Levantar el driver y Conectarnos
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            //2 Crear una sentencia
            preparedStatement = connection.prepareStatement("DELETE FROM ODONTOLOGOS WHERE ID_ODONTOLOGO = ?");
            preparedStatement.setInt(1,id);

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
    public Odontologo buscar(Integer id) throws Exception {
        //log.debug("Buscando al odontologo con id = " + id);
        Odontologo odontologo = null;
        try {
            //1 Levantar el driver y Conectarnos
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            //2 Crear una sentencia
            preparedStatement = connection.prepareStatement("SELECT * FROM ODONTOLOGOS WHERE ID_ODONTOLOGO = ?");
            preparedStatement.setInt(1,id);

            //3 Ejecutar una sentencia SQL
            ResultSet result = preparedStatement.executeQuery();

            //4 Obtener resultados
            while (result.next()) {
                int idPaciente = result.getInt("ID_ODONTOLOGO");
                String nombre = result.getString("NOMBRE");
                String apellido = result.getString("APELLIDO");
                int matricula = result.getInt("MATRICULA");

                odontologo = new Odontologo(idPaciente,nombre,apellido,matricula);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            //log.error(throwables);
        }finally {
            preparedStatement.close();
            connection.close();
        }

        return odontologo;
    }

    @Override
    public List<Odontologo> buscarTodos() throws Exception {
        //log.debug("Buscando todos los odontologos");
        List<Odontologo> odontologos = new ArrayList<>();
        try {
            //1 Levantar el driver y Conectarnos
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            //2 Crear una sentencia
            preparedStatement = connection.prepareStatement("SELECT *  FROM ODONTOLOGOS");

            //3 Ejecutar una sentencia SQL
            ResultSet result = preparedStatement.executeQuery();

            //4 Obtener resultados
            //4 Obtener resultados
            while (result.next()) {
                int idOdontologo = result.getInt("ID_ODONTOLOGO");
                String nombre = result.getString("NOMBRE");
                String apellido = result.getString("APELLIDO");
                int matricula = result.getInt("MATRICULA");

                Odontologo odontologo = new Odontologo(idOdontologo,nombre,apellido,matricula);
                odontologos.add(odontologo);
            }


        } catch (Exception e) {
            e.printStackTrace();
            //log.error(throwables);
        }finally {
            preparedStatement.close();
            connection.close();
        }

        return odontologos;
    }

    @Override
    public Odontologo actualizar(Odontologo odontologo) throws Exception {

        try {
            //1 Levantar el driver y Conectarnos
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            //2 Crear una sentencia
            preparedStatement = connection.prepareStatement("UPDATE ODONTOLOGOS SET NOMBRE = ?, APELLIDO = ?, MATRICULA = ?  WHERE ID_ODONTOLOGOS = ?");
            //No le vamos a pasar el ID ya que hicimos que fuera autoincremental en la base de datos
            //preparedStatement.setInt(1,odontologo.getId());
            preparedStatement.setString(1, odontologo.getNombre());
            preparedStatement.setString(2, odontologo.getApellido());
            preparedStatement.setInt(3, odontologo.getMatricula());
            preparedStatement.setInt(4, odontologo.getId());

            //3 Ejecutar una sentencia SQL
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            //log.error(throwables);
        }finally {
            preparedStatement.close();
            connection.close();
        }
        return odontologo;
    }
}
