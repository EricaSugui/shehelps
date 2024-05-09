package com.soulcode.demo.controller;

import com.soulcode.demo.models.Ticket;
import com.soulcode.demo.repositories.TicketRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class TechnicianController {
    TicketRepository ticketRepository;

    public TechnicianController(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }



    @GetMapping("/")
    public String telaTecnico(Model model) {

        List<Ticket> tickets = ticketRepository.findByPrioridade("Alta");

        model.addAttribute("tickets", tickets);

        System.out.println("tickets:" + tickets);

        return "/";

    }

}