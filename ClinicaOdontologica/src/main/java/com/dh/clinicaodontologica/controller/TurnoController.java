package com.dh.clinicaodontologica.controller;

import com.dh.clinicaodontologica.entity.Turno;
import com.dh.clinicaodontologica.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    @Autowired
    private TurnoService turnoService;

    @PostMapping
    public ResponseEntity<Turno> guardarTurno(@RequestBody Turno turno){
        return new ResponseEntity<>(turnoService.guardarTurno(turno), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Turno>> buscarPorID(@PathVariable Long id){
        return ResponseEntity.ok(turnoService.buscarPorID(id));
    }

    @PutMapping
    public ResponseEntity<String> actualizarTurno(@RequestBody Turno turno){
        ResponseEntity<String> responseEntity;
        Optional<Turno> turnoBuscado = turnoService.buscarPorID(turno.getId());
        if(turnoBuscado.isPresent()){
            turnoService.actualizarTurno(turno);
            responseEntity = new ResponseEntity<>("Turno actualizado exitosamente", HttpStatus.OK);
        }else{
            responseEntity = new ResponseEntity<>("Turno no encontrado", HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id){
        ResponseEntity<String> responseEntity;
        Optional<Turno> turnoBuscado = turnoService.buscarPorID(id);
        if(turnoBuscado.isPresent()){
            turnoService.eliminarTurno(id);
            responseEntity = new ResponseEntity<>("Turno eliminado exitosamente", HttpStatus.OK);
        }else{
            responseEntity = new ResponseEntity<>("Turno no encontrado", HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @GetMapping
    public ResponseEntity<List<Turno>> listarTodos(){
        return ResponseEntity.ok(turnoService.listarTodos());
    }

}
