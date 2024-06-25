package com.dh.clinicaodontologica.service;

import com.dh.clinicaodontologica.entity.Domicilio;
import com.dh.clinicaodontologica.entity.Odontologo;
import com.dh.clinicaodontologica.entity.Paciente;
import com.dh.clinicaodontologica.entity.Turno;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TurnoServiceTest {
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private PacienteService pacienteService;

    @Test
    @Order(1)
    public void guardarTurno(){
        Odontologo odontologo = new Odontologo("DH1", "Valeria", "Muñoz");
        Paciente paciente = new Paciente("John", "Doe", "1111", LocalDate.now(),
                new Domicilio("Calle falsa", 123, "La Rioja", "Argentina"), "john@doe.com");
        odontologoService.guardarOdontologo(odontologo);
        pacienteService.guardarPaciente(paciente);
        Turno turno = new Turno(paciente, odontologo, LocalDate.now());
        Turno turnoGuardado = turnoService.guardarTurno(turno);
        Assertions.assertEquals(1, turnoGuardado.getId());
    }

    @Test
    @Order(2)
    public void buscarTurno(){
        Long id = 1L;
        Optional<Turno> turnoBuscado = turnoService.buscarPorID(id);
        Assertions.assertNotNull(turnoBuscado.get());
    }

    @Test
    @Order(3)
    public void actualizarTurno(){
        Long id = 1L;
        Odontologo odontologo = new Odontologo("DH1", "Valeria", "Muñoz");
        Paciente paciente = new Paciente("Emily", "Davis", "1111", LocalDate.now(),
                new Domicilio("Calle falsa", 123, "La Rioja", "Argentina"), "emily@davis.com");
        odontologoService.guardarOdontologo(odontologo);
        pacienteService.guardarPaciente(paciente);
        Turno turno = new Turno(id, paciente, odontologo, LocalDate.now());
        turnoService.actualizarTurno(turno);
        Optional<Turno> turnoBuscado = turnoService.buscarPorID(id);
        Assertions.assertEquals(paciente.getNombre(), turnoBuscado.get().getPaciente().getNombre());
    }

    @Test
    @Order(4)
    public void listarTodos(){
        List<Turno> listaTurnos = turnoService.listarTodos();
        Assertions.assertEquals(1, listaTurnos.size());
    }

    @Test
    @Order(5)
    public void eliminarTurno(){
        Long id = 1L;
        turnoService.eliminarTurno(id);
        Optional<Turno> turnoBuscado = turnoService.buscarPorID(id);
        Assertions.assertFalse(turnoBuscado.isPresent());
    }

}
