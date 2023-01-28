package com.proyectofinal.clinicaodontologica.services;

import com.proyectofinal.clinicaodontologica.exceptions.BadRequestException;
import com.proyectofinal.clinicaodontologica.models.Odontologo;
import com.proyectofinal.clinicaodontologica.models.Paciente;
import com.proyectofinal.clinicaodontologica.models.Turno;
import com.proyectofinal.clinicaodontologica.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {

    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private TurnoRepository turnoRepository;

    public Turno guardarTurno(Turno turno, Integer pacienteId, Integer odontologoId) throws BadRequestException {

        Paciente paciente = pacienteService.buscarPorId(pacienteId);
        Odontologo odontologo = odontologoService.buscarPorId(odontologoId);

        if (paciente == null) {
            throw new BadRequestException("No existe el paciente con id: " + pacienteId);
        } else if (odontologo == null) {
            throw new BadRequestException("No existe el odontologo con id: " + odontologoId);
        }

        turno.setPaciente(paciente);
        turno.setOdontologo(odontologo);
        return turnoRepository.save(turno);
    }

    public Turno actualizarTurno(Turno turno) {
        Optional<Turno> turno1 = turnoRepository.findById(turno.getId());

        if (turno1.isPresent()) {
            return turnoRepository.save(turno);
        } else {
            return null;
        }
    }

    public Turno buscarPorId(Integer id) {
        Optional<Turno> turno = turnoRepository.findById(id);

        if (turno.isPresent()) {
            return turno.get();
        } else {
            return null;
        }
    }

    public List<Turno> buscarTodos() {
        return turnoRepository.findAll();
    }

    public void eliminar(Integer id) {
        turnoRepository.deleteById(id);
    }
}
