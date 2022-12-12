package com.proyectofinal.clinicaodontologica.controllers;

import com.proyectofinal.clinicaodontologica.exceptions.BadRequestException;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity registrarTurno(@RequestParam Integer pacienteId, @RequestParam Integer odontologoId, @RequestBody Turno turno) throws BadRequestException {

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(turnoService.guardarTurno(turno, pacienteId, odontologoId));
        } catch (BadRequestException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PutMapping("/actualizar")
    @ResponseBody
    public Turno actualizarTurno(@RequestParam Integer pacienteId, @RequestParam Integer odontologoId, @RequestBody Turno turno) throws BadRequestException{

        Turno response = null;

        if (turno.getId() != null && turnoService.buscarPorId(turno.getId()) != null) {
            response = turnoService.actualizarTurno(turno);
        }
        return response;
    }

    @DeleteMapping("/eliminar")
    @ResponseBody
    public void eliminarTurno(@RequestParam Integer id) {
        turnoService.eliminar(id);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Turno obtenerPorPathVariable(@PathVariable("id") Integer id){
        return turnoService.buscarPorId(id);
    }

    @GetMapping()
    @ResponseBody
    public List<Turno> buscarTodos() {
        return turnoService.buscarTodos();
    }
}
