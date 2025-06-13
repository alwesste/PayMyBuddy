package com.openclassrooms.paymybuddyback.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.paymybuddyback.modelsDTO.ConnexionDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc // Configure un environnement de test pour faire des appels HTTP
@ActiveProfiles(profiles = "test")

public class ConnexionControllerTest {


    // Permet de simuler des requetes HTTP
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void connexionTest() throws Exception {

        ConnexionDTO connexionDTO = new ConnexionDTO( "geremi@gmail.com", "mathilde@gmail.com");

        mockMvc.perform(post("/api/addConnexion")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(connexionDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldReturnBadRequestWhenAddingSelfAsConnexion() throws Exception {
        ConnexionDTO connexionDTO = new ConnexionDTO("shiori@gmail.com", "shiori@gmail.com");

        mockMvc.perform(post("/api/addConnexion")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(connexionDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnNotFoundWhenTargetUserDoesNotExist() throws Exception {
        ConnexionDTO connexionDTO = new ConnexionDTO("shiori@gmail.com", "nobody@fake.com");

        mockMvc.perform(post("/api/addConnexion")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(connexionDTO)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnConnexionAlreadyExist() throws Exception {
        ConnexionDTO connexionDTO = new ConnexionDTO("geremi@gmail.com", "paul@gmail.com");

        mockMvc.perform(post("/api/addConnexion")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(connexionDTO)))
                .andExpect(status().isBadRequest());
    }
 }
