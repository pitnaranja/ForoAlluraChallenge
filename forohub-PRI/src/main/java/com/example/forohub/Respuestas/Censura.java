package com.example.forohub.Respuestas;

import com.example.forohub.Topicos.Topicos;
import com.example.forohub.Usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name="censura")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Censura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String palabra;
    @NotNull
    private Integer repeticiones;
    @NotNull
    private Boolean activo;






}

