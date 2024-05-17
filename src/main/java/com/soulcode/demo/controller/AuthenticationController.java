package com.soulcode.demo.controller;

import ch.qos.logback.classic.Logger;
import com.soulcode.demo.models.Persona;
import com.soulcode.demo.models.Sector;
import com.soulcode.demo.models.TypeUser;
import com.soulcode.demo.repositories.TypeRepository;
import com.soulcode.demo.service.AuthenticationService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String save(@RequestParam String nome,
                       @RequestParam String email,
                       @RequestParam String senha,
                       @RequestParam String confirmacaoSenha,
                       @RequestParam TypeUser tipoUsuario,
                       @RequestParam String setor,
                       Model model) {

        Sector sectorEnum = Sector.valueOf(setor);

        logger.debug("Recebido pedido de registro de novo usuário.");

        if (nome == null || email == null || senha == null || setor == null) {
            logger.error("Nome, email, senha e setor são obrigatórios.");
            model.addAttribute("errorMessage", "Por favor, preencha todos os campos obrigatórios.");
            return "cadastro";
        }

        if (autenticacaoService.checkIfEmailAlreadyExists(email)) {
            logger.error("Este email já foi utilizado. Por favor, digite outro email.");
            model.addAttribute("errorMessage", "Este email já foi utilizado. Por favor, digite outro email.");
            return "cadastro";
        }

        if (!autenticacaoService.confirmedPassword(senha, confirmacaoSenha)) {
            logger.error("As senhas não correspondem.");
            model.addAttribute("errorMessage", "As senhas não correspondem.");
            return "cadastro";
        }

        try {
            autenticacaoService.registerNewUser(nome, email, senha, tipoUsuario, sectorEnum);
            logger.info("Usuário registrado com sucesso: " + email);
            return "redirect:/login";

        } catch (Exception e) {
            logger.error("Erro ao registrar o usuário.", e);
            model.addAttribute("errorMessage", "Erro ao registrar o usuário. Por favor, tente novamente mais tarde.");
            return "cadastro";
        }

    }

    @PostMapping("/login")
    public RedirectView login(@RequestParam String loginEmail,
                              @RequestParam String loginSenha,
                              @RequestParam TypeUser tipoUsuario,
                              RedirectAttributes redirectAttributes,
                              HttpSession session) {

        logger.debug("Recebido pedido de login de usuário.");

        if (loginEmail.isEmpty() || loginSenha.isEmpty()) {
            logger.error("Email e senha são obrigatórios.");
            return new RedirectView("/login?error=Email e senha sao obrigatorios.");
        }

        Persona usuario = typeRepository.findByEmailAndTipoUsuario(loginEmail, tipoUsuario);


        if (usuario != null && usuario.getSenha().equals(loginSenha)) {
            logger.info("Usuário autenticado com sucesso: " + loginEmail);

            session.setAttribute("nomeUsuario", usuario.getNome());
            session.setAttribute("setorUsuario", usuario.getSetor());
            session.setAttribute("usuarioLogado", usuario);
            session.setAttribute("email", loginEmail);

            switch (tipoUsuario) {
                case USUARIO:
                    return new RedirectView("/user");
                case ADMINISTRADOR:
                    return new RedirectView("/admin");
                case TECNICO:
                    return new RedirectView("/technical");
                default:
                    logger.error("Tipo de usuário inválido.");
                    return new RedirectView("/login?error=Tipo de usuário invalido.");
            }
        } else {
            logger.error("Credenciais inválidas.");
            return new RedirectView("/login?error=Credenciais invalidas.");
        }
    }
}