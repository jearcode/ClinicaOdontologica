package com.dh.clinicaodontologica.service;

import com.dh.clinicaodontologica.entity.Domicilio;
import com.dh.clinicaodontologica.entity.Paciente;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PacienteServiceTest {
    @Autowired
    PacienteService pacienteService;

    @Test
    @Order(1)
    public void guardarPaciente(){
        Paciente paciente = new Paciente("John", "Doe", "1111", LocalDate.now(),
                new Domicilio("Calle falsa", 123, "La Rioja", "Argentina"), "john@doe.com");
        Paciente pacienteGuardado = pacienteService.guardarPaciente(paciente);
        Assertions.assertEquals(1L, pacienteGuardado.getId());
    }

    @Test
    @Order(2)
    public void buscarPacientePorID(){
        Long id = 1L;
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorID(id);
        Assertions.assertNotNull(pacienteBuscado.get());
    }

    @Test
    @Order(3)
    public void actualizarPaciente(){
        Long id = 1L;
        Paciente paciente = new Paciente(id,"Emily", "Davis", "1111", LocalDate.now(),
                new Domicilio("Calle falsa", 123, "La Rioja", "Argentina"), "emily@davis.com");
        pacienteService.actualizarPaciente(paciente);
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorID(id);
        Assertions.assertEquals("Emily", pacienteBuscado.get().getNombre());
    }

    @Test
    @Order(4)
    public void listarTodos(){
        List<Paciente> listaPacientes = pacienteService.listarTodos();
        Assertions.assertEquals(1, listaPacientes.size());
    }

    @Test
    @Order(5)
    public void eliminarPaciente(){
        pacienteService.eliminarPaciente(1L);
        Optional<Paciente> pacienteEliminado = pacienteService.buscarPorID(1L);
        Assertions.assertFalse(pacienteEliminado.isPresent());
    }

}
