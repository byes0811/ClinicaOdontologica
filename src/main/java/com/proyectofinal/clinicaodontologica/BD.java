package com.proyectofinal.clinicaodontologica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class BD {
    private static final String SQL_CREATE_TABLE =" DROP TABLE IF EXISTS ODONTOLOGOS; CREATE TABLE ODONTOLOGOS " +
            "(ID INT AUTO_INCREMENT PRIMARY KEY, " +
            "MATRICULA INT NOT NULL, " +
            "NOMBRE varchar(100), " +
            "APELLIDO varchar(100)); ";

    public static void crearBD() throws Exception {

        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        statement.execute(SQL_CREATE_TABLE);

    }
    public static Connection getConnection() throws Exception{
        return DriverManager.getConnection("jdbc:h2:~/test","sa","");
    }
}
