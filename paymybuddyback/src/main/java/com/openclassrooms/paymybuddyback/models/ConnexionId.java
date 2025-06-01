package com.openclassrooms.paymybuddyback.models;

import java.io.Serializable;
import java.util.Objects;

public class ConnexionId implements Serializable {
    private Integer userId1;
    private Integer userId2;

    public ConnexionId() {}

    public ConnexionId(Integer userId1, Integer userId2) {
        this.userId1 = userId1;
        this.userId2 = userId2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConnexionId)) return false;
        ConnexionId that = (ConnexionId) o;
        return Objects.equals(userId1, that.userId1) &&
                Objects.equals(userId2, that.userId2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId1, userId2);
    }
}