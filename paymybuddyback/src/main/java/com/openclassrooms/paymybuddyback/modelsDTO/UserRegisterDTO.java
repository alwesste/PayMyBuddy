package com.openclassrooms.paymybuddyback.modelsDTO;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRegisterDTO (

        String username,

        @Email
        String email,

        @Size(min = 4, message = "Le mot de passe doit contenir au moins 4 caracteres")
        @NotBlank
        String password
)
{}
