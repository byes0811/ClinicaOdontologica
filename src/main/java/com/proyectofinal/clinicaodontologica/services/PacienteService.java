package com.proyectofinal.clinicaodontologica.services;

import com.proyectofinal.clinicaodontologica.models.Paciente;
import com.proyectofinal.clinicaodontologica.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente guardarPaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    public Paciente actualizarPaciente(Paciente paciente) {
        Optional<Paciente> paciente1 = pacienteRepository.findById(paciente.getId());

        if (paciente1.isPresent()) {
            return pacienteRepository.save(paciente1.get());
        } else {
            return null;
        }
    }

    public Paciente buscarPorId(Integer id) {
        Optional<Paciente> paciente = pacienteRepository.findById(id);

        if (paciente.isPresent()) {
            return paciente.get();
        } else {
            return null;
        }
    }

    public List<Paciente> buscarTodos() {
        return pacienteRepository.findAll();
    }

    public void eliminar(Integer id) {
        pacienteRepository.deleteById(id);
    }
}
