package com.example.forohub.repositorio;

import com.example.forohub.Respuestas.Censura;
import com.example.forohub.Respuestas.Respuestas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface respuestaRepositorio extends JpaRepository <Respuestas, Long> {




}
