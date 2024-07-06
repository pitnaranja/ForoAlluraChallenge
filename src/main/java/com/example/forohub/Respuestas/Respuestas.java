package com.example.forohub.Respuestas;

import com.example.forohub.Topicos.Topicos;
import com.example.forohub.Usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="respuestas")
@AllArgsConstructor
@NoArgsConstructor
public class Respuestas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
    @NotNull
    private String mensaje;
    @ManyToOne
    @JoinColumn(name="topico_id")
    @NotNull
    private Topicos topico;
    @NotNull
    private LocalDateTime fechaCreacion;
    @NotNull
    @ManyToOne
    @JoinColumn(name="usuario_id")
    private Usuario autor;
    @NotNull
    private Boolean solucion;
 @NotNull
 private Boolean status;



}
