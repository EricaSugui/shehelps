package com.soulcode.demo.controller;

import com.soulcode.demo.models.Persona;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Controller
public class PublicController {
    @GetMapping("/")
    public String index(Model model, HttpSession session) {

        Persona usuario = (Persona) session.getAttribute("usuarioLogado");
        boolean usuarioLogado = usuario != null;
        model.addAttribute("usuarioLogado", usuarioLogado);

        return "index";
    }
    @GetMapping("/faq")
    public String faq() {
        return "faq";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/register")
    public String register() {
        return "register";
    }


}
