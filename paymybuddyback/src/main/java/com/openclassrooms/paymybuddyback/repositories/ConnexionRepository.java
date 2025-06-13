package com.openclassrooms.paymybuddyback.repositories;

import com.openclassrooms.paymybuddyback.models.Connexion;
import com.openclassrooms.paymybuddyback.models.ConnexionId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnexionRepository extends CrudRepository<Connexion, ConnexionId> {
    boolean existsByUserId1AndUserId2(Integer userId1, Integer userId2);

}
