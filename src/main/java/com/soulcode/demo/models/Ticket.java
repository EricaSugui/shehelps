package com.soulcode.demo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @Column
    private Sector Setor;

    @Column
    private String descricao;

    @Column
    private String prioridade;

    @Column
    private LocalDate dataSolicitacaoAtual;

    @Column
    @Enumerated(EnumType.STRING)
    private Sector setorDeDirecionamento;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    public Ticket() {
    }

}
