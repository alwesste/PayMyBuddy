package com.openclassrooms.paymybuddyback.controllers;

import com.openclassrooms.paymybuddyback.modelsDTO.UserRegisterDTO;
import com.openclassrooms.paymybuddyback.services.IregisterUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class);
    private final IregisterUserService registerUserService;

    public UserController(IregisterUserService registerUserService) {
        this.registerUserService = registerUserService;
    }

    @PostMapping("/register")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void registerUser(@RequestBody UserRegisterDTO userRegisterDTO) {
        logger.info("Utilisateur a ajouter: email = {} , prenom = {}", userRegisterDTO.getEmail(), userRegisterDTO.getUsername());
        registerUserService.register(userRegisterDTO);
    }
}
