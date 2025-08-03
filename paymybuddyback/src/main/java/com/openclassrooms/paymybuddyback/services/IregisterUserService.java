package com.openclassrooms.paymybuddyback.services;

import com.openclassrooms.paymybuddyback.models.User;
import com.openclassrooms.paymybuddyback.modelsDTO.UserRegisterDTO;

public interface IregisterUserService {

    /**
     * @param userRegisterDTO objet contenant le username, l'email et le password
     * @return un user enregistrer dans la base de donnees
     */
    public User register(UserRegisterDTO userRegisterDTO);

    /**
     * @param userRegisterDTO objet contenant le username, l'email et le password
     * @return un nouveau mot de passe
     */
    public User updateUser(UserRegisterDTO userRegisterDTO);

}
