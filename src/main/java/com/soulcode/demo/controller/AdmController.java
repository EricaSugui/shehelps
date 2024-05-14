package com.soulcode.demo.controller;


import com.soulcode.demo.models.*;
import com.soulcode.demo.repositories.PersonaRepository;
import com.soulcode.demo.repositories.TicketRepository;
import com.soulcode.demo.service.TicketService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.stream.Collectors;

import static com.soulcode.demo.models.Status.*;
import static com.soulcode.demo.models.TypeUser.ADMINISTRADOR;
import static com.soulcode.demo.models.TypeUser.TECNICO;

@Controller
public class AdmController {

    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    TicketService ticketService;

    @GetMapping("admin")
    public String todosChamados(Model model,
                                HttpSession session,
                                @ModelAttribute("filtroPropriedade") Ticket filtroPropriedade,
                                @ModelAttribute("filtroSetor") Ticket filtroSetor) {

        Persona usuario = (Persona) session.getAttribute("usuarioLogado");
        if (usuario == null || !usuario.getTipo().equals(ADMINISTRADOR)) {
            return "redirect:/login";
        }

        model.addAttribute("usuario", usuario);

        List<Ticket> items = ticketRepository.findAll();
        model.addAttribute("items", items);

        List<Ticket> statusEmAberto = ticketRepository.findByStatus(Aguardando_técnico);
        List<Ticket> statusEmAtendimento = ticketRepository.findByStatus(Em_atendimento);
        List<Ticket> statusFinalizado = ticketRepository.findByStatus(Finalizado);

        int contagemStatusEmAberto = statusEmAberto.size();
        int contagemStatusEmAtendimento = statusEmAtendimento.size();
        int contagemStatusFinalizado = statusFinalizado.size();

        model.addAttribute("contagemStatusEmAberto", contagemStatusEmAberto);
        model.addAttribute("contagemStatusEmAtendimento", contagemStatusEmAtendimento);
        model.addAttribute("contagemStatusFinalizado", contagemStatusFinalizado);

        List<Ticket> ticketsFiltradosPrioridade = ticketService.filtrarPorPrioridade(filtroPropriedade.getPrioridade());
        model.addAttribute("ticketsFiltradosPrioridade", ticketsFiltradosPrioridade);

        List<Ticket> ticketsSetorDirecionamento = ticketService.filtrarPorSetorDirecionamento(filtroSetor.getSetorDeDirecionamento());
        model.addAttribute("ticketsSetorDirecionamento", ticketsSetorDirecionamento);

        return "admin";
    }

}
