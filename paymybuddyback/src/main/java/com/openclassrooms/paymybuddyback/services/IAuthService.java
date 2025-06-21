package com.openclassrooms.paymybuddyback.services;

import com.openclassrooms.paymybuddyback.modelsDTO.LoginDetailDTO;

public interface IAuthService {
    public boolean login(LoginDetailDTO loginDetailDTO);
}
