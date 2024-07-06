package com.example.forohub.Respuestas;


import com.example.forohub.Respuestas.Validador.ValidadorDeRespuestas;
import com.example.forohub.Topicos.Topicos;
import com.example.forohub.Usuario.Usuario;
import com.example.forohub.infra.errores.ValidacionExcepcion;
import com.example.forohub.repositorio.UsuarioRepositorio;
import com.example.forohub.repositorio.censuraRepositorio;
import com.example.forohub.repositorio.respuestaRepositorio;
import com.example.forohub.repositorio.topicosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class RespuestasService {

    // reglas de negocio . usuarios valido - topico activo y en el cuerpo dle mensaj no pueden ir palabras malsonantes
    @Autowired
    private censuraRepositorio censuraRepositorio;
    @Autowired
    private topicosRepositorio topicosRepositorio;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private respuestaRepositorio respuestaRepositorio;
    @Autowired
    List <ValidadorDeRespuestas> validarRespuestas;



        public DatosDevueltosRespuesta insertarRespuesta (DatosRespuesta datosRespuesta)
        {
            Optional<Topicos> topicos = topicosRepositorio.findById(datosRespuesta.topico());
            Optional<Usuario> usuario = usuarioRepositorio.findById(datosRespuesta.usuario());
            Topicos topicos1;
            Usuario usuario1;
            if (!topicos.isPresent()) { throw new ValidacionExcepcion(" Este topico no se ha encontrado");}
            else { topicos1=topicos.get();}
            if (!usuario.isPresent()) { throw new ValidacionExcepcion(" Este usuario no se ha encontrado");}
            else { usuario1=usuario.get();}

            validarRespuestas.forEach(v ->v.validar (datosRespuesta)); // comprueba la bateria de validaciones

           var respuesta = new  Respuestas(null, datosRespuesta.mensaje(),topicos1,datosRespuesta.fechacreacion()
            ,usuario1,datosRespuesta.activo(),datosRespuesta.solucion());

            respuestaRepositorio.save(respuesta);

            return new DatosDevueltosRespuesta(respuesta);

        }

    }



