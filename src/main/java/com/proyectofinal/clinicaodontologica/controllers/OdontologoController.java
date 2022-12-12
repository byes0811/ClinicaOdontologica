package com.proyectofinal.clinicaodontologica.controllers;

import com.proyectofinal.clinicaodontologica.exceptions.ResourceNotFoundException;
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

    @DeleteMapping("/eliminar")
    public void eliminarOdontologoRP(@RequestParam Integer id){
        odontologoService.eliminar(id);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarOdontologoPath(@PathVariable("id") Integer id){
        odontologoService.eliminar(id);
    }

    @GetMapping()
    @ResponseBody
    public List<Odontologo> buscarTodos(){
        return odontologoService.buscarTodos();
    }

    @PutMapping("/actualizar")
    @ResponseBody
    public Odontologo actualizarOdontologo(@RequestBody Odontologo odontologo) throws ResourceNotFoundException {

        return odontologoService.actualizarOdontologo(odontologo);
    }
}
