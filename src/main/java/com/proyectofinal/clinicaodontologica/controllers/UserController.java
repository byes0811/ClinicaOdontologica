package com.proyectofinal.clinicaodontologica.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/")
    public void home() {

    }

    @GetMapping("/user")
    public String user() {
        return "index";
    }

    @GetMapping("/admin")
    public String admin() {
        return "index";
    }
}
