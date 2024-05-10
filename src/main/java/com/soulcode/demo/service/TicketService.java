package com.soulcode.demo.service;

import com.soulcode.demo.models.Sector;
import com.soulcode.demo.models.Status;
import com.soulcode.demo.models.Ticket;
import com.soulcode.demo.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TicketService {
    @Autowired
    TicketRepository ticketRepository;


    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public void createTicket(String descricao, String prioridade, Sector setorDeDirecionamento) {
        Ticket ticket = new Ticket();
        ticket.setDescricao(descricao);
        ticket.setPrioridade(prioridade);
        ticket.setDataSolicitacaoAtual(LocalDate.now());
        ticket.setSetorDeDirecionamento(setorDeDirecionamento);
        ticket.setStatus(Status.Aguardando_técnico);


        ticketRepository.save(ticket);
    }

    public List<Ticket> filtrarPorPrioridade(String prioridade) {
        if (prioridade != null && !prioridade.isEmpty()) {
            return ticketRepository.findByPrioridade(prioridade);
        } else {
            return ticketRepository.findAll();
        }
    }
}