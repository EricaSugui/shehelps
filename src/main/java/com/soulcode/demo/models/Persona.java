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

    @Getter
    @Setter
    @Column
    private String Setor;

    @Column
    @Enumerated(EnumType.STRING)
    private TypeUser tipo;

    public Persona() {

    }

    public TypeUser getTipo() {
        return tipo;
    }

    public void setTipo(TypeUser tipo) {
        this.tipo = tipo;
    }
}
