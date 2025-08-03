package com.openclassrooms.paymybuddyback.services;

import com.openclassrooms.paymybuddyback.modelsDTO.LoginDetailDTO;


public interface IAuthService {

    /**
     * @param loginDetailDTO objet contenant le usernqme et le password
     * @return un boolean pour connqitre l'etqt de l'authentification
     */
    public boolean login(LoginDetailDTO loginDetailDTO);
}
