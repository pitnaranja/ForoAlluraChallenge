package com.example.forohub.Respuestas.Validador;

import com.example.forohub.Respuestas.Censura;
import com.example.forohub.Respuestas.DatosRespuesta;
import com.example.forohub.infra.errores.ValidacionExcepcion;
import com.example.forohub.repositorio.censuraRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class Censor implements ValidadorDeRespuestas{

    @Autowired
    private censuraRepositorio censuraRepositorio;
    public void validar(DatosRespuesta datosRespuesta) {

        List<String> PalabrasCensuradas = censuraRepositorio.findByActivoTrue()
                .stream().map(Censura::getPalabra).toList();
        Boolean mensajeCensurado = null;

        for (String palabra : PalabrasCensuradas) {
            String palabraCensurada = palabra;
//escapar y agregar limites de palabra completa - case-insensitive
            Pattern regex = Pattern.compile("\\b" + Pattern.quote(palabraCensurada) + "\\b", Pattern.CASE_INSENSITIVE);
            Matcher match = regex.matcher(datosRespuesta.mensaje());

//la palabra está en el texto??
            if (match.find()) {  //si se quiere encontrar todas las ocurrencias: cambiar el if por while
                Censura palabraTabla = censuraRepositorio.findByPalabra(palabraCensurada);
                palabraTabla.setRepeticiones(palabraTabla.getRepeticiones() + 1);
                censuraRepositorio.save(palabraTabla);
                mensajeCensurado = true;
            }

        }
        if (mensajeCensurado!= null) {
            throw new ValidacionExcepcion(" El mensaje ha sido censurado.");
        }

    }



}