package com.example.forohub.Cursos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursoDTO {
    @NotNull
    private  String nombrecurso;
    @NotNull
    @Enumerated(EnumType.STRING)
    private String categoria;

}
