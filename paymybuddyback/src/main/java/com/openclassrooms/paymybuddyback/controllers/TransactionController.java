package com.openclassrooms.paymybuddyback.controllers;

import com.openclassrooms.paymybuddyback.modelsDTO.TransactionDTO;
import com.openclassrooms.paymybuddyback.services.ITransactionService;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TransactionController {

    private static final Logger logger = LogManager.getLogger(TransactionController.class);

    private final ITransactionService transactionService;

    public TransactionController(ITransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * Permet d'effectuer des transactions vers un utilisateur.
     * @param transactionDTO un objet qui prend un String senderUsername, String receiverUsername, String description, double amount.
     */
    @PostMapping("/transaction")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void transaction(@Valid @RequestBody TransactionDTO transactionDTO) {
        logger.info("{} envoie {}$ a {}", transactionDTO.senderUsername(), transactionDTO.amount(), transactionDTO.receiverUsername());
        transactionService.makeMoneyTransaction(transactionDTO);
    }

    /**
     *
     * @param currentUserEmail
     * @return une liste des transactions d'un utilisateur.
     */
    @GetMapping("/seeTransaction")
    @ResponseStatus(code = HttpStatus.OK)
    public List<TransactionDTO> seeMoneyTransaction(@RequestParam String currentUserEmail) {
        logger.info("Affiche les transactions de {}", currentUserEmail);
        return transactionService.seeMeneyTransaction(currentUserEmail);
    }
}
