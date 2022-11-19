package com.proyectofinal.clinicaodontologica.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Odontologo {

    private Integer id;
    private String nombre;
    private String apellido;
    private Integer matricula;

    @Override
    public String toString() {
        return "Odontólogo{" +
                ", matricula=" + matricula +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                '}';
    }
}