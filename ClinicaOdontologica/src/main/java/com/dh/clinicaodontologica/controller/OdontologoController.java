package com.dh.clinicaodontologica.controller;

import com.dh.clinicaodontologica.entity.Odontologo;
import com.dh.clinicaodontologica.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    @Autowired
    private OdontologoService odontologoService;

    @PostMapping
    public ResponseEntity<Odontologo> guardarOdontologo(@RequestBody Odontologo odontologo){
        return new ResponseEntity<>(odontologoService.guardarOdontologo(odontologo), HttpStatus.CREATED);
    }

    @GetMapping("/buscar-id/{id}")
    public ResponseEntity<Optional<Odontologo>> buscarPorID(@PathVariable Long id){
        return ResponseEntity.ok(odontologoService.buscarPorID(id));
    }

    @GetMapping("/buscar-matricula/{matricula}")
    public ResponseEntity<Optional<Odontologo>> buscarPorMatricula(@PathVariable String matricula){
        return ResponseEntity.ok(odontologoService.buscarPorMatricula(matricula));
    }

    @PutMapping
    public ResponseEntity<String> actualizarOdontologo(@RequestBody Odontologo odontologo){
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorID(odontologo.getId());
        ResponseEntity<String> responseEntity;
        if(odontologoBuscado.isPresent()){
            odontologoService.actualizarOdontologo(odontologo);
            responseEntity = new ResponseEntity<>("Odontólogo actualizado exitosamente", HttpStatus.OK);
        }
        else{
            responseEntity = new ResponseEntity<>("No se encontró el odontólogo", HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id){
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorID(id);
        ResponseEntity<String> responseEntity;
        if(odontologoBuscado.isPresent()){
            odontologoService.eliminarOdontologo(id);
            responseEntity = new ResponseEntity<>("Odontólogo eliminado exitosamente", HttpStatus.OK);
        }
        else{
            responseEntity = new ResponseEntity<>("No se encontró el odontólogo", HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @GetMapping
    public ResponseEntity<List<Odontologo>> listarTodos(){
        return ResponseEntity.ok(odontologoService.listarTodos());
    }
}
