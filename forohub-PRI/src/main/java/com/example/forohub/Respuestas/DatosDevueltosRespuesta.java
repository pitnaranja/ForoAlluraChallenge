package com.example.forohub.Respuestas;

import com.example.forohub.Usuario.Usuario;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

import java.time.LocalDate;

public record DatosDevueltosRespuesta(String mensaje,LocalDate fechacreacion,Usuario usuario,Boolean activo,Boolean solucion) {
    DatosDevueltosRespuesta (Respuestas respuestas)
    {
        this(respuestas.getMensaje(),respuestas.getFechacreacion(),respuestas.getAutor(), null, null);
    }

}
