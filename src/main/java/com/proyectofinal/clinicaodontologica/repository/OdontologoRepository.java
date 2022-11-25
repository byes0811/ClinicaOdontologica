package com.proyectofinal.clinicaodontologica.repository;

import com.proyectofinal.clinicaodontologica.models.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OdontologoRepository extends JpaRepository<Odontologo, Integer> {
}
