package com.example.forohub.Respuestas.Validador;

import com.example.forohub.Respuestas.DatosRespuesta;
import com.example.forohub.infra.errores.ValidacionExcepcion;
import com.example.forohub.repositorio.UsuarioRepositorio;
import com.example.forohub.repositorio.topicosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;

public class TopicoValido implements ValidadorDeRespuestas{
    @Autowired
    private topicosRepositorio topicosRepositorio;

    public void  validar (DatosRespuesta datosRespuesta)
    {
        if (datosRespuesta.topico() == null) {
            return;
        }
        var  topicoSeleccionado = topicosRepositorio.findByIdAndActivoTrue(true,datosRespuesta.topico());

        if (!topicoSeleccionado) {
            throw new ValidacionExcepcion(" Este topico ya se cerro y no esta activo!");
        }

    }
}
