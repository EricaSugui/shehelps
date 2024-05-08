package com.soulcode.demo.repositories;

import com.soulcode.demo.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket> findByStatusId(int statusId);

}
