package com.proyectofinal.clinicaodontologica.services;

import com.proyectofinal.clinicaodontologica.models.AppUser;
import com.proyectofinal.clinicaodontologica.models.AppUserRol;
import com.proyectofinal.clinicaodontologica.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password1 = passwordEncoder.encode("1234");

        BCryptPasswordEncoder passwordEncoder2 = new BCryptPasswordEncoder();
        String password2 = passwordEncoder.encode("4567");

        appUserRepository.save(new AppUser("Franco", "franco", "franco@gmail.com", password1, AppUserRol.ROLE_ADMIN));
    }
}
