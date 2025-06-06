package com.openclassrooms.paymybuddyback.controllers;

import com.openclassrooms.paymybuddyback.modelsDTO.TransactionDTO;
import com.openclassrooms.paymybuddyback.repositories.TransactionRepository;
import com.openclassrooms.paymybuddyback.services.ITransactionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TransactionController {

    private static final Logger logger = LogManager.getLogger(TransactionController.class);

    private final ITransactionService transactionService;
    private final TransactionRepository transactionRepository;

    public TransactionController(ITransactionService transactionService, TransactionRepository transactionRepository) {
        this.transactionService = transactionService;
        this.transactionRepository = transactionRepository;
    }

    @PostMapping("/transaction")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void transaction (@RequestBody TransactionDTO transactionDTO) {
        logger.info("{} envoie {}$ a {}", transactionDTO.getSenderUsername(), transactionDTO.getAmount(), transactionDTO.getReceiverUsername());
        transactionService.moneyTransaction(transactionDTO);
    }
}
