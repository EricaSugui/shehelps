package com.soulcode.demo.controller;

import com.soulcode.demo.models.Sector;
import com.soulcode.demo.models.Ticket;
import com.soulcode.demo.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String mostrarFormularioChamado() {
        return "ticket";
    }


    @PostMapping("/criar-chamado")
    public String criarChamado(
            @RequestParam String descricao,
            @RequestParam String usuario,
            @RequestParam String setorUsuario,
            @RequestParam Sector setorDirecionamento,
            @RequestParam String prioridade
    ) {
        ticketService.createTicket(descricao, usuario, setorUsuario, setorDirecionamento, prioridade);
        // Redirecionar para uma página de confirmação ou outra página desejada após a criação do chamado
        return "redirect:/pagina-de-confirmacao";
    }
}
