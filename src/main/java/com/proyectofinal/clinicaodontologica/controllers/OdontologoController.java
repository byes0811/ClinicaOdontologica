package com.proyectofinal.clinicaodontologica.controllers;

import com.proyectofinal.clinicaodontologica.dao.impl.OdontologoDaoH2;
import com.proyectofinal.clinicaodontologica.models.Odontologo;
import com.proyectofinal.clinicaodontologica.services.OdontologoService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    private OdontologoService odontologoService = new OdontologoService(new OdontologoDaoH2());

    @PostMapping()
    @ResponseBody
    public Odontologo registrarOdontologo(@RequestBody Odontologo odontologo) throws SQLException {
        return odontologoService.guardar(odontologo);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Odontologo obtenerPorPathVariable(@PathVariable("id") Integer id){
        return odontologoService.buscarPorId(id);
    }

    @GetMapping("/RP")
    @ResponseBody
    public Odontologo obtenerPorRequestParam(@RequestParam Integer id){
        return odontologoService.buscarPorId(id);
    }


    // Delete
    @DeleteMapping("/delete")
    public void deletePorRequestParam(@RequestParam Integer id){
        odontologoService.eliminar(id);
    }

    // All
    @GetMapping()
    @ResponseBody
    public List<Odontologo> buscarTodos(){
        return odontologoService.buscarTodos();
    }
}
