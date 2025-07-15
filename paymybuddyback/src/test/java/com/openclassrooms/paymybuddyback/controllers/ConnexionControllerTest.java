package com.openclassrooms.paymybuddyback.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.paymybuddyback.modelsDTO.ConnexionDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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

        ConnexionDTO connexionDTO = new ConnexionDTO("geremi@gmail.com", "jean@gmail.com");

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

    @Test
    void getConnexionTestShouldReturnTheListOfRelation() throws Exception {
        String currentUserEmail = "geremi@gmail.com";

        mockMvc.perform(get("/api/seeConnexion")
                        .param("currentUserEmail", currentUserEmail)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].email", is("paul@gmail.com")))
                .andExpect(jsonPath("$[0].username", is("paul")))
                .andExpect(jsonPath("$[1].email", is("mathilde@gmail.com")))
                .andExpect(jsonPath("$[1].username", is("mathilde")));
    }

    @Test
    void shouldThrowRuntimeExceptionWhenEmailIsEmpty() throws Exception {
        mockMvc.perform(get("/api/seeConnexion")
                        .param("currentUserEmail", "")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
