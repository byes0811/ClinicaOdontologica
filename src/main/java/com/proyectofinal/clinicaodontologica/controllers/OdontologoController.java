package com.proyectofinal.clinicaodontologica.controllers;

import com.proyectofinal.clinicaodontologica.models.Odontologo;
import com.proyectofinal.clinicaodontologica.services.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    @Autowired
    private OdontologoService odontologoService;

    @PostMapping()
    @ResponseBody
    public Odontologo registrarOdontologo(@RequestBody Odontologo odontologo) {
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

    @DeleteMapping("/delete")
    public void deletePorRequestParam(@RequestParam Integer id){
        odontologoService.eliminar(id);
    }

    @GetMapping()
    @ResponseBody
    public List<Odontologo> buscarTodos(){
        return odontologoService.buscarTodos();
    }
}
