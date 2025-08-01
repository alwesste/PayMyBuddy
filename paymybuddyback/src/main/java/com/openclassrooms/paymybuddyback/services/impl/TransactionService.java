package com.openclassrooms.paymybuddyback.services.impl;

import com.openclassrooms.paymybuddyback.exceptions.InvalidTransactionException;
import com.openclassrooms.paymybuddyback.exceptions.UserNotFoundException;
import com.openclassrooms.paymybuddyback.models.Transaction;
import com.openclassrooms.paymybuddyback.models.User;
import com.openclassrooms.paymybuddyback.modelsDTO.TransactionDTO;
import com.openclassrooms.paymybuddyback.modelsDTOMapper.TransactionDTOMapper;
import com.openclassrooms.paymybuddyback.repositories.TransactionRepository;
import com.openclassrooms.paymybuddyback.repositories.UserRepository;
import com.openclassrooms.paymybuddyback.services.ITransactionService;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class TransactionService implements ITransactionService {

    private static final Logger logger = LogManager.getLogger(TransactionService.class);

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final TransactionDTOMapper transactionDTOMapper;

    public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository, TransactionDTOMapper transactionDTOMapper) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.transactionDTOMapper = transactionDTOMapper;
    }

    @Override
    public Transaction makeMoneyTransaction(TransactionDTO transactionDTO) {

        User sender = userRepository.findByEmail(transactionDTO.senderUsername())
                .orElseThrow(() -> new UserNotFoundException("Expéditeur introuvable"));

        User receiver = userRepository.findByEmail(transactionDTO.receiverUsername()) // changer pas email qui est la cle
                .orElseThrow(() -> new UserNotFoundException("Destinataire introuvable"));

        if (sender.getUsername().equalsIgnoreCase(receiver.getUsername())) {
            throw new InvalidTransactionException("Le sender et le receiver sont la meme personne");
        }

        if (transactionDTO.amount() <= 0) {
            throw new InvalidTransactionException("Le montant de la transaction ne peut etre negatif");
        }

        Transaction transaction = new Transaction();
        transaction.setSender(sender);
        transaction.setReceiver(receiver);
        transaction.setDescription(transactionDTO.description());
        transaction.setAmount(transactionDTO.amount());
        transaction.setDate(new Date());

        return transactionRepository.save(transaction);

    }

    @Override
    public List<TransactionDTO> seeMeneyTransaction(String currentUserEmail) {
        User currentUser = userRepository.findByEmail(currentUserEmail)
                .orElseThrow(() -> new UserNotFoundException("User introuvable : " + currentUserEmail));


        List<Transaction> transactionList = transactionRepository.findBySenderId(currentUser.getId());
        return transactionList.stream()
                .map(transactionDTOMapper)
                .toList();

    }


}

