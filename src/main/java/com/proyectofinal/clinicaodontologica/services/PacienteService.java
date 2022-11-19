package com.proyectofinal.clinicaodontologica.services;

import com.proyectofinal.clinicaodontologica.dao.IDao;
import com.proyectofinal.clinicaodontologica.models.Paciente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {

    private IDao<Paciente> pacienteIDao;

    public PacienteService(IDao<Paciente> pacienteIDao){
        this.pacienteIDao = pacienteIDao;
    }

    public PacienteService() {
    }

    public Paciente guardarPaciente(Paciente p){
        return pacienteIDao.guardar(p);
    }

    public Paciente actualizarPaciente(Paciente p){
        return pacienteIDao.actualizar(p);
    }

    public Paciente buscar(Integer id){
        return pacienteIDao.buscar(id);
    }

    public List<Paciente> buscarTodos(){
        return pacienteIDao.buscarTodos();
    }

    public void eliminar(Integer id){
        pacienteIDao.eliminar(id);
    }
}
