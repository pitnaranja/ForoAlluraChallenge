package com.example.forohub.repositorio;

import com.example.forohub.Cursos.Cursos;
import com.example.forohub.Topicos.Topicos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface topicosRepositorio extends JpaRepository<Topicos,Long> {
    Page<Topicos> findByStatusTrue(Pageable paginacion);

//   List<Topicos> findByCurso(String curso);

   //Page<Topicos> findByStatusTrueOrderByFechacreacion(Pageable paginacion);
//    @Query("SELECT t FROM Topicos t WHERE t.cursos = :curso")
//    Page<Topicos> findByCursosOrderByFechacreacion(@Param("curso") String curso );

    @Query("SELECT t FROM Topicos t WHERE  t.fechacreacion < :fechai AND t.fechacreacion > :fechaf ")
    List<Topicos> findByCursosOrderByFechacreacion( @Param("fechai") LocalDate fechai, @Param("fechaf") LocalDate fechaf);

    @Query ( " SELECT t FROM Topicos t WHERE t.activo = :activo  AND t.id = :id" )
    Boolean findByIdAndActivoTrue(@Param("activo") Boolean activo, @Param("id") Long id);

}
