package com.example.forohub.Usuario;

import com.example.forohub.Roles.Roles;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String username;
    @Email
    @NotBlank
    private String email;
    @NotNull
    private String password;
    @DateTimeFormat
    private LocalDate fechaadmision;
    @ManyToMany(fetch = FetchType.EAGER,targetEntity = Roles.class,cascade = CascadeType.PERSIST)
    @JoinTable(name="usuarioroles", joinColumns= @JoinColumn(name="usuario_id"), inverseJoinColumns = @JoinColumn(name="roles_id"))
    @jakarta.validation.constraints.NotNull
    private Set<Roles> roles;
    @NotNull
    private Boolean activo;




}

