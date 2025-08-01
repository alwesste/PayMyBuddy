package com.openclassrooms.paymybuddyback.modelsDTOMapper;

import com.openclassrooms.paymybuddyback.models.Connexion;
import com.openclassrooms.paymybuddyback.modelsDTO.UserConnexionDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserConnexionDTOMapper implements Function<Connexion, UserConnexionDTO> {
    @Override
    public UserConnexionDTO apply(Connexion connexion) {
        return  new UserConnexionDTO(
                connexion.getUser2().getEmail(),
                connexion.getUser2().getUsername()
        );
    }

}
