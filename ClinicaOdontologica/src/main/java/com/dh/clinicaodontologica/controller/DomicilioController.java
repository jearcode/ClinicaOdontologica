package com.dh.clinicaodontologica.controller;

import com.dh.clinicaodontologica.entity.Domicilio;
import com.dh.clinicaodontologica.service.DomicilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/domicilios")
public class DomicilioController {

    @Autowired
    private DomicilioService domicilioService;

    @PostMapping
    public ResponseEntity<Domicilio> guardarDomicilio(@RequestBody Domicilio domicilio){
        return new ResponseEntity<>(domicilioService.guardarDomicilio(domicilio), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Domicilio>> buscarPorID(@PathVariable Long id){
        return ResponseEntity.ok(domicilioService.buscarPorID(id));
    }

    @PutMapping
    public ResponseEntity<String> actualizarDomicilio(@RequestBody Domicilio domicilio){
        Optional<Domicilio> domicilioBuscado = domicilioService.buscarPorID(domicilio.getId());
        ResponseEntity<String> responseEntity;
        if(domicilioBuscado.isPresent()){
            domicilioService.actualizarDomicilio(domicilio);
            responseEntity = new ResponseEntity<>("Domicilio actualizado con éxito", HttpStatus.OK);
        }else{
            responseEntity = new ResponseEntity<>("No se encontró el domicilio", HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarDomicilio(@PathVariable Long id){
        Optional<Domicilio> domicilioBuscado = domicilioService.buscarPorID(id);
        ResponseEntity<String> responseEntity;
        if(domicilioBuscado.isPresent()){
            domicilioService.eliminarDomicilio(id);
            responseEntity = new ResponseEntity<>("Domicilio eliminado con éxito", HttpStatus.OK);
        }else{
            responseEntity = new ResponseEntity<>("No se encontró el domicilio", HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @GetMapping
    public ResponseEntity<List<Domicilio>> listarTodos(){
        return ResponseEntity.ok(domicilioService.listarTodos());
    }


}
