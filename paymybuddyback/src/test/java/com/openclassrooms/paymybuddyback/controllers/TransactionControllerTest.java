package com.openclassrooms.paymybuddyback.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.paymybuddyback.modelsDTO.TransactionDTO;
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
public class TransactionControllerTest {

    // Permet de simuler des requetes HTTP
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void transactionTest() throws Exception {

        TransactionDTO newTransaction = new TransactionDTO("geremi", "paul", "Payement de avril" ,150.00);

        mockMvc.perform(post("/api/transaction")
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(newTransaction)))
                    .andExpect(status().isCreated());

    }

    @Test
    void shouldReturnNotFoundWhenSenderDoesNotExist() throws Exception {
        TransactionDTO transactionDTO = new TransactionDTO("nobodyHere", "paul", "Paiement test", 50.0);

        mockMvc.perform(post("/api/transaction")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(transactionDTO)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnNotFoundWhenReceiverDoesNotExist() throws Exception {
        TransactionDTO transactionDTO = new TransactionDTO("geremi", "nobodyHere", "Paiement test", 50.0);

        mockMvc.perform(post("/api/transaction")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(transactionDTO)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnBadRequestWhenSenderAndReceiverAreSame() throws Exception {
        TransactionDTO transactionDTO = new TransactionDTO("geremi", "geremi", "paying_mysefl", 50.0);

        mockMvc.perform(post("/api/transaction")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(transactionDTO)))
                .andExpect(status().isBadRequest());
    }

}
