package com.proyectofinal.clinicaodontologica.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "domicilios")
public class Domicilio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private String calle;
    @Column
    private String numero;
    @Column
    private String localidad;
    @Column
    private String provincia;

}
