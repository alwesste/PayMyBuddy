package com.openclassrooms.paymybuddyback.services;

import com.openclassrooms.paymybuddyback.modelsDTO.ConnexionDTO;
import com.openclassrooms.paymybuddyback.modelsDTO.UserConnexionDTO;

import java.util.List;

public interface IconnexionService {
    public void addConnexionWithEmail(ConnexionDTO connexionDTO);

    public List<UserConnexionDTO> getAllUserFromConnexion(String currentUserMail);

}
