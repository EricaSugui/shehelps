package com.soulcode.demo.controller;

import com.soulcode.demo.models.Persona;
import com.soulcode.demo.models.Sector;
import com.soulcode.demo.models.Status;
import com.soulcode.demo.models.Ticket;
import com.soulcode.demo.repositories.TicketRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.nio.file.FileStore;
import java.util.List;
import java.util.stream.Collectors;

@SessionAttributes("usuarioLogado")
@Controller
public class TechnicianController {
    TicketRepository ticketRepository;

    @Autowired
    public TechnicianController(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }
    @GetMapping("/technical")
    public String telaTecnico(Model model, HttpSession session) {
        Persona usuario = (Persona) session.getAttribute("usuarioLogado");
        if (usuario == null) {
            return "redirect:/login";
        }

        Sector setorDoUsuario = usuario.getSetor();
        model.addAttribute("usuario", usuario);
        model.addAttribute("setorDoUsuario", setorDoUsuario);

        List<Ticket> ticketsAguardandoTecnico = ticketRepository.findByStatus(Status.Aguardando_técnico);
        model.addAttribute("items", ticketsAguardandoTecnico);

        List<Ticket> ticketsDoSetor = ticketsAguardandoTecnico.stream()
                .filter(ticket -> ticket.getSetorDeDirecionamento() == setorDoUsuario)
                .collect(Collectors.toList());
        model.addAttribute("abertos", ticketsDoSetor);

        return "technical";


//        Sector setorDoUsuario;
//        if (usuario != null) {
//            model.addAttribute("usuario", usuario);
//
//            // Obtém o setor do usuário e adiciona ao modelo
//            setorDoUsuario = usuario.getSetor();
//            model.addAttribute("setorDoUsuario", setorDoUsuario);
//
////            return "technical";
//        } else {
//            return "redirect:/login";
//        }
//
//// HttpSession session  Persona usuarioLogado = (Persona) session.getAttribute("usuarioLogado");
//
//        List<Ticket> items = ticketRepository.findByStatus(Status.valueOf("Aguardando_técnico"));
//        model.addAttribute("items", items);
////// Localiza pelo setor
////        List<Ticket> abertos = ticketRepository.findBySetorDeDirecionamento(Sector.TI);
////        model.addAttribute("abertos", abertos);
//
//        // Filtra os chamados pelo setor do usuário
//        List<Ticket> abertos = items.stream()
//                .filter(ticket -> ticket.getSetorDeDirecionamento() == setorDoUsuario)
//                .collect(Collectors.toList());
//
//        // Adiciona os chamados filtrados ao modelo
//        model.addAttribute("abertos", abertos);
//        return "technical";
    }

}