package com.example.forohub.Respuestas.Validador;

import com.example.forohub.Respuestas.DatosRespuesta;
import com.example.forohub.infra.errores.ValidacionExcepcion;
import com.example.forohub.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;

public class UsuarioValido implements ValidadorDeRespuestas {
@Autowired
    private UsuarioRepositorio usuarioRepositorio;
    public void validar(DatosRespuesta datosRespuesta)
    {
        if (datosRespuesta.usuario() == null) {
            return;
        }
        var  topicoSeleccionado = usuarioRepositorio.findByIdAndActivoTrue(datosRespuesta.usuario(), true);

        if (!topicoSeleccionado) {
            throw new ValidacionExcepcion(" Este usuario no esta Activo!");
        }

    }
}
