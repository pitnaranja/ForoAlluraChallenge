package com.example.forohub.Respuestas;

import com.example.forohub.Topicos.Topicos;
import com.example.forohub.Usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="respuestas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Respuestas {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 @NotNull
 private String mensaje;
 @ManyToOne
 @JoinColumn(name = "id_topico")
 @NotNull
 private Topicos topico;
 @NotNull
 private LocalDate fechacreacion;
 @NotNull
 @ManyToOne
 @JoinColumn(name = "id_usuario")
 private Usuario autor;
 @NotNull
 private Boolean activo;
 @NotNull
 private Boolean solucion;




}
