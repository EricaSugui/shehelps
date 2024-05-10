package com.soulcode.demo.controller;

import com.soulcode.demo.dto.TicketDTO;
import com.soulcode.demo.models.Persona;
import com.soulcode.demo.models.Sector;
import com.soulcode.demo.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/chamado")
    public String mostrarFormularioChamado(Model model, Principal principal) {

        return "ticket";
    }

    @PostMapping("/criar-chamado")
    public String criarChamado(
            @RequestParam String descricao,
            @RequestParam String prioridade,
            @RequestParam Sector setorDeDirecionamento,
            RedirectAttributes redirectAttributes,
            Principal principal) {

        ticketService.createTicket(descricao, prioridade, setorDeDirecionamento);
        return "ticket";
    }
}

