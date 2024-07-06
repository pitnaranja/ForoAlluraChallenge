package com.example.forohub.Topicos;

import com.example.forohub.Cursos.Cursos;
import com.example.forohub.Usuario.Usuario;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;



public record TopicosDTO(
        @NotNull
        String titulo,
        @NotNull
        String mensaje,
        LocalDate fechacreacion,
        Boolean status,
        Boolean activo,
        @NotNull
        String usuario,
        @NotNull
        String curso) {
}
