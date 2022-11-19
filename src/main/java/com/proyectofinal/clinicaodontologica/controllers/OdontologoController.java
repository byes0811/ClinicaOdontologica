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
    public Odontologo registrarOdontologo(@RequestBody Odontologo odontologo) throws Exception {
        return odontologoService.guardar(odontologo);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Odontologo obtenerPorPathVariable(@PathVariable("id") Integer id)throws Exception{
        return odontologoService.buscarPorId(id);
    }

    @GetMapping("/RP")
    @ResponseBody
    public Odontologo obtenerPorRequestParam(@RequestParam Integer id)throws Exception{
        return odontologoService.buscarPorId(id);
    }

    @DeleteMapping("/delete")
    public void deletePorRequestParam(@RequestParam Integer id)throws Exception{
        odontologoService.eliminar(id);
    }

    @GetMapping()
    @ResponseBody
    public List<Odontologo> buscarTodos()throws Exception{
        return odontologoService.buscarTodos();
    }
}
