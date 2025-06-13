package com.openclassrooms.paymybuddyback.exceptions;

public class ConnexionAlreadyExistException extends RuntimeException {
    public ConnexionAlreadyExistException(String message) {
        super(message);
    }
}
