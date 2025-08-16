package com.openclassrooms.paymybuddyback.controllers;

import com.openclassrooms.paymybuddyback.modelsDTO.LoginDetailDTO;
import com.openclassrooms.paymybuddyback.services.IAuthService;
import jakarta.validation.Valid;
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


    /**
     *
     * @param loginDetailDTO
     * @return une reponse status ok si la connection est etablie a partir de l'email du mot de passe
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDetailDTO loginDetailDTO) {

        boolean isAuthenticated = authService.login(loginDetailDTO);

        if (isAuthenticated) {
            logger.info("L'utilisateur est connecte {}", loginDetailDTO.email());
            LoginDetailDTO currentUser = new LoginDetailDTO(loginDetailDTO.email(), loginDetailDTO.password());

            return ResponseEntity.ok(currentUser);

        } else {
            logger.warn("Echec de de connexion de l'utilisateur {}", loginDetailDTO.email());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email ou mot de passe incorrect");
        }
    }

}