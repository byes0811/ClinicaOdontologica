package com.proyectofinal.clinicaodontologica.controllers;

import com.example.Integrador.dao.Impl.OdontologoDaoH2;
import com.example.Integrador.models.Odontologo;
import com.example.Integrador.services.OdontologoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    private OdontologoService odontologoService = new OdontologoService(new OdontologoDaoH2());

    @PostMapping()
    @ResponseBody
    public Odontologo registrarOdontologo(@RequestBody Odontologo odontologo){
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
