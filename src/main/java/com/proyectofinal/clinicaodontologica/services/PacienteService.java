package com.proyectofinal.clinicaodontologica.services;

import com.example.Integrador.dao.IDao;
import com.example.Integrador.models.Paciente;
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


/*
    private List<Paciente> pacientes = new ArrayList<>();

    public PacienteService(){
        pacientes.add(new Paciente(1L,"Leo","Messi","leomessi@mail.com",
                12345678, LocalDate.of(2022,6,10)));
        pacientes.add(new Paciente(2L,"Cristiano","Ronaldo","Cr7@mail.com",
                12345689, LocalDate.of(2022,6,9)));
    }

    public List<Paciente> getAll(){
        return pacientes;
    }

    public Paciente getPacientByEmail(String email){
        for(int i = 0; i<pacientes.size();i++){
            if(pacientes.get(i).getEmail().equals(email)){
                return pacientes.get(i);
            }
        }
        return null;
    }

    public void savePaciente(Paciente paciente){
        pacientes.add(paciente);
    }*/
}
