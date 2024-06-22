package com.dh.clinicaodontologica.service;

import com.dh.clinicaodontologica.entity.Odontologo;
import com.dh.clinicaodontologica.entity.Paciente;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OdontologoServiceTest {
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private PacienteService pacienteService;

    @Test
    @Order(1)
    public void guardarOdontologo(){
        Odontologo odontologo = new Odontologo("DH1", "Valeria", "Muñoz");
        Odontologo odontologoGuardado = odontologoService.guardarOdontologo(odontologo);
        Long id = 1L;
        Assertions.assertEquals(id, odontologoGuardado.getId());
    }

    @Test
    @Order(2)
    public void buscarOdontologo(){
        Long id = 1L;
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorID(id);
        Assertions.assertNotNull(odontologoBuscado.get());
    }

    @Test
    @Order(3)
    public void actualizarOdontologo(){
        Long id = 1L;
        Odontologo odontologo = new Odontologo(id, "DH2", "Jeremias", "Ibarra");
        odontologoService.actualizarOdontologo(odontologo);
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorID(id);
        Assertions.assertEquals("Jeremias", odontologoBuscado.get().getNombre());
    }

    @Test
    @Order(4)
    public void listarTodos(){
        List<Odontologo> listaOdontologos = odontologoService.listarTodos();
        Assertions.assertEquals(1, listaOdontologos.size());
    }

    @Test
    @Order(5)
    public void eliminarOdontologo(){
        odontologoService.eliminarOdontologo(1L);
        Optional<Paciente> pacienteEliminado = pacienteService.buscarPorID(1L);
        Assertions.assertFalse(pacienteEliminado.isPresent());
    }

}
