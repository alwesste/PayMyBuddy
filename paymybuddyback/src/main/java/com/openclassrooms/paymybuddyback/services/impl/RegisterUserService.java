package com.openclassrooms.paymybuddyback.services.impl;

import com.openclassrooms.paymybuddyback.exceptions.EmailAlreadyExistException;
import com.openclassrooms.paymybuddyback.models.User;
import com.openclassrooms.paymybuddyback.repositories.UserRepository;
import com.openclassrooms.paymybuddyback.services.IregisterUserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RegisterUserService implements IregisterUserService {

    private final UserRepository userRepository;

    public RegisterUserService( UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyExistException("Email déjà utilisé : " + user.getEmail());
        }

        return userRepository.save(user);
    }
}
