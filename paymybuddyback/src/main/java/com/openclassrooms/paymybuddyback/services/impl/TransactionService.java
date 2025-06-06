package com.openclassrooms.paymybuddyback.services.impl;

import com.openclassrooms.paymybuddyback.exceptions.InvalidTransactionException;
import com.openclassrooms.paymybuddyback.exceptions.UserNotFoundException;
import com.openclassrooms.paymybuddyback.models.Transaction;
import com.openclassrooms.paymybuddyback.models.User;
import com.openclassrooms.paymybuddyback.modelsDTO.TransactionDTO;
import com.openclassrooms.paymybuddyback.repositories.TransactionRepository;
import com.openclassrooms.paymybuddyback.repositories.UserRepository;
import com.openclassrooms.paymybuddyback.services.ITransactionService;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Transactional
public class TransactionService implements ITransactionService {

    private static final Logger logger = LogManager.getLogger(TransactionService.class);

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Transaction moneyTransaction(TransactionDTO transactionDTO) {

        User sender = userRepository.findByUsername(transactionDTO.getSenderUsername())
                .orElseThrow(() -> new UserNotFoundException("Expéditeur introuvable"));

        User receiver = userRepository.findByUsername(transactionDTO.getReceiverUsername())
                .orElseThrow(() -> new UserNotFoundException("Destinataire introuvable"));

        if (sender.getUsername().equalsIgnoreCase(receiver.getUsername())) {
            throw new InvalidTransactionException("Le sender et le receiver sont la meme personne");
        }

        Transaction transaction = new Transaction();
        transaction.setSender(sender);
        transaction.setReceiver(receiver);
        transaction.setDescription(transactionDTO.getDescription());
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setDate(new Date());

        return transactionRepository.save(transaction);

    }

}

