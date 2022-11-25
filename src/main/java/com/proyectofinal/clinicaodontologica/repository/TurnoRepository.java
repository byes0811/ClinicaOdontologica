package com.proyectofinal.clinicaodontologica.repository;

import com.proyectofinal.clinicaodontologica.models.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Integer> {
}
