package com.example.forohub.Usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;



    @Data
    @AllArgsConstructor
    @NoArgsConstructor

    public class UsuarioDTO {

        @NotNull
        private String username;
        @Email
        @NotNull
        private String email;
        @NotNull
        private String password;

        private Set<String> roles;
        @NotNull
        private Boolean activo;


    }

