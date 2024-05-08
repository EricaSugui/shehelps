package com.soulcode.demo.dto;

import com.soulcode.demo.models.TypeUser;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class LoginDTO {
    private String email;
    private String senha;
    private TypeUser tipo;

    public LoginDTO() {
    }

}
