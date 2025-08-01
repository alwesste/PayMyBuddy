package com.openclassrooms.paymybuddyback.services.impl;

import com.openclassrooms.paymybuddyback.exceptions.EmailAlreadyExistException;
import com.openclassrooms.paymybuddyback.exceptions.UserNotFoundException;
import com.openclassrooms.paymybuddyback.models.User;
import com.openclassrooms.paymybuddyback.modelsDTO.UserRegisterDTO;
import com.openclassrooms.paymybuddyback.repositories.UserRepository;
import com.openclassrooms.paymybuddyback.services.IregisterUserService;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RegisterUserService implements IregisterUserService {

    private final static Logger logger = LogManager.getLogger(RegisterUserService.class);

    private final UserRepository userRepository;

    public RegisterUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(UserRegisterDTO userRegisterDTO) {
        if (userRepository.existsByEmail(userRegisterDTO.email())) {
            logger.error("Erreur lors de l'enregistrement d'un utilisateur dans la base de donnee.");
            throw new EmailAlreadyExistException("Email déjà utilisé : " + userRegisterDTO.email());
        }

        User user = new User();
        user.setUsername(userRegisterDTO.username());
        user.setEmail(userRegisterDTO.email());
        user.setPassword(userRegisterDTO.password());
        return userRepository.save(user);
    }

    @Override
    public User updateUser(UserRegisterDTO userRegisterDTO) {

        User userToUpdate = userRepository.findByEmail(userRegisterDTO.email())
                .orElseThrow(() -> new UserNotFoundException("Utilisateur non trouve"));

        userToUpdate.setPassword(userRegisterDTO.password());

        return userRepository.save(userToUpdate);
    }

}
