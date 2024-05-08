package com.soulcode.demo.controller;

import ch.qos.logback.classic.Logger;
import com.soulcode.demo.models.Persona;
import com.soulcode.demo.models.TypeUser;
import com.soulcode.demo.repositories.PersonaRepository;
import com.soulcode.demo.service.AuthenticationService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
public class UserController {


}