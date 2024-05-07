package com.soulcode.demo.controller;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
@Controller
public class PublicController {
    @GetMapping("/")
    public String index() {
        return "index";
    }
    @GetMapping("/faq")
    public String faq() {
        return "faq";
    }

    /** a partir daqui, colocar essas rotas onde
     * for mais conveniente.
     * */
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/register")
    public String register() {
        return "register";
    }
    @GetMapping("/ticket")
    public String ticket() {
        return "ticket";
    }

}
