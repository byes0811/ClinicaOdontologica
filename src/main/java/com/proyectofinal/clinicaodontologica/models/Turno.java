package com.proyectofinal.clinicaodontologica.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Turno {

    private Integer id;
    private Integer id_paciente;
    private Integer id_odontologo;
    private Date fecha_cita;
}
