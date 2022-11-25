package com.proyectofinal.clinicaodontologica.services;

import com.proyectofinal.clinicaodontologica.models.Odontologo;
import com.proyectofinal.clinicaodontologica.models.Paciente;
import com.proyectofinal.clinicaodontologica.models.Turno;
import com.proyectofinal.clinicaodontologica.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
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

    public Turno guardarTurno(Turno turno) {
        return turnoRepository.save(turno);
    }

    public Turno actualizarTurno(Turno turno)  {
        return null;
    }

    public Turno buscarPorId(Integer id)  {
        Optional<Turno> turno = turnoRepository.findById(id);

        if(turno.isPresent()){
            return turno.get();
        }else{
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
