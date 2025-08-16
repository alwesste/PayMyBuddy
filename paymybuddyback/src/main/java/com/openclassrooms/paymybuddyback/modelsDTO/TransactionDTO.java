package com.openclassrooms.paymybuddyback.modelsDTO;

import jakarta.validation.constraints.NotNull;

public record TransactionDTO (
        String senderUsername,
        String receiverUsername,
        String description,
        @NotNull
        double amount) {
}
