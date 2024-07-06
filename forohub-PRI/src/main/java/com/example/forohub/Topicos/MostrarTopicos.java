package com.example.forohub.Topicos;

import com.example.forohub.Cursos.Cursos;
import com.example.forohub.Usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record MostrarTopicos(

        @NotNull
        String titulo,
        @NotNull String mensaje,
        LocalDate fechacreacion,
        Boolean status,
        @NotNull
        String cursos,
        @NotNull
        String autor


) {
public MostrarTopicos( Topicos topicos) {
this(topicos.getTitulo(), topicos.getMensaje(), topicos.getFechacreacion(),topicos.getStatus(),topicos.getCursos(), topicos.getAutor());
}

}
