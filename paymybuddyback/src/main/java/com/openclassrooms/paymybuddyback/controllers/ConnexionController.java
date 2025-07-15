package com.openclassrooms.paymybuddyback.controllers;

import com.openclassrooms.paymybuddyback.modelsDTO.ConnexionDTO;
import com.openclassrooms.paymybuddyback.modelsDTO.UserConnexionDTO;
import com.openclassrooms.paymybuddyback.services.IconnexionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("/api")
public class ConnexionController {

    private static final Logger logger = LogManager.getLogger(ConnexionController.class);

    private final IconnexionService connexionService;

    public ConnexionController(IconnexionService connexionService) {
        this.connexionService = connexionService;
    }

    @PostMapping("/addConnexion")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void connexion(@RequestBody ConnexionDTO connexionDTO) {
        try {
            connexionService.addConnexionWithEmail(connexionDTO);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }


    @GetMapping("/seeConnexion")
    public List<UserConnexionDTO> getConnexion(@RequestParam String currentUserEmail) {
        try{
            return connexionService.getAllUserFromConnexion(currentUserEmail);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


}
