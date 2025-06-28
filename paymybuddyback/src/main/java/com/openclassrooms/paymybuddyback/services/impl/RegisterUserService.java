package com.openclassrooms.paymybuddyback.services.impl;

import com.openclassrooms.paymybuddyback.exceptions.EmailAlreadyExistException;
import com.openclassrooms.paymybuddyback.models.User;
import com.openclassrooms.paymybuddyback.modelsDTO.UserRegisterDTO;
import com.openclassrooms.paymybuddyback.repositories.UserRepository;
import com.openclassrooms.paymybuddyback.services.IregisterUserService;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class RegisterUserService implements IregisterUserService {

    private final static Logger logger = LogManager.getLogger(RegisterUserService.class);

    private final UserRepository userRepository;

    public RegisterUserService( UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(UserRegisterDTO userRegisterDTO) {
        if (userRepository.existsByEmail(userRegisterDTO.getEmail())) {
            logger.error("Erreur lors de l'enregistrement d'un utilisateur dans la base de donnee.");
            throw new EmailAlreadyExistException("Email déjà utilisé : " + userRegisterDTO.getEmail());
        }

        User user = new User();
        user.setUsername(userRegisterDTO.getUsername());
        user.setEmail(userRegisterDTO.getEmail());
        user.setPassword(userRegisterDTO.getPassword());
        return userRepository.save(user);
    }

    @Override
    public User updateUser(UserRegisterDTO userRegisterDTO) {

       User userToUpdate = userRepository.findByEmail(userRegisterDTO.getEmail())
               .orElseThrow(() -> new RuntimeException("Utilisateur non trouve"));

       userToUpdate.setPassword(userRegisterDTO.getPassword());

       return userRepository.save(userToUpdate);
    }
}
