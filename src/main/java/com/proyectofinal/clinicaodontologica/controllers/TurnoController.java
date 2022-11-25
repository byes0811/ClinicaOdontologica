package com.proyectofinal.clinicaodontologica.controllers;

import com.proyectofinal.clinicaodontologica.models.Odontologo;
import com.proyectofinal.clinicaodontologica.models.Paciente;
import com.proyectofinal.clinicaodontologica.models.Turno;
import com.proyectofinal.clinicaodontologica.repository.OdontologoRepository;
import com.proyectofinal.clinicaodontologica.repository.PacienteRepository;
import com.proyectofinal.clinicaodontologica.services.OdontologoService;
import com.proyectofinal.clinicaodontologica.services.PacienteService;
import com.proyectofinal.clinicaodontologica.services.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    @Autowired
    private TurnoService turnoService;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private OdontologoService odontologoService;

    @PostMapping()
    @ResponseBody
    public Turno registrarTurno(@RequestParam Integer pacienteId, @RequestParam Integer odontologoId, @RequestBody Turno turno) {

        Paciente paciente = pacienteService.buscarPorId(pacienteId);
        Odontologo odontologo = odontologoService.buscarPorId(odontologoId);

        if (paciente != null && odontologo != null) {
            turno.setPaciente(paciente);
            turno.setOdontologo(odontologo);
            return turnoService.guardarTurno(turno);
        }
        return null;
    }

    @PutMapping()
    @ResponseBody
    public Turno actualizarTurno(@RequestBody Turno turno) {

        Turno response = null;

        if (turno.getId() != null && turnoService.buscarPorId(turno.getId()) != null) {
            response = turnoService.actualizarTurno(turno);
        }
        return response;
    }

    @DeleteMapping()
    @ResponseBody
    public void eliminarTurno(@RequestParam Integer id) {
        turnoService.eliminar(id);
    }

    @GetMapping()
    @ResponseBody
    public List<Turno> buscarTodos() {
        return turnoService.buscarTodos();
    }
}
