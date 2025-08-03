package com.openclassrooms.paymybuddyback.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RedirectionController {

    /**
     * Rediriges les requetes vers les routes specifies :"/", "/inscription", "/connexion", "/profil", "/transfer", "/relation".
     * Cette redirection permet au front-end de gérer les routes côté client.
     *
     * @return la redirection sur la page index.html
     */
    @RequestMapping(value = {"/", "/inscription", "/connexion", "/profil", "/transfer", "/relation"})
    public String redirect() {
        return "forward:/index.html";
    }
}
