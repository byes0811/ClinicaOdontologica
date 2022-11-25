package com.proyectofinal.clinicaodontologica.repository;

import com.proyectofinal.clinicaodontologica.models.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomicilioRepository extends JpaRepository<Domicilio, Integer> {
}
