package com.proyectofinal.clinicaodontologica.dao.impl;

import com.proyectofinal.clinicaodontologica.dao.IDao;
import com.proyectofinal.clinicaodontologica.models.Turno;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TurnoDaoH2 implements IDao<Turno> {

    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    //con la instruccion INIT=RUNSCRIPT cuando se conecta a la base ejecuta el script de sql que esta en dicho archivo
    private final static String DB_URL = "jdbc:h2:~/db_clinica;INIT=RUNSCRIPT FROM 'create.sql'";
    private final static String DB_USER = "sa";
    private final static String DB_PASSWORD = "";

    private PacienteDaoH2 pacienteDaoH2 = new PacienteDaoH2();
    private OdontologoDaoH2 odontologoDaoH2 = new OdontologoDaoH2();


    @Override
    public Turno guardar(Turno turno) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //1 Levantar el driver y Conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            /*
            Paciente paciente = pacienteDaoH2.guardar(turno.getPaciente());
            turno.getPaciente().setId(paciente.getId());

            Odontologo odontologo = odontologoDaoH2.guardar(turno.getOdontologo());
            turno.getOdontologo().setId(odontologo.getId());*/

            //2 Crear una sentencia especificando que el ID lo auto incrementa la base de datos y que nos devuelva esa Key es decir ID
            preparedStatement = connection.prepareStatement("INSERT INTO TURNOS(ID_PACIENTE,ID_ODONTOLOGO,FECHA_CITA) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);

            //No le vamos a pasar el ID ya que hicimos que fuera autoincremental en la base de datos
            //preparedStatement.setInt(1,domicilio.getId());
            preparedStatement.setInt(1, turno.getIdPaciente());
            preparedStatement.setInt(2, turno.getIdOdontologo());
            preparedStatement.setDate(3, turno.getFechaCita());

            //3 Ejecutar una sentencia SQL y obtener los ID que se autogeneraron en la base de datos
            preparedStatement.executeUpdate();
            ResultSet keys = preparedStatement.getGeneratedKeys();
            if (keys.next())
                turno.setIdTurno(keys.getInt(1));

            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return turno;
    }

    @Override
    public Turno actualizar(Turno turno) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //1 Levantar el driver y Conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            /*Paciente paciente = pacienteDaoH2.actualizar(turno.getPaciente());

            Odontologo odontologo = odontologoDaoH2.actualizar(turno.getOdontologo());*/

            //2 Crear una sentencia especificando que el ID lo auto incrementa la base de datos y que nos devuelva esa Key es decir ID
            preparedStatement = connection.prepareStatement("UPDATE TURNOS SET ID_PACIENTE=?, ID_ODONTOLOGO=?, FECHA_CITA=? WHERE ID_TURNO = ?");
            //No le vamos a pasar el ID ya que hicimos que fuera autoincremental en la base de datos
            //preparedStatement.setInt(1,paciente.getId());

            //Tenemos que pasarle la clave foranea del ID del domicilio es decir el campo domicilio_id
            preparedStatement.setInt(1, turno.getIdPaciente());
            preparedStatement.setInt(2, turno.getIdOdontologo());
            preparedStatement.setDate(3, turno.getFechaCita());
            preparedStatement.setInt(4, turno.getIdTurno());

            //3 Ejecutar una sentencia SQL
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return turno;
    }

    @Override
    public Turno buscar(Integer id) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Turno turno = null;
        try {
            //1 Levantar el driver y Conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            //2 Crear una sentencia
            preparedStatement = connection.prepareStatement("SELECT *  FROM TURNOS WHERE ID_TURNO = ?");
            preparedStatement.setInt(1, id);

            //3 Ejecutar una sentencia SQL
            ResultSet result = preparedStatement.executeQuery();

            //4 Obtener resultados
            while (result.next()) {
                Integer idTurno = result.getInt("ID_TURNO");
                Integer idPaciente = result.getInt("ID_PACIENTE");
                Integer idOdontologo = result.getInt("ID_ODONTOLOGO");
                Date fechaCita = result.getDate("FECHA_CITA");

                turno = new Turno(idTurno, idPaciente, idOdontologo, fechaCita);
            }

            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return turno;
    }

    @Override
    public void eliminar(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            //1 Levantar el driver y Conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            //2 Crear una sentencia
            preparedStatement = connection.prepareStatement("DELETE FROM TURNOS WHERE ID_TURNO = ?");
            preparedStatement.setInt(1, id);

            //3 Ejecutar una sentencia SQL
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Turno> buscarTodos() {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Turno> turnos = new ArrayList<>();
        try {
            //1 Levantar el driver y Conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            //2 Crear una sentencia
            preparedStatement = connection.prepareStatement("SELECT *  FROM TURNOS");

            //3 Ejecutar una sentencia SQL
            ResultSet result = preparedStatement.executeQuery();

            //4 Obtener resultados
            while (result.next()) {

                int idTurno = result.getInt("ID_TURNO");
                int idPaciente = result.getInt("ID_PACIENTE");
                int idOdontologo = result.getInt("ID_ODONTOLOGO");
                Date fechaCita = result.getDate("FECHA_CITA");

                Turno turno = new Turno(idTurno, idPaciente, idOdontologo, fechaCita);
                turnos.add(turno);
            }

            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return turnos;
    }
}
