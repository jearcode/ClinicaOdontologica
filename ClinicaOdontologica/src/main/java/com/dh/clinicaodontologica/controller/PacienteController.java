package com.dh.clinicaodontologica.controller;

import com.dh.clinicaodontologica.entity.Paciente;
import com.dh.clinicaodontologica.exception.ResourceNotFoundException;
import com.dh.clinicaodontologica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<?> guardarPaciente(@RequestBody Paciente paciente) {
        ResponseEntity<?> responseEntity;
        if (pacienteService.existeEmail(paciente.getEmail())) {
            responseEntity = ResponseEntity.status(HttpStatus.CONFLICT).body("El email ya está registrado.");
        } else {
            Paciente pacienteGuardado = pacienteService.guardarPaciente(paciente);
            responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(pacienteGuardado);
        }

        return responseEntity;
    }

    @GetMapping("/buscar-id/{id}")
    public ResponseEntity<Optional<Paciente>> buscarPaciente(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteService.buscarPorID(id));
    }

    @GetMapping("/buscar-email/{email}")
    public ResponseEntity<?> buscarPorEmail(@PathVariable String email) {
        ResponseEntity<?> responseEntity;
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorEmail(email);
        if (pacienteBuscado.isPresent()) {
            responseEntity = ResponseEntity.ok(pacienteService.buscarPorEmail(email));
        } else {
            responseEntity = ResponseEntity.notFound().build();
        }
        return responseEntity;
    }

    @PutMapping
    public ResponseEntity<String> actualizarPaciente(@RequestBody Paciente paciente) {
        ResponseEntity<String> responseEntity;
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorID(paciente.getId());

        if (pacienteBuscado.isPresent()) {
            if (pacienteService.existeEmail(paciente.getEmail()) &&
                    !pacienteBuscado.get().getEmail().equals(paciente.getEmail())) {
                responseEntity = ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("El email ya está registrado.");
            } else {
                pacienteService.actualizarPaciente(paciente);
                responseEntity = ResponseEntity.ok("Paciente actualizado con éxito");
            }
        } else {
            responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Paciente no encontrado.");
        }

        return responseEntity;
    }


/*    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException {
        ResponseEntity<String> responseEntity;
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorID(id);
        if (pacienteBuscado.isPresent()) {
            pacienteService.eliminarPaciente(id);
            responseEntity = ResponseEntity.ok("Paciente eliminado con éxito");
        } else {
            responseEntity = ResponseEntity.badRequest().build();
        }
        return responseEntity;
    }*/
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPorID(id);
        if(pacienteBuscado.isPresent()){
            pacienteService.eliminarPaciente(id);
            return ResponseEntity.ok("paciente eliminado con exito");
        }else{
            //aca lanzamos la exception
            throw new ResourceNotFoundException("No existe ese id : "+id);
        }
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> listarTodos() {
        return ResponseEntity.ok(pacienteService.listarTodos());
    }


}
