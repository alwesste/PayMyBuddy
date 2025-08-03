package com.openclassrooms.paymybuddyback.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Gère l'exception levée lorsque l'utilisateur n'est pas trouvé.
     *
     * @param ex l'exception UserNotFoundException
     * @return une réponse HTTP 404 avec le message d'erreur
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    /**
     * Gère l'exception levée lors d'une transaction invalide.
     *
     * @param ex l'exception InvalidTransactionException
     * @return une réponse HTTP 400 avec le message d'erreur
     */
    @ExceptionHandler(InvalidTransactionException.class)
    public ResponseEntity<String> handleInvalidTransaction(InvalidTransactionException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    /**
     * Gère l'exception levée lorsqu'une adresse e-mail est déjà utilisée.
     *
     * @param ex l'exception EmailAlreadyExistException
     * @return une réponse HTTP 400 avec le message d'erreur
     */
    @ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<String> handleEmailAlreadyExistExceptions(EmailAlreadyExistException  ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    /**
     * Gère l'exception levée lorsqu'une connexion est invalide.
     *
     * @param ex l'exception InvalidConnexionException
     * @return une réponse HTTP 400 avec le message d'erreur
     */
    @ExceptionHandler(InvalidConnexionException.class)
    public ResponseEntity<String> handleInvalidConnexionException(InvalidConnexionException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    /**
     * Gère l'exception levée lorsqu'une connexion existe déjà entre deux utilisateurs.
     *
     * @param ex l'exception ConnexionAlreadyExistException
     * @return une réponse HTTP 400 avec le message d'erreur
     */
    @ExceptionHandler(ConnexionAlreadyExistException.class)
    public ResponseEntity<String> handleConnexionAlreadyExist(ConnexionAlreadyExistException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    /**
     * Gère toutes les exceptions non spécifiquement prises en charge par un autre gestionnaire.
     *
     * @return une réponse HTTP 500 avec un message d'erreur générique
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAll() {
        return ResponseEntity.status(500).body("Une erreur serveur est survenue.");
    }

    /**
     * Gère les erreurs de validation des arguments annotés avec @Valid.
     *
     * @param ex l'exception MethodArgumentNotValidException
     * @return une réponse HTTP 400 contenant les messages d'erreur de validation
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String errorMessages = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + " " + error.getDefaultMessage())
                .distinct()
                .collect(Collectors.joining("; "));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessages);
    }
}
