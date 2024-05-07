package com.soulcode.demo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@Table(name = "chamados")
@Setter
@Getter
public class Ticket {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String titulo;

    private String descricao;

    private int prioridade;

    private LocalDateTime dataInicio;

    private String status;

    //    @ManyToOne
//    @JoinColumn(name = "setor_id")
//    private Sector sector;
//
//    @ManyToOne
//    @JoinColumn(name = "status_id")
//    private Status statusId;

    @ManyToOne
    @JoinColumn(name = "tecnico_id")
    private Persona tecnical;

    @Setter
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Persona user;

    public LocalDateTime getDataInicio() {
        return LocalDateTime.now();
    }

    public Ticket() {
    }

//    public String getNomeSolicitante() {
//        return (user != null) ? user.getNome() : null;
//    }
}
