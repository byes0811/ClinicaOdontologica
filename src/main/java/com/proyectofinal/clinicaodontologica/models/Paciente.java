package com.proyectofinal.clinicaodontologica.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Paciente {

    private Integer id;
    private String nombre;
    private String apellido;
    private int dni;
    private Date fechaIngreso;
    private Domicilio domicilio;
}
