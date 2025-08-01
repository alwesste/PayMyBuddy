package com.openclassrooms.paymybuddyback.services.impl;

import com.openclassrooms.paymybuddyback.exceptions.ConnexionAlreadyExistException;
import com.openclassrooms.paymybuddyback.exceptions.InvalidTransactionException;
import com.openclassrooms.paymybuddyback.exceptions.UserNotFoundException;
import com.openclassrooms.paymybuddyback.models.Connexion;
import com.openclassrooms.paymybuddyback.models.User;
import com.openclassrooms.paymybuddyback.modelsDTO.ConnexionDTO;
import com.openclassrooms.paymybuddyback.modelsDTO.UserConnexionDTO;
import com.openclassrooms.paymybuddyback.modelsDTOMapper.UserConnexionDTOMapper;
import com.openclassrooms.paymybuddyback.repositories.ConnexionRepository;
import com.openclassrooms.paymybuddyback.repositories.UserRepository;
import com.openclassrooms.paymybuddyback.services.IconnexionService;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ConnexionService implements IconnexionService {

    private final static Logger logger = LogManager.getLogger(ConnexionService.class);

    private final UserRepository userRepository;
    private final ConnexionRepository connexionRepository;
    private final UserConnexionDTOMapper connexionDTOMapper;

    public ConnexionService(UserRepository userRepository, ConnexionRepository connexionRepository, UserConnexionDTOMapper connexionDTOMapper) {
        this.userRepository = userRepository;
        this.connexionRepository = connexionRepository;
        this.connexionDTOMapper = connexionDTOMapper;
    }

    @Override
    public void addConnexionWithEmail(ConnexionDTO connexionDTO) {

        if (connexionDTO.currentUserEmail().equalsIgnoreCase(connexionDTO.targetUserEmail())) {
            throw new InvalidTransactionException("Impossible de s'ajouter sois-même comme contact.");
        }

        User currentUser = userRepository.findByEmail(connexionDTO.currentUserEmail())
                .orElseThrow(() -> new UserNotFoundException("User introuvable" + connexionDTO.currentUserEmail()));

        User targetUser = userRepository.findByEmail(connexionDTO.targetUserEmail())
                .orElseThrow(() -> new UserNotFoundException("User introuvable" + connexionDTO.targetUserEmail()));

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
                .map(connexionDTOMapper)
                .toList();
    }

}
