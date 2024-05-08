package com.soulcode.demo.controller;

import ch.qos.logback.classic.Logger;
import com.soulcode.demo.models.Persona;
import com.soulcode.demo.models.Sector;
import com.soulcode.demo.models.TypeUser;
import com.soulcode.demo.repositories.TypeRepository;
import com.soulcode.demo.service.AuthenticationService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;


@Controller
@RequestMapping
public class AuthenticationController {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(UserController.class);

    @Autowired
    AuthenticationService autenticacaoService;

    @Autowired
    TypeRepository typeRepository;

    @PostMapping("/cadastro")
    public ResponseEntity<Object> save(@RequestParam String nome,
                                       @RequestParam String email,
                                       @RequestParam String senha,
                                       @RequestParam String confirmacaoSenha,
                                       @RequestParam TypeUser tipoUsuario,
                                       @RequestParam Sector setor) { // Corrigindo o nome do parâmetro para 'setor'

        logger.debug("Recebido pedido de registro de novo usuário.");

        if (nome == null || email == null || senha == null || setor == null) {
            logger.error("Nome, email, senha e setor são obrigatórios.");
            return ResponseEntity.badRequest().body("Por favor, preencha todos os campos obrigatórios.");
        }

        if (autenticacaoService.checkIfEmailAlreadyExists(email)) {
            logger.error("Este email já foi utilizado. Por favor, digite outro email.");
            return ResponseEntity.badRequest().body("Este email já foi utilizado. Por favor, digite outro email.");
        }

        if (!autenticacaoService.confirmedPassword(senha, confirmacaoSenha)) {
            logger.error("As senhas não correspondem.");
            return ResponseEntity.badRequest().body("As senhas não correspondem.");
        }

        try {
            autenticacaoService.registerNewUser(nome, email, senha, tipoUsuario, setor);
            logger.info("Usuário registrado com sucesso: " + email);
            return ResponseEntity.ok("Usuário registrado com sucesso.");
        } catch (Exception e) {
            logger.error("Erro ao registrar o usuário.", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao registrar o usuário. Por favor, tente novamente mais tarde.");
        }
    }

    @PostMapping("/login")
    public RedirectView login(@RequestParam String loginEmail,
                              @RequestParam String loginSenha,
                              @RequestParam TypeUser tipoUsuario,
                              @RequestParam Sector setor,
                              RedirectAttributes redirectAttributes) {

        logger.debug("Recebido pedido de login de usuário.");

        if (loginEmail.isEmpty() || loginSenha.isEmpty()) {
            logger.error("Email e senha são obrigatórios.");
            return new RedirectView("/login?error=true");
        }

        Persona usuario = typeRepository.findByEmailAndTipoUsuarioAndSetor(loginEmail, tipoUsuario, setor);

        if (usuario != null && usuario.getSenha().equals(loginSenha)) {
            logger.info("Usuário autenticado com sucesso: " + loginEmail);
            return new RedirectView("/user");
        } else {
            logger.error("Credenciais inválidas.");
            return new RedirectView("/login?error=true");
        }
    }
}