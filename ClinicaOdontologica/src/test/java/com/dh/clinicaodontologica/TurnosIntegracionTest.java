package com.dh.clinicaodontologica;

import com.dh.clinicaodontologica.entity.Domicilio;
import com.dh.clinicaodontologica.entity.Odontologo;
import com.dh.clinicaodontologica.entity.Paciente;
import com.dh.clinicaodontologica.entity.Turno;
import com.dh.clinicaodontologica.service.OdontologoService;
import com.dh.clinicaodontologica.service.PacienteService;
import com.dh.clinicaodontologica.service.TurnoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false) //Desactivamos la seguridad
public class TurnosIntegracionTest {
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private MockMvc mockMvc;

    private void cargarDatos(){
        Paciente paciente = pacienteService.guardarPaciente(
                new Paciente("John", "Doe", "1111", LocalDate.now(),
                new Domicilio("Calle falsa", 123, "La Rioja", "Argentina"),
                        "john@doe.com"));
        Odontologo odontologo = odontologoService.guardarOdontologo(new Odontologo("DH1", "John", "Doe"));
        Turno turno = turnoService.guardarTurno(new Turno(paciente, odontologo, LocalDateTime.now()));
    }
    @Test
    public void ListarTodosLosTurnosTest() throws Exception{
        cargarDatos();
        MvcResult respuesta = mockMvc.perform(MockMvcRequestBuilders.get("/turnos")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        Assertions.assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }

}
