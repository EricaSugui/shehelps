package com.soulcode.demo.service;

import com.soulcode.demo.models.Sector;
import com.soulcode.demo.models.Ticket;
import com.soulcode.demo.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public void createTicket(String descricao, String usuario, String setorUsuario, Sector setorDeDirecionamento, String prioridade) {
        Ticket ticket = new Ticket();
        ticket.setDescricao(descricao);
        ticket.setUsuario(usuario);
        ticket.setSetorUsuario(setorUsuario);
        ticket.setSetorDeDirecionamento(setorDeDirecionamento);
        ticket.setPrioridade(prioridade);
        ticket.setDataSolicitacaoAtual(LocalDate.now());

        ticketRepository.save(ticket);
    }
}
