package com.soulcode.demo.repositories;

import com.soulcode.demo.models.Sector;
import com.soulcode.demo.models.Status;
import com.soulcode.demo.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket>findByPrioridade(String prioridade);
    List<Ticket> findBySetorDeDirecionamento(Sector sector);
    List<Ticket> findByStatus(Status status);
}
