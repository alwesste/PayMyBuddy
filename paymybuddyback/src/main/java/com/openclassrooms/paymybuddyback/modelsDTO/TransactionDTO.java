package com.openclassrooms.paymybuddyback.modelsDTO;

public class TransactionDTO {
    private String senderUsername;
    private String receiverUsername;
    private String description;
    private double amount;

    public TransactionDTO() {}

    public TransactionDTO(String senderUsername, String receiverUsername, String description, double amount) {
        this.senderUsername = senderUsername;
        this.receiverUsername = receiverUsername;
        this.description = description;
        this.amount = amount;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public String getReceiverUsername() {
        return receiverUsername;
    }

    public void setReceiverUsername(String receiverUsername) {
        this.receiverUsername = receiverUsername;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
