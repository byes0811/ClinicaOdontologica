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

    public List<Odontologo> buscarTodos() throws Exception{
        return odontologoDao.buscarTodos();
    }

    public Odontologo buscarPorId(Integer id)throws Exception{
        return odontologoDao.buscar(id);
    }

    public void eliminar(Integer id)throws Exception{
        odontologoDao.eliminar(id);
    }

    public Odontologo actualizarOdontologo(Odontologo o)throws Exception{
        return odontologoDao.actualizar(o);
    }

    public Odontologo guardar(Odontologo odontologo) throws Exception {
        return odontologoDao.guardar(odontologo);
    }


}
