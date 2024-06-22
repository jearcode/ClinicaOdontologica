package com.dh.clinicaodontologica;

import com.dh.clinicaodontologica.entity.Odontologo;
import com.dh.clinicaodontologica.service.OdontologoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class OdontologosIntegracionTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void guardarOdontologo() throws Exception{
        Odontologo odontologo = new Odontologo("DH1", "John", "Doe");

        ObjectMapper objectMapper = new ObjectMapper();
        String odontologoJson = objectMapper.writeValueAsString(odontologo);

        MvcResult respuesta = mockMvc.perform(MockMvcRequestBuilders.post("/odontologos")
                        .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(odontologoJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();
        Assertions.assertEquals("application/json", respuesta.getResponse().getContentType());
    }
}
