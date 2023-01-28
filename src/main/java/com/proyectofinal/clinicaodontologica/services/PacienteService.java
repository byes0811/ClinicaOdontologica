package com.proyectofinal.clinicaodontologica.services;

import com.proyectofinal.clinicaodontologica.exceptions.ResourceNotFoundException;
import com.proyectofinal.clinicaodontologica.models.Paciente;
import com.proyectofinal.clinicaodontologica.repository.PacienteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente guardarPaciente(Paciente paciente) {
        Paciente paciente1 = pacienteRepository.save(paciente);
        log.info("Paciente " + paciente1.toString() + " guardado con exito.");
        return paciente1;
    }

    public Paciente actualizarPaciente(Paciente paciente) {
        Optional<Paciente> paciente1 = pacienteRepository.findById(paciente.getId());

        if (paciente1.isPresent()) {
            return pacienteRepository.save(paciente);
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

    public void eliminar(Integer id) throws ResourceNotFoundException {

        if (buscarPorId(id) == null)
            throw new ResourceNotFoundException("No existe paciente con id: " + id);

        pacienteRepository.deleteById(id);
    }
}
