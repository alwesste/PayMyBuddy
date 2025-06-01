package com.openclassrooms.paymybuddyback.repositories;

import com.openclassrooms.paymybuddyback.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    boolean existsByEmail(String email);

}
