package com.openclassrooms.paymybuddyback.controllers;

import com.openclassrooms.paymybuddyback.modelsDTO.UserRegisterDTO;
import com.openclassrooms.paymybuddyback.services.IregisterUserService;
import jakarta.validation.Valid;
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

    /**
     * Permet d'enregistrer un nouvel utilisateur en BDD
     *
     * @param userRegisterDTO objet contenant username, email et password
     */
    @PostMapping("/register")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void registerUser(@Valid @RequestBody UserRegisterDTO userRegisterDTO) {
        logger.info("Utilisateur a ajouter: email = {} , prenom = {}", userRegisterDTO.email(), userRegisterDTO.username());
        registerUserService.register(userRegisterDTO);
    }

    /**
     * Permet de changer le mot de passe de l'utilisateur.
     *
     * @param userRegisterDTO objet contenant username, email et password
     */
    @PostMapping("/updatePassword")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void updateUser(@Valid @RequestBody UserRegisterDTO userRegisterDTO) {
        registerUserService.updateUser(userRegisterDTO);
    }
}
