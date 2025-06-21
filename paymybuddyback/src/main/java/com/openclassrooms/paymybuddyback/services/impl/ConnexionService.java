package com.openclassrooms.paymybuddyback.services.impl;

import com.openclassrooms.paymybuddyback.exceptions.ConnexionAlreadyExistException;
import com.openclassrooms.paymybuddyback.exceptions.InvalidTransactionException;
import com.openclassrooms.paymybuddyback.exceptions.UserNotFoundException;
import com.openclassrooms.paymybuddyback.models.Connexion;
import com.openclassrooms.paymybuddyback.models.User;
import com.openclassrooms.paymybuddyback.modelsDTO.ConnexionDTO;
import com.openclassrooms.paymybuddyback.modelsDTO.UserConnexionDTO;
import com.openclassrooms.paymybuddyback.repositories.ConnexionRepository;
import com.openclassrooms.paymybuddyback.repositories.UserRepository;
import com.openclassrooms.paymybuddyback.services.IconnexionService;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ConnexionService implements IconnexionService {

    private final static Logger logger = LogManager.getLogger(ConnexionService.class);

    private final UserRepository userRepository;

    private final ConnexionRepository connexionRepository;

    public ConnexionService(UserRepository userRepository, ConnexionRepository connexionRepository) {
        this.userRepository = userRepository;
        this.connexionRepository = connexionRepository;
    }

    @Override
    public void addConnexionWithEmail(ConnexionDTO connexionDTO) {

        if (connexionDTO.getCurrentUserEmail().equalsIgnoreCase(connexionDTO.getTargetUserEmail())) {
            throw new InvalidTransactionException("Impossible d'ajouter soi-même comme contact.");
        }

        User currentUser = userRepository.findByEmail(connexionDTO.getCurrentUserEmail())
                .orElseThrow(() -> new UserNotFoundException("User introuvable" + connexionDTO.getCurrentUserEmail()));

        User targetUser = userRepository.findByEmail(connexionDTO.getTargetUserEmail())
                .orElseThrow(() -> new UserNotFoundException("User introuvable" + connexionDTO.getTargetUserEmail()));

        boolean exists = connexionRepository.existsByUserId1AndUserId2(currentUser.getId(), targetUser.getId());

        if (exists) {
            throw new ConnexionAlreadyExistException("Cette connexion existe deja.");
        }

        Connexion connexion = new Connexion(
                currentUser.getId(), targetUser.getId(), currentUser, targetUser
        );

        connexionRepository.save(connexion);
    }

    @Override
    public List<UserConnexionDTO> getAllUserFromConnexion(String currentUserEmail) {
        User currentUser = userRepository.findByEmail(currentUserEmail)
                .orElseThrow(() -> new UserNotFoundException("User introuvable : " + currentUserEmail));

        List<Connexion> connexions = connexionRepository.findByUserId1(currentUser.getId());

        return connexions.stream()
                .map(connexion -> new UserConnexionDTO(
                        connexion.getUser2().getId(),
                        connexion.getUser2().getUsername(),
                        connexion.getUser2().getEmail()
                ))
                .toList();
    }



}
