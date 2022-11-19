package com.proyectofinal.clinicaodontologica.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Domicilio {

    private Integer id;
    private String calle;
    private String numero;
    private String localidad;
    private String provincia;
}
