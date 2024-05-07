package com.soulcode.demo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class Persona {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Getter
    @Setter
    @Column
    private String Nome;
    @Getter
    @Setter
    @Column
    private String Email;

    @Getter
    @Setter
    @Column
    private String Senha;

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
