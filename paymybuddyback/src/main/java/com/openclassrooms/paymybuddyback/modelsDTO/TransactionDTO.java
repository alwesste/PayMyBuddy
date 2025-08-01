package com.openclassrooms.paymybuddyback.modelsDTO;

public record TransactionDTO (String senderUsername, String receiverUsername, String description, double amount) {
}
