package com.example.forohub.Respuestas;

import com.example.forohub.Topicos.Topicos;
import com.example.forohub.Usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DatosRespuesta(
        @NotNull
        String mensaje,
        @NotNull
        Long topico,
        @NotNull
        LocalDate fechacreacion,
        @NotNull
        Long usuario,
        @NotNull
        Boolean activo,
        @NotNull
        Boolean solucion



) {
}
