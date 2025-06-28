package com.openclassrooms.paymybuddyback.services;

import com.openclassrooms.paymybuddyback.models.User;
import com.openclassrooms.paymybuddyback.modelsDTO.UserRegisterDTO;

public interface IregisterUserService {

    public User register(UserRegisterDTO userRegisterDTO);

    public User updateUser(UserRegisterDTO userRegisterDTO);
}
