package com.proyectofinal.clinicaodontologica.repository;

import com.proyectofinal.clinicaodontologica.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;
@Repository
@Transactional
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
    Optional<AppUser> findByEmail(String email);
}
