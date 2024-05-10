package com.soulcode.demo.controller;

import com.soulcode.demo.models.Sector;
import com.soulcode.demo.models.Ticket;
import com.soulcode.demo.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class TechnicianController {
    TicketRepository ticketRepository;

    @Autowired
    public TechnicianController(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }
    @GetMapping("technical")
    public String telaTecnico(Model model) {
//        HttpSession session  Persona usuarioLogado = (Persona) session.getAttribute("usuarioLogado");
        // localiza pela prioridade, depois vou trocar pra status
        List<Ticket> items = ticketRepository.findByPrioridade("Alta");
        model.addAttribute("items", items);
// Localiza pelo setor
        List<Ticket> abertos = ticketRepository.findBySetorDeDirecionamento(Sector.TI);
        model.addAttribute("abertos", abertos);
        return "technical";
    }

}