package com.proyectofinal.clinicaodontologica.services;

import com.proyectofinal.clinicaodontologica.dao.IDao;
import com.proyectofinal.clinicaodontologica.dao.Impl.OdontologoDaoH2;
import com.proyectofinal.clinicaodontologica.dao.Impl.PacienteDaoH2;
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

    public Turno guardarTurno(Turno turno) {

        Paciente paciente = pacienteIDao.buscar(turno.getId_paciente());
        Odontologo odontologo = odontologoIDao.buscar(turno.getId_odontologo());

        if (paciente == null || odontologo == null) {
            turno = null;
            return turno;
        }
        return turnoIDao.guardar(turno);
    }

    public Turno actualizarTurno(Turno turno) {
        return turnoIDao.actualizar(turno);
    }

    public Turno buscar(Integer id) {
        return turnoIDao.buscar(id);
    }

    public List<Turno> buscarTodos(){
        return turnoIDao.buscarTodos();
    }

    public void eliminar(Integer id){
        turnoIDao.eliminar(id);
    }
}
