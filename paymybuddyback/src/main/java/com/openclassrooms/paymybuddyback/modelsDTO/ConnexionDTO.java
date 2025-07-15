package com.openclassrooms.paymybuddyback.modelsDTO;

import java.io.Serializable;

public class ConnexionDTO implements Serializable {

    private String currentUserEmail;
    private String targetUserEmail;

    public ConnexionDTO() {
    }

    public ConnexionDTO(String currentUserEmail, String targetUserEmail) {
        this.currentUserEmail = currentUserEmail;
        this.targetUserEmail = targetUserEmail;
    }

    public String getCurrentUserEmail() {
        return currentUserEmail;
    }

    public void setCurrentUserEmail(String currentUserEmail) {
        this.currentUserEmail = currentUserEmail;
    }

    public String getTargetUserEmail() {
        return targetUserEmail;
    }

    public void setTargetUserEmail(String targetUserEmail) {
        this.targetUserEmail = targetUserEmail;
    }
}
