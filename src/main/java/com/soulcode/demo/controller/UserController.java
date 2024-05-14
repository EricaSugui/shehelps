package com.soulcode.demo.controller;

import com.soulcode.demo.models.Persona;
import com.soulcode.demo.models.Status;
import com.soulcode.demo.models.Ticket;
import com.soulcode.demo.models.TypeUser;
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

@Controller
@RequestMapping
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(TicketController.class);

    @Autowired
    TicketService ticketService;


    @GetMapping("/user")
    public String mostrarFormularioChamado(Model model, Principal principal, HttpSession session) {
        Persona usuario = (Persona) session.getAttribute("usuarioLogado");
        if (usuario == null || usuario.getTipo().equals(TypeUser.USUARIO)) {
            return "redirect:/login";
        }

        logger.debug("Mostrando formulário de criação de chamado.");
        return "user";
    }


    public UserController(TicketService ticketService) {
        this.ticketService = ticketService;
    }
    @GetMapping("/user-dashboard")
    public String userDashboard(Model model, HttpSession session, @RequestParam(required = false) Status filterStatus) {

        String email = (String) session.getAttribute("email");

        List<Ticket> userTickets = ticketService.getTicketsByEmail(email);

        if (filterStatus != null) {
            userTickets = ticketService.getTicketsByEmailAndStatus(email, filterStatus);
        }

        model.addAttribute("tickets", userTickets);
        model.addAttribute("filterStatus", filterStatus);

        return "user";
    }
}