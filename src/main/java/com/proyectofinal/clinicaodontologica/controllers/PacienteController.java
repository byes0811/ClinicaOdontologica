package com.proyectofinal.clinicaodontologica.controllers;

import com.proyectofinal.clinicaodontologica.exceptions.ResourceNotFoundException;
import com.proyectofinal.clinicaodontologica.models.Odontologo;
import com.proyectofinal.clinicaodontologica.models.Paciente;
import com.proyectofinal.clinicaodontologica.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping()
    @ResponseBody
    public Paciente registrarPaciente(@RequestBody Paciente paciente) {
        return pacienteService.guardarPaciente(paciente);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Paciente obtenerPorPathVariable(@PathVariable("id") Integer id){
        return pacienteService.buscarPorId(id);
    }

    @PutMapping("/actualizar")
    @ResponseBody
    public Paciente actualizarPaciente(@RequestBody Paciente paciente) {

        Paciente response = null;

        if(paciente.getId() != null && pacienteService.buscarPorId(paciente.getId()) != null){
            response = pacienteService.actualizarPaciente(paciente);
        }

        return response;
    }

    @DeleteMapping("/eliminar")
    public void deletePorRequestParam(@RequestParam Integer id) throws ResourceNotFoundException {
        pacienteService.eliminar(id);
    }

    @GetMapping()
    @ResponseBody
    public List<Paciente> buscarTodos(){
        return pacienteService.buscarTodos();
    }

}