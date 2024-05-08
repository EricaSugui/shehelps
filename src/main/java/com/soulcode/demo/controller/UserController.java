package com.soulcode.demo.controller;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.core.model.Model;
import com.soulcode.demo.models.Persona;
import com.soulcode.demo.models.TypeUser;
import com.soulcode.demo.repositories.PersonaRepository;
import com.soulcode.demo.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
public class UserController {
    @GetMapping("/user")
    public String user(Model model) {
        return "user";
    }

    @GetMapping("/pagina-adm")
    public String pageAdm(@RequestParam(required = false) String nome, Model model, HttpServletRequest request) {
        return "ainda não sei o que colocar aqui kkkkk";
    }

}