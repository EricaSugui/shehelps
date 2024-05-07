package com.soulcode.demo.service;

import com.soulcode.demo.models.Persona;
import com.soulcode.demo.models.TypeUser;
import com.soulcode.demo.repositories.PersonaRepository;
import com.soulcode.demo.repositories.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private TypeRepository typeRepository;

    public void registerNewUser(String nome, String email, String senha, TypeUser tipo) {
        Persona user = new Persona();
        user.setNome(nome);
        user.setEmail(email);
        user.setSenha(senha);
        user.setTipo(tipo);

        typeRepository.save(user);
    }

    public boolean checkIfEmailAlreadyExists(String email) {
        Persona userExists = typeRepository.findByEmail(email);
        return userExists != null;
    }

    public boolean confirmedPassword(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }
}