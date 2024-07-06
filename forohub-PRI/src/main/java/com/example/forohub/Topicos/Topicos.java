package com.example.forohub.Topicos;

import com.example.forohub.Cursos.Cursos;
import com.example.forohub.Usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.forohub.repositorio.cursoRepositorio;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="topicos")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter


public class Topicos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String titulo;
    @NotNull
    private String mensaje;
    private LocalDate fechacreacion;

    private Boolean status;
    @NotNull
    private String cursos;
    @NotNull
    private String autor;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
    @ManyToOne
    @NotNull
    @JoinColumn(name = "id_curso")
    private Cursos curso;

    private Boolean activo;



    public void ActualizarTopicos (TopicosActualizar topicosActualizar) {
        if (topicosActualizar.titulo() != null) {
            this.titulo = topicosActualizar.titulo();
        }
        if (topicosActualizar.mensaje() != null) {
            this.mensaje = topicosActualizar.mensaje();
        }


    }
    public void desactivarTopico() {
        this.activo = false;
    }




//    public Topicos (TopicosDTO topicosDTO)
// {this.titulo= topicosDTO.titulo();
//     this.status = topicosDTO.status();
//     this.curso= Cursos.builder().build();
//     this.usuario=Usuario.builder().build();
//     this.FechaCreacion= topicosDTO.FechaCreacion();
//     this.mensaje=topicosDTO.mensaje();}

 }



