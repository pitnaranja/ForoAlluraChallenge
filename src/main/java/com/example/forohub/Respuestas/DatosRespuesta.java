package com.example.forohub.Respuestas;

import com.example.forohub.Topicos.Topicos;
import com.example.forohub.Usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosRespuesta(

        String mensaje,
        @NotNull
        Long topico,
        @NotNull
        LocalDateTime fechacreacion,
        @NotNull
        Long usuario,
        @NotNull
        Boolean solucion,
        @NotNull
        Boolean status


) {
}
