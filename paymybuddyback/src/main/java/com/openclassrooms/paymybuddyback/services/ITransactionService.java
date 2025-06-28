package com.openclassrooms.paymybuddyback.services;

import com.openclassrooms.paymybuddyback.models.Transaction;
import com.openclassrooms.paymybuddyback.modelsDTO.TransactionDTO;

import java.util.List;

public interface ITransactionService {

    public Transaction moneyTransaction(TransactionDTO transactionDTO);

    public List<TransactionDTO> seeMeneyTransaction(String currentUserEmail);
}
