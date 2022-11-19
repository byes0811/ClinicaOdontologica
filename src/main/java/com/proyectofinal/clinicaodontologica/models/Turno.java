package com.proyectofinal.clinicaodontologica.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Turno {

    private Integer idTurno;
    private Integer idPaciente;
    private Integer idOdontologo;
    private Date fechaCita;
}
