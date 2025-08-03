package com.openclassrooms.paymybuddyback.modelsDTOMapper;

import com.openclassrooms.paymybuddyback.models.Transaction;
import com.openclassrooms.paymybuddyback.modelsDTO.TransactionDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class TransactionDTOMapper implements Function<Transaction, TransactionDTO> {

    /**
     * @param transaction the function argument
     * @return un objet dto
     */
    @Override
    public TransactionDTO apply(Transaction transaction) {
        return new TransactionDTO(
                transaction.getSender().getUsername(),
                transaction.getReceiver().getUsername(),
                transaction.getDescription(),
                transaction.getAmount()
        );
    }
}
