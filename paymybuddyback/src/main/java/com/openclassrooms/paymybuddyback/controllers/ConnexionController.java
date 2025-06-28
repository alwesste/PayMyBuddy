package com.openclassrooms.paymybuddyback.controllers;

import com.openclassrooms.paymybuddyback.modelsDTO.ConnexionDTO;
import com.openclassrooms.paymybuddyback.modelsDTO.UserConnexionDTO;
import com.openclassrooms.paymybuddyback.services.IconnexionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
        connexionService.addConnexionWithEmail(connexionDTO);
    }


    @GetMapping("/seeConnexion")
    public List<UserConnexionDTO> getConnexion(@RequestParam String currentUserEmail) {
        return connexionService.getAllUserFromConnexion(currentUserEmail);
    }


}
