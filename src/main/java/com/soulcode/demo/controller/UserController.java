package com.soulcode.demo.controller;

import com.soulcode.demo.models.Persona;
import com.soulcode.demo.models.Status;
import com.soulcode.demo.models.Ticket;
import com.soulcode.demo.service.TicketService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import static com.soulcode.demo.models.TypeUser.USUARIO;

@RequestMapping
@Controller
public class UserController {

    private final TicketService ticketService;

    @Autowired
    public UserController(TicketService ticketService) {
        this.ticketService = ticketService;
    }


    @GetMapping("/user")
    public String userDashboard(Model model, HttpSession session, @RequestParam(required = false) Status status) {
        Persona usuario = (Persona) session.getAttribute("usuarioLogado");
        if (usuario == null) {
            return "redirect:/login";
        }

        String email = usuario.getEmail();
        String nomeUsuario = usuario.getNome();

        boolean usuarioLogado = usuario != null;
        model.addAttribute("usuarioLogado", usuarioLogado);

        List<Ticket> userTickets = ticketService.getTicketsByEmail(email);

        System.out.println(status);

        if (status != null) {
            userTickets = ticketService.getTicketsByEmailAndStatus(email, status);

        }

        model.addAttribute("tickets", userTickets);
        model.addAttribute("filterStatus", status);
        model.addAttribute("nomeUsuario", nomeUsuario);

        return "user";
    }
}