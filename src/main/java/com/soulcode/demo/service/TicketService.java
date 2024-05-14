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

    private final TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public void createTicket(String tituloChamado, String descricao, String prioridade, Sector setorDeDirecionamento, String nomeUsuario, Sector setor) {
        Ticket ticket = new Ticket();
        ticket.setTituloChamado(tituloChamado);
        ticket.setDescricao(descricao);
        ticket.setPrioridade(prioridade);
        ticket.setDataSolicitacaoAtual(LocalDate.now());
        ticket.setSetorDeDirecionamento(setorDeDirecionamento);
        ticket.setStatus(Status.Aguardando_técnico);
        ticket.setNome(nomeUsuario);
        ticket.setSetor(setor);

        ticketRepository.save(ticket);
    }

    public List<Ticket> filtrarPorPrioridade(String filtroPropriedade) {
        if (filtroPropriedade != null && !filtroPropriedade.isEmpty()) {
            return ticketRepository.findByPrioridade(filtroPropriedade);
        } else {
            return ticketRepository.findAll();
        }
    }

    public List<Ticket> filtrarPorSetorDirecionamento(Sector setorDeDirecionamento) {
        if (setorDeDirecionamento != null) {
            return ticketRepository.findBySetorDeDirecionamento(setorDeDirecionamento);
        } else {
            return ticketRepository.findAll();
        }
    }

}