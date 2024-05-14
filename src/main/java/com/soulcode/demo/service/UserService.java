package com.soulcode.demo.service;

import com.soulcode.demo.models.Persona;
import com.soulcode.demo.models.Sector;
import com.soulcode.demo.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final PersonaRepository personaRepository;

    @Autowired
    public UserService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

}