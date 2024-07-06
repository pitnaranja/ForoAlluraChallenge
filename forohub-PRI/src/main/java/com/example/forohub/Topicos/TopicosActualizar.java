package com.example.forohub.Topicos;

import com.example.forohub.Cursos.Cursos;
import com.example.forohub.Usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record TopicosActualizar(
      @NotNull
      Long id,
        String titulo,
        String mensaje


) {
}
