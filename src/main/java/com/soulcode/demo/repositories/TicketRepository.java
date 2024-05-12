package com.soulcode.demo.repositories;

import com.soulcode.demo.models.Sector;
import com.soulcode.demo.models.Status;
import com.soulcode.demo.models.Ticket;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket>findByPrioridade(String prioridade);
    List<Ticket> findBySetorDeDirecionamento(Sector sector);
    List<Ticket> findByStatus(Status status);
    Optional<Ticket> findById(Long id);
}
