package com.soulcode.demo.dto;

import com.soulcode.demo.models.TypeUser;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class TechnicianDTO {
    private String nome;
    private String email;
    private String setor;
    private String senha;
    private String confirmarSenha;

    private TypeUser tipo;

}
