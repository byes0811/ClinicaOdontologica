package com.proyectofinal.clinicaodontologica.controllers;

import com.proyectofinal.clinicaodontologica.dao.impl.PacienteDaoH2;
import com.proyectofinal.clinicaodontologica.models.Paciente;
import com.proyectofinal.clinicaodontologica.services.PacienteService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {


    private PacienteService pacienteService = new PacienteService(new PacienteDaoH2());

    @PostMapping()
    @ResponseBody
    public Paciente registrarPaciente(@RequestBody Paciente paciente) throws SQLException {
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