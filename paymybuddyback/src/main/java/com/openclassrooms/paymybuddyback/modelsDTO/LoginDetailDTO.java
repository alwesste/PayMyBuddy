package com.openclassrooms.paymybuddyback.modelsDTO;

public class LoginDetailDTO {
    private String email;
    private String password;

    public LoginDetailDTO() {
    }

    public LoginDetailDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
