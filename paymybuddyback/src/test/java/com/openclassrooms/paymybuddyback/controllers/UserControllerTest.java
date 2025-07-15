package com.openclassrooms.paymybuddyback.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.paymybuddyback.models.User;
import com.openclassrooms.paymybuddyback.modelsDTO.UserRegisterDTO;
import com.openclassrooms.paymybuddyback.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc // Configure un environnement ed test pour faire des appels HTTP
@ActiveProfiles(profiles = "test")
public class UserControllerTest {

    // Permet de simuler des requetes HTTP
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void registerUserTest() throws Exception {

        UserRegisterDTO newUser = new UserRegisterDTO("Antoine", "Antoine@gmail.com", "passwordTest");

        mockMvc.perform(post("/api/register")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(newUser)))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldReturnConflictWhenEmailAlreadyExists() throws Exception {
        UserRegisterDTO existingUser = new UserRegisterDTO("Antoine", "antoine@gmail.com", "password");

        mockMvc.perform(post("/api/register")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(existingUser)))
                .andExpect(status().isCreated());

        mockMvc.perform(post("/api/register")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(existingUser)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnNewPassword() throws Exception {
        UserRegisterDTO userWithNewPassword = new UserRegisterDTO("geremi", "geremi@gmail.com", "newPasswordForTheTest");

        mockMvc.perform(post("/api/updatePassword")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(userWithNewPassword)))
                .andExpect(status().isAccepted());

        User updatetedUser = userRepository.findByEmail(userWithNewPassword.getEmail()).orElseThrow();
        assertEquals(userWithNewPassword.getPassword(), updatetedUser.getPassword());
    }

    @Test
    void shouldReturnErrorNotFoundException() throws Exception {
        UserRegisterDTO noUserFound = new UserRegisterDTO("noName", "noEmail", "noPassword");

        mockMvc.perform(post("/api/updatePassword")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(noUserFound)))
                .andExpect(status().isNotFound());
    }
}
