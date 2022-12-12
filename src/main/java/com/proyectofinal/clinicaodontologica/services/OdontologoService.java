package com.proyectofinal.clinicaodontologica.services;

import com.proyectofinal.clinicaodontologica.exceptions.ResourceNotFoundException;
import com.proyectofinal.clinicaodontologica.repository.OdontologoRepository;
import com.proyectofinal.clinicaodontologica.models.Odontologo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {

    @Autowired
    private OdontologoRepository odontologoRepository;

    public List<Odontologo> buscarTodos() {
        return odontologoRepository.findAll();
    }

    public Odontologo buscarPorId(Integer id) {

        Optional<Odontologo> odontologo = odontologoRepository.findById(id);

        if (odontologo.isPresent()) {
            return odontologo.get();
        } else {
            return null;
        }
    }

    public void eliminar(Integer id) {
        odontologoRepository.deleteById(id);
    }

    public Odontologo actualizarOdontologo(Odontologo odontologo) throws ResourceNotFoundException {

        Optional<Odontologo> odontologo1 = odontologoRepository.findById(odontologo.getId());

        if (odontologo1.isPresent()) {
            return odontologoRepository.save(odontologo);
        } else {
            throw new ResourceNotFoundException("No existe odontologo con id: " + odontologo.getId());
        }
    }

    public Odontologo guardar(Odontologo odontologo) {
        return odontologoRepository.save(odontologo);
    }
}
