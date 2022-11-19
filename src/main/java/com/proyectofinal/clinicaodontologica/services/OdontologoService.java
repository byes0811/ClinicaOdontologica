package com.proyectofinal.clinicaodontologica.services;

import com.proyectofinal.clinicaodontologica.dao.IDao;
import com.proyectofinal.clinicaodontologica.models.Odontologo;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class OdontologoService {

    private IDao<Odontologo> odontologoDao;

    public OdontologoService(IDao<Odontologo> odontologoDao) {
        this.odontologoDao = odontologoDao;
    }

    public OdontologoService() {
    }

    public List<Odontologo> buscarTodos() {
        return odontologoDao.buscarTodos();
    }

    public Odontologo buscarPorId(Integer id){
        return odontologoDao.buscar(id);
    }

    public void eliminar(Integer id){
        odontologoDao.eliminar(id);
    }

    public Odontologo actualizarOdontologo(Odontologo o){
        return odontologoDao.actualizar(o);
    }

    public Odontologo guardar(Odontologo odontologo) throws SQLException {
        return odontologoDao.guardar(odontologo);
    }


}
