package com.example.forohub.infra.errores;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class tratadorErrores {

// @ExceptionHandler (NoResourceFoundException.class)
//    public ResponseEntity noExisteElrecurso


//
        @ExceptionHandler(EntityNotFoundException.class) // con la anotacion aviso a Spring que busque si hay una excepcion y dentro del parentesis debo poner el tipo de excepcion que me esta dando la respuesta o la consola
        public ResponseEntity tratarError404(){
            return ResponseEntity.notFound().build();
        }

        // captura el error METHODARGUMENT... osea que falta alguna informacion en el cuerpo del JSON
    // el argumento del metodo debe ser de la misma clase que el error capturado
    // creamos una valriabel que a ese argumento le asigne getFieldErrors
    // los ordenamos con lso Streams , creamos un DTO para sacar la informacion mas ordenada y
    // devolvemos response Entity
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity tratarError400(MethodArgumentNotValidException e){
            var errores = e.getFieldErrors().stream().map(DatosErrorValidacion::new).toList();
            return ResponseEntity.badRequest().body(errores);
        }
//
//        @ExceptionHandler(ValidacionDeIntegridad.class)
//        public ResponseEntity errorHandlerValidacionesIntegridad(Exception e){
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//
//        @ExceptionHandler(ValidationException.class)
//        public ResponseEntity errorHandlerValidacionesDeNegocio(Exception e){
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//
        private record DatosErrorValidacion(String campo, String error){
            public DatosErrorValidacion(FieldError error) {
                this(error.getField(), error.getDefaultMessage());
           }
       }

    }

