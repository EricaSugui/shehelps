package com.soulcode.demo.controller;

import com.soulcode.demo.models.*;
import com.soulcode.demo.repositories.PersonaRepository;
import com.soulcode.demo.repositories.TicketRepository;
import com.soulcode.demo.service.TicketService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.file.FileStore;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SessionAttributes("usuarioLogado")
@Controller
public class TechnicianController {
    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    TicketService ticketService;

    @Autowired
    PersonaRepository personaRepository;

    @GetMapping("/technical")
    public String telaTecnico(Model model, HttpSession session,@ModelAttribute("filtroSetor") Ticket filtroSetor) {
        Persona usuario = (Persona) session.getAttribute("usuarioLogado");
        if (usuario == null || usuario.getTipo().equals(TypeUser.USUARIO)) {
            return "redirect:/login";
        }

        Sector setorDoUsuario = usuario.getSetor();
        model.addAttribute("usuario", usuario);
        model.addAttribute("setorDoUsuario", setorDoUsuario);
        model.addAttribute("usuarioNome", usuario.getNome());

        List<Ticket> ticketsDoTecnico = ticketRepository.findByStatusIn(Arrays.asList(Status.Em_atendimento, Status.Finalizado));

        List<Ticket> ticketsDoSetor = ticketsDoTecnico.stream()
                .filter(ticket -> ticket.getSetorDeDirecionamento() == setorDoUsuario)
                .collect(Collectors.toList());

        model.addAttribute("items", ticketsDoSetor);

        List<Ticket> ticketsAguardandoTecnico = ticketRepository.findByStatus(Status.Aguardando_técnico);

        List<Ticket>ticketsAguardandoTecnicoDoSetor = ticketsAguardandoTecnico.stream()
                .filter(ticket -> ticket.getSetorDeDirecionamento() == setorDoUsuario)
                .collect(Collectors.toList());

        model.addAttribute("abertos", ticketsAguardandoTecnicoDoSetor);

        return "technical";

    }
    
    @PostMapping("/technical")
    public String tratarChamado(@RequestParam("id")Long id, @ModelAttribute("ticket") Ticket ticket, RedirectAttributes redirectAttributes){
        Ticket chamado = ticketRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de chamado inválido: " + id));


        ticketRepository.save(chamado);
        redirectAttributes.addAttribute("mensagem", "Chamado atualizado com sucesso!");
        return "/technical";
    }

    private void orElseThrow(Object o) {
    }

    @GetMapping("/technical-teste")
    public List<Persona> getTecnicos() {
        return personaRepository.findAll();
    }


}