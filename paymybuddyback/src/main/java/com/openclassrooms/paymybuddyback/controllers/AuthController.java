package com.openclassrooms.paymybuddyback.controllers;

import com.openclassrooms.paymybuddyback.modelsDTO.LoginDetailDTO;
import com.openclassrooms.paymybuddyback.services.IAuthService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {

    private static final Logger logger = LogManager.getLogger(AuthController.class);

    private final IAuthService authService;

    public AuthController(IAuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDetailDTO loginDetailDTO) {
        boolean isAuthenticated = authService.login(loginDetailDTO);

        if (isAuthenticated) {
            logger.info("L'utilisateur est connecte {}", loginDetailDTO.getEmail());
            return ResponseEntity.ok().build();
        } else {
            logger.warn("Echec de de connexion de l'utilisateur {}", loginDetailDTO.getEmail());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email ou mot de passe incorrect");
        }
    }

}