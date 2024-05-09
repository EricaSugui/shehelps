package com.soulcode.demo.dto;

import com.soulcode.demo.models.Sector;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketDTO {

    private String descricao;
    private String prioridade;
    private Sector setorDeDirecionamento;

    public TicketDTO() {
    }

}

