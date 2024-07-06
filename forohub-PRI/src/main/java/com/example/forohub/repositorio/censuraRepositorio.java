package com.example.forohub.repositorio;

import com.example.forohub.Respuestas.Censura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface censuraRepositorio extends JpaRepository<Censura,Long> {

  List<Censura> findByActivoTrue();
  Censura findByPalabra(String palabra);
  @Query( " SELECT c FROM Censura c WHERE c.palabra LIKE %:palabra% ")
    Optional <Censura> findByPalabrita(@Param("palabra") String palabra);

}
