package com.openclassrooms.paymybuddyback.models;

import jakarta.persistence.*;

@Entity
public class Connexion {
    @Id
    @Column(name = "user_id_1")
    private Integer userId1;

    @Id
    @Column(name = "user_id_2")
    private Integer userId2;

    @ManyToOne
    @JoinColumn(name = "user_id_1", insertable = false, updatable = false)
    private User user1;

    @ManyToOne
    @JoinColumn(name = "user_id_2", insertable = false, updatable = false)
    private User user2;

    public Connexion() {}

    public Connexion(Integer userId1, Integer userId2, User user1, User user2) {
        this.userId1 = userId1;
        this.userId2 = userId2;
        this.user1 = user1;
        this.user2 = user2;
    }

    public Integer getUserId1() {
        return userId1;
    }

    public void setUserId1(Integer userId1) {
        this.userId1 = userId1;
    }

    public Integer getUserId2() {
        return userId2;
    }

    public void setUserId2(Integer userId2) {
        this.userId2 = userId2;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }
}
