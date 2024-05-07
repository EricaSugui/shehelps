package com.soulcode.demo.dto;

import com.soulcode.demo.models.TypeUser;

public class LoginDTO {
    private String email;
    private String senha;
    private TypeUser tipo;

    public LoginDTO() {
    }

    public TypeUser getTipo() {
        return tipo;
    }

    public void setTipo(TypeUser tipo) {
        this.tipo = tipo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
