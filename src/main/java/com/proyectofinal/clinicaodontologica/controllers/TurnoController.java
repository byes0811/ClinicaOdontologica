package com.proyectofinal.clinicaodontologica.controllers;

import com.proyectofinal.clinicaodontologica.dao.impl.TurnoDaoH2;
import com.proyectofinal.clinicaodontologica.models.Turno;
import com.proyectofinal.clinicaodontologica.services.TurnoService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    private TurnoService turnoService = new TurnoService(new TurnoDaoH2());

    @PostMapping()
    @ResponseBody
    public Turno registrarTurno(@RequestBody Turno turno) throws SQLException {
        return turnoService.guardarTurno(turno);
    }

    @PutMapping()
    @ResponseBody
    public Turno actualizarTurno(@RequestBody Turno turno){

        Turno response = null;

        if(turno.getIdTurno() != null && turnoService.buscar(turno.getIdTurno()) != null){
            response = turnoService.actualizarTurno(turno);
        }
        return response;
    }

    @DeleteMapping()
    @ResponseBody
    public void eliminarTurno(@RequestParam Integer id){
        turnoService.eliminar(id);
    }

    @GetMapping()
    @ResponseBody
    public List<Turno> buscarTodos(){
        return turnoService.buscarTodos();
    }
}
