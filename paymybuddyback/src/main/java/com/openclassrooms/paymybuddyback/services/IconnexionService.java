package com.openclassrooms.paymybuddyback.services;

import com.openclassrooms.paymybuddyback.modelsDTO.ConnexionDTO;
import com.openclassrooms.paymybuddyback.modelsDTO.UserConnexionDTO;

import java.util.List;

public interface IconnexionService {

    /**
     * @param connexionDTO objet contenant le current user email et le target user email
     */
    public void addConnexionWithEmail(ConnexionDTO connexionDTO);

    /**
     * @param currentUserMail
     * @return une liste des users coonecter a un utilisateur precis
     */
    public List<UserConnexionDTO> getAllUserFromConnexion(String currentUserMail);

}
