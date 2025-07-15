package com.openclassrooms.paymybuddyback.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.paymybuddyback.modelsDTO.LoginDetailDTO;
import com.openclassrooms.paymybuddyback.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(profiles = "test")
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldReturnOkForTheLogin() throws Exception {
        LoginDetailDTO userToLog = new LoginDetailDTO("geremi@gmail.com", "password1");

        mockMvc.perform(post("/api/login")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(userToLog)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnUnauthorized() throws Exception {
        LoginDetailDTO userToLog = new LoginDetailDTO("WrongEmail", "WrongPassword");

        mockMvc.perform(post("/api/login")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(userToLog)))
                .andExpect(status().isUnauthorized());
    }

}
