package com.openclassrooms.paymybuddyback.repositories;

import com.openclassrooms.paymybuddyback.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);


}
