package com.openclassrooms.paymybuddyback.modelsDTO;

import jakarta.validation.constraints.Email;

public record ConnexionDTO (
        @Email
        String currentUserEmail,
        @Email
        String targetUserEmail){
}