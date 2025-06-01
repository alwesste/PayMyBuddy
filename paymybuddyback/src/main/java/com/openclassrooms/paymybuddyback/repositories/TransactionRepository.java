package com.openclassrooms.paymybuddyback.repositories;

import com.openclassrooms.paymybuddyback.models.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer> {
}
