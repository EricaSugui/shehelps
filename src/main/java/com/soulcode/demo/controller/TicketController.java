package com.soulcode.demo.controller;

import com.soulcode.demo.dto.TicketDTO;
import com.soulcode.demo.models.Persona;
import com.soulcode.demo.models.Sector;
import com.soulcode.demo.models.Ticket;
import com.soulcode.demo.repositories.TicketRepository;
import com.soulcode.demo.repositories.TypeRepository;
import com.soulcode.demo.service.TicketService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;


@Controller
@RequestMapping
public class TicketController {

    @Autowired
    TicketRepository ticketRepository;
    private final TicketService ticketService;
    private TypeRepository typeRepository;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
        this.typeRepository = typeRepository;
    }

    @GetMapping("/chamado")
    public String mostrarFormularioChamado(Model model, Principal principal) {

        return "ticket";
    }

    @PostMapping("/criar-chamado")
    public String criarChamado(
            @RequestParam String tituloChamado,
            @RequestParam String descricao,
            @RequestParam String prioridade,
            @RequestParam Sector setorDeDirecionamento,
            RedirectAttributes redirectAttributes,
            Principal principal,
            HttpSession session) {

        String nomeUsuario = (String) session.getAttribute("nomeUsuario");
        Sector setor = (Sector) session.getAttribute("setor");

        ticketService.createTicket(tituloChamado,descricao, prioridade, setorDeDirecionamento, nomeUsuario, setor);
        redirectAttributes.addAttribute("mensagem", "Chamado criado com sucesso!");

        return "redirect:/chamado";
    }

    @GetMapping("/edit-ticket/{id}")
    public String editarChamado(@PathVariable("id") Long id, Model model) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID de chamado inválido: " + id));

        model.addAttribute("ticket", ticket);

        return "edit-ticket";
    }

    @PostMapping("/edit-ticket")
    public String atualizarChamado(@RequestParam("id") Long id, @ModelAttribute("ticket") Ticket ticket, RedirectAttributes redirectAttributes) {
        Ticket existingTicket = ticketRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de chamado inválido: " + id));

        existingTicket.setPrioridade(ticket.getPrioridade());
        existingTicket.setSetorDeDirecionamento(ticket.getSetorDeDirecionamento());

        ticketRepository.save(existingTicket);

        redirectAttributes.addAttribute("mensagem", "Chamado atualizado com sucesso!");
        return "redirect:/admin";
    }
}

