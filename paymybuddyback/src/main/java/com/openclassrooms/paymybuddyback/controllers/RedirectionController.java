package com.openclassrooms.paymybuddyback.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RedirectionController {
    @RequestMapping(value = { "/", "/inscription", "/connexion", "/profil", "/transfer", "/relation" })
    public String redirect() {
        return "forward:/index.html";
    }
}
