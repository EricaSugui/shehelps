package com.soulcode.demo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column
    private String Nome;

    @Column
    private String Email;

    @Column
    private String Senha;


    @Column
    @Enumerated(EnumType.STRING)
    private TypeUser Tipo;

    @Setter
    @Column
    @Enumerated(EnumType.STRING)
    private Sector Setor;

    public Persona() {

    }

}
