package com.openclassrooms.paymybuddyback.services;

import com.openclassrooms.paymybuddyback.models.Transaction;
import com.openclassrooms.paymybuddyback.modelsDTO.TransactionDTO;

public interface ITransactionService {

    public Transaction moneyTransaction(TransactionDTO transactionDTO);

}
