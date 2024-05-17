package com.soulcode.demo.controller;


import com.soulcode.demo.models.*;
import com.soulcode.demo.repositories.PersonaRepository;
import com.soulcode.demo.repositories.TicketRepository;
import com.soulcode.demo.service.TicketService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

import static com.soulcode.demo.models.Status.*;
import static com.soulcode.demo.models.TypeUser.ADMINISTRADOR;
import static com.soulcode.demo.models.TypeUser.TECNICO;

@Controller
public class AdmController {

    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    TicketService ticketService;

    @GetMapping("/admin")
    public String todosChamados(Model model,
                                HttpSession session,
                                @RequestParam(required = false) String prioridade,
                                @RequestParam(required = false) String setorDeDirecionamento) {

        Persona usuario = (Persona) session.getAttribute("usuarioLogado");
        if (usuario == null || !usuario.getTipo().equals(ADMINISTRADOR)) {
            return "redirect:/login";
        }

        model.addAttribute("usuario", usuario);

        List<Ticket> items;
        if ((prioridade != null && !prioridade.isEmpty()) && (setorDeDirecionamento != null && !setorDeDirecionamento.isEmpty())) {
            Sector setorEnum = Sector.valueOf(setorDeDirecionamento);
            items = ticketRepository.findByPrioridadeAndSetorDeDirecionamento(prioridade, setorEnum);
        } else if (prioridade != null && !prioridade.isEmpty()) {
            items = ticketRepository.findByPrioridade(prioridade);
        } else if (setorDeDirecionamento != null && !setorDeDirecionamento.isEmpty()) {
            Sector setorEnum = Sector.valueOf(setorDeDirecionamento);
            items = ticketRepository.findBySetorDeDirecionamento(setorEnum);
        } else {
            items = ticketRepository.findAll();
        }

        model.addAttribute("items", items);

        int contagemStatusEmAberto = (int) items.stream().filter(item -> item.getStatus().equals(Aguardando_técnico)).count();
        int contagemStatusEmAtendimento = (int) items.stream().filter(item -> item.getStatus().equals(Em_atendimento)).count();
        int contagemStatusFinalizado = (int) items.stream().filter(item -> item.getStatus().equals(Finalizado)).count();

        model.addAttribute("contagemStatusEmAberto", contagemStatusEmAberto);
        model.addAttribute("contagemStatusEmAtendimento", contagemStatusEmAtendimento);
        model.addAttribute("contagemStatusFinalizado", contagemStatusFinalizado);

        model.addAttribute("filtroPropriedade", prioridade);
        model.addAttribute("filtroSetor", setorDeDirecionamento);

        return "admin";
    }


    @PostMapping("/admin")
    public String tratarChamado(@RequestParam("id") Long id, @ModelAttribute("ticket") Ticket aberto, HttpSession session, RedirectAttributes redirectAttributes) {
        Ticket chamado = ticketRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de chamado inválido: " + id));


        if (!aberto.getSetorDeDirecionamento().equals(chamado.getSetorDeDirecionamento()) &&
                aberto.getStatus().equals(chamado.getStatus())) {
            chamado.setStatus(Escalado_para_outro_setor);
        } else {
            chamado.setStatus(aberto.getStatus());
        }

        chamado.setSetorDeDirecionamento(aberto.getSetorDeDirecionamento());
        chamado.setPrioridade(aberto.getPrioridade());

        ticketRepository.save(chamado);
        redirectAttributes.addAttribute("mensagem", "Chamado atualizado com sucesso!");

        return "redirect:/admin";
    }



}
