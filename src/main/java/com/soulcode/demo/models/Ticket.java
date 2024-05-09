package com.soulcode.demo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
public class Ticket {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    @Column
    private String descricao;

    @Getter @Setter
    @Column
    private String prioridade;

    @Setter
    @Getter
    @Column
    private LocalDate dataSolicitacaoAtual;

    @Setter
    @Column
    @Enumerated(EnumType.STRING)
    private Sector setorDeDirecionamento;

    public Ticket() {
    }

    public Sector getSetorDeDirecionamento() {
        return setorDeDirecionamento;
    }

}