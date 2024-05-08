package com.soulcode.demo.dto;

import com.soulcode.demo.models.TypeUser;

public class UserDTO {
    private String nome;
    private String email;
    private String senha;
    private String confirmacaoSenha;
    private String setor;
    private TypeUser tipo;

    public UserDTO() {
    }

    public TypeUser getTipo() {
        return tipo;
    }

    public void setTipo(TypeUser tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public String getConfirmacaoSenha() {
        return confirmacaoSenha;
    }

    public void setConfirmacaoSenha(String confirmacaoSenha) {
        this.confirmacaoSenha = confirmacaoSenha;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }
}
