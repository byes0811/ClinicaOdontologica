package com.proyectofinal.clinicaodontologica.services;

import com.proyectofinal.clinicaodontologica.dao.IDao;
import com.proyectofinal.clinicaodontologica.dao.impl.OdontologoDaoH2;
import com.proyectofinal.clinicaodontologica.dao.impl.PacienteDaoH2;
import com.proyectofinal.clinicaodontologica.models.Odontologo;
import com.proyectofinal.clinicaodontologica.models.Paciente;
import com.proyectofinal.clinicaodontologica.models.Turno;

import java.util.List;

public class TurnoService {
    private IDao<Paciente> pacienteIDao;
    private IDao<Odontologo> odontologoIDao;

    private IDao<Turno> turnoIDao;

    public TurnoService(IDao<Turno> turnoIDao) {
        this.turnoIDao = turnoIDao;
        this.pacienteIDao = new PacienteDaoH2();
        this.odontologoIDao = new OdontologoDaoH2();
    }

    public Turno guardarTurno(Turno turno) throws Exception {

        Paciente paciente = pacienteIDao.buscar(turno.getIdPaciente());
        Odontologo odontologo = odontologoIDao.buscar(turno.getIdOdontologo());

        if (paciente == null || odontologo == null) {
            turno = null;
            return turno;
        }
        return turnoIDao.guardar(turno);
    }

    public Turno actualizarTurno(Turno turno) throws Exception {
        return turnoIDao.actualizar(turno);
    }

    public Turno buscar(Integer id) throws Exception {
        return turnoIDao.buscar(id);
    }

    public List<Turno> buscarTodos() throws Exception {
        return turnoIDao.buscarTodos();
    }

    public void eliminar(Integer id) throws Exception {
        turnoIDao.eliminar(id);
    }
}
