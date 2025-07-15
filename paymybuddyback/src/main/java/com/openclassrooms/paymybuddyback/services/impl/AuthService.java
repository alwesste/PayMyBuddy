package com.openclassrooms.paymybuddyback.services.impl;

import com.openclassrooms.paymybuddyback.models.User;
import com.openclassrooms.paymybuddyback.modelsDTO.LoginDetailDTO;
import com.openclassrooms.paymybuddyback.repositories.UserRepository;
import com.openclassrooms.paymybuddyback.services.IAuthService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements IAuthService {

    private final static Logger logger = LogManager.getLogger(AuthService.class);

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean login(LoginDetailDTO loginDetailDTO) {
        try {
            Optional<User> userOpt = userRepository.findByEmail(loginDetailDTO.getEmail());

            if (userOpt.isPresent() && userOpt.get().getPassword().equals(loginDetailDTO.getPassword())) {
                logger.info("Authentification réussie pour l'utilisateur : {}", loginDetailDTO.getEmail());
                return true;
            }

            logger.warn("Échec de l'authentification pour l'utilisateur : {}", loginDetailDTO.getEmail());
            return false;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
