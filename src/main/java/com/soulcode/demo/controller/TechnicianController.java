package com.soulcode.demo.controller;

import com.soulcode.demo.models.*;
import com.soulcode.demo.repositories.PersonaRepository;
import com.soulcode.demo.repositories.TicketRepository;
import com.soulcode.demo.service.TicketService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.Console;
import java.nio.file.FileStore;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.soulcode.demo.models.Status.Escalado_para_outro_setor;

@SessionAttributes("usuarioLogado")
@Controller
public class TechnicianController {
    private static final Logger logger = LoggerFactory.getLogger(TicketController.class);

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    TicketService ticketService;

    @Autowired
    PersonaRepository personaRepository;

    @GetMapping("/technical")
    public String telaTecnico(Model model, HttpSession session,@ModelAttribute("filtroSetor") Ticket filtroSetor) {
        Persona usuario = (Persona) session.getAttribute("usuarioLogado");
        if (usuario == null || usuario.getTipo().equals(TypeUser.USUARIO)) {
            return "redirect:/login";
        }

        Sector setorDoUsuario = usuario.getSetor();
        model.addAttribute("usuario", usuario);
        model.addAttribute("setorDoUsuario", setorDoUsuario);
        model.addAttribute("usuarioNome", usuario.getNome());

        boolean verificacaoUser = usuario != null;
        model.addAttribute("verificacaoUser", verificacaoUser);

        List<Ticket> ticketsDoTecnicoEmAndamento = ticketRepository.findByStatus(Status.Em_atendimento);

        List<Ticket> ticketsDoSetor = ticketsDoTecnicoEmAndamento.stream()
                .filter(ticket -> ticket.getSetorDeDirecionamento() == setorDoUsuario)
                .collect(Collectors.toList());

        model.addAttribute("andamentos", ticketsDoSetor);

        int contagemStatusEmAndamento = ticketsDoSetor.size();
        model.addAttribute("contagemStatusEmAndamento", contagemStatusEmAndamento);

        List<Ticket> ticketsDoTecnico = ticketRepository.findByStatus(Status.Finalizado);

        List<Ticket> ticketsDoSetor2 = ticketsDoTecnico.stream()
                .filter(ticket -> ticket.getSetorDeDirecionamento() == setorDoUsuario)
                .collect(Collectors.toList());

        model.addAttribute("items", ticketsDoSetor2);

        int finalizados = ticketsDoSetor2.size();
        model.addAttribute("finalizados", finalizados);

        List<Ticket> ticketsAguardandoTecnico = ticketRepository.findByStatusIn(Arrays.asList(Status.Aguardando_técnico, Status.Escalado_para_outro_setor));

        List<Ticket>ticketsAguardandoTecnicoDoSetor = ticketsAguardandoTecnico.stream()
                .filter(ticket -> ticket.getSetorDeDirecionamento() == setorDoUsuario)
                .collect(Collectors.toList());

        model.addAttribute("abertos", ticketsAguardandoTecnicoDoSetor);

        int aberto = ticketsAguardandoTecnicoDoSetor.size();
        model.addAttribute("aberto", aberto);

        return "technical";

    }
    
    @PostMapping("/technical")
    public String tratarChamado(@RequestParam("id")Long id, @ModelAttribute("ticket") Ticket aberto, HttpSession session,RedirectAttributes redirectAttributes){
        Ticket chamado = ticketRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de chamado inválido: " + id));


        Persona usuarioLogado = (Persona) session.getAttribute("usuarioLogado");

        if (usuarioLogado != null) {
            chamado.setTecnicoAtribuido(usuarioLogado.getNome());
        }

        if (!aberto.getSetorDeDirecionamento().equals(chamado.getSetorDeDirecionamento()) &&
                aberto.getStatus().equals(chamado.getStatus())) {
            chamado.setStatus(Escalado_para_outro_setor);
        } else {
            chamado.setStatus(aberto.getStatus());
        }


        chamado.setSetorDeDirecionamento(aberto.getSetorDeDirecionamento());
        chamado.setRespostaTecnico(aberto.getRespostaTecnico());



        ticketRepository.save(chamado);
        redirectAttributes.addAttribute("mensagem", "Chamado atualizado com sucesso!");


        return "redirect:/technical";
    }

    private void orElseThrow(Object o) {
    }


}