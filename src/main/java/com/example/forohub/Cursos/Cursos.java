package com.example.forohub.Cursos;
import com.example.forohub.Cursos.Categoria;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Table(name="cursos")
public class Cursos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String nombrecurso;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;

}
