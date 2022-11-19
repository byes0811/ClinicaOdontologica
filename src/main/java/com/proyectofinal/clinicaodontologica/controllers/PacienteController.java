package com.example.Integrador.controllers;

import com.example.Integrador.dao.Impl.PacienteDaoH2;
import com.example.Integrador.models.Paciente;
import com.example.Integrador.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {


    private PacienteService pacienteService = new PacienteService(new PacienteDaoH2());

    @PostMapping()
    @ResponseBody
    public Paciente registrarPaciente(@RequestBody Paciente paciente){
        return pacienteService.guardarPaciente(paciente);
    }

    @PutMapping()
    @ResponseBody
    public Paciente actualizarPaciente(@RequestBody Paciente paciente){

        Paciente respose = null;

        if(paciente.getId() != null && pacienteService.buscar(paciente.getId()) != null){
            respose = pacienteService.actualizarPaciente(paciente);
        }

        return respose;
    }

    @GetMapping()
    @ResponseBody
    public List<Paciente> buscarTodos(){
        return pacienteService.buscarTodos();
    }

}