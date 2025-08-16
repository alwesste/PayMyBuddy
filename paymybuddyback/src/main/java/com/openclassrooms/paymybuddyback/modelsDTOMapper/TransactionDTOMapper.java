package com.openclassrooms.paymybuddyback.modelsDTOMapper;

import com.openclassrooms.paymybuddyback.models.Transaction;
import com.openclassrooms.paymybuddyback.models.User;
import com.openclassrooms.paymybuddyback.modelsDTO.TransactionDTO;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    /**
     * @param dto
     * @param sender
     * @param receiver
     * @return un objet entity Transaction a partir de DTO
     */
    public Transaction toEntity(TransactionDTO dto, User sender, User receiver) {
        Transaction transaction = new Transaction();
        transaction.setSender(sender);
        transaction.setReceiver(receiver);
        transaction.setDescription(dto.description());
        transaction.setAmount(dto.amount());
        transaction.setDate(new Date());
        return transaction;
    }
}
