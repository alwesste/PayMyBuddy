package com.openclassrooms.paymybuddyback.modelsDTO;

public class UserConnexionDTO {
    private Integer id;
    private String email;
    private String username;

    public UserConnexionDTO() {
    }

    public UserConnexionDTO(Integer id, String email, String username) {
        this.id = id;
        this.email = email;
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
