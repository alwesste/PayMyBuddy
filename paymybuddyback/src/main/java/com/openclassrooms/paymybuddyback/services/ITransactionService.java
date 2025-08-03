package com.openclassrooms.paymybuddyback.services;

import com.openclassrooms.paymybuddyback.models.Transaction;
import com.openclassrooms.paymybuddyback.modelsDTO.TransactionDTO;

import java.util.List;

public interface ITransactionService {

    /**
     * @param transactionDTO objet contenant "String senderUsername, String receiverUsername, String description, double amount"
     * @return une transaction enregistrer en BDD
     */
    public Transaction makeMoneyTransaction(TransactionDTO transactionDTO);

    /**
     * @param currentUserEmail
     * @return les transactions d'un utilisateur d'un user precis
     */
    public List<TransactionDTO> seeMeneyTransaction(String currentUserEmail);
}
