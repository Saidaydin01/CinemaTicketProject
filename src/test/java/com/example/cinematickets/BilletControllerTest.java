package com.example.cinematickets;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BilletControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testInvalidFilm() throws Exception {
        Billet invalidBillet = new Billet(0, "", 2, "Ola", "Nordmann", "12345678", "ola@nordmann.no");

        mockMvc.perform(post("/lagre")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(invalidBillet)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Film navn må oppgis."));
    }

    @Test
    public void testInvalidAntall() throws Exception {
        Billet invalidBillet = new Billet(0, "Silent Hill", 0, "Ola", "Nordmann", "12345678", "ola@nordmann.no");

        mockMvc.perform(post("/lagre")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(invalidBillet)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Antall må være større enn null."));
    }

    @Test
    public void testInvalidFornavn() throws Exception {
        Billet invalidBillet = new Billet(0, "Silent Hill", 1, "", "Nordmann", "12345678", "ola@nordmann.no");

        mockMvc.perform(post("/lagre")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(invalidBillet)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Fornavn må oppgis."));
    }

    @Test
    public void testInvalidEtternavn() throws Exception {
        Billet invalidBillet = new Billet(0, "Silent Hill", 1, "Ola", "", "12345678", "ola@nordmann.no");

        mockMvc.perform(post("/lagre")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(invalidBillet)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Etternavn må oppgis."));
    }

    @Test
    public void testInvalidTelefon() throws Exception {
        Billet invalidBillet = new Billet(0, "Silent Hill", 1, "Ola", "Nordmann", "+1234567", "ola@nordmann.no");

        mockMvc.perform(post("/lagre")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(invalidBillet)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Ugyldig telefonnummer."));
    }

    @Test
    public void testInvalidEpost() throws Exception {
        Billet invalidBillet = new Billet(0, "Silent Hill", 1, "Ola", "Nordmann", "12345678", "ola");

        mockMvc.perform(post("/lagre")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(invalidBillet)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Ugyldig epostadresse."));
    }


    @Test
    public void testValidBillet() throws Exception {
        Billet validBillet = new Billet(0, "Silent Hill", 2, "Ola", "Nordmann", "12345678", "ola@nordmann.no");
        mockMvc.perform(post("/lagre")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(validBillet)))
                .andExpect(status().isOk())
                .andExpect(content().string("Billet lagret."));
    }
}
