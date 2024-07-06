package com.example.forohub.controller;

import com.example.forohub.Cursos.Categoria;

import com.example.forohub.Cursos.CursoDTO;
import com.example.forohub.Cursos.Cursos;
import com.example.forohub.Respuestas.DatosRespuesta;
import com.example.forohub.Respuestas.Respuestas;
import com.example.forohub.Topicos.MostrarTopicos;
import com.example.forohub.Topicos.Topicos;
import com.example.forohub.Topicos.TopicosActualizar;
import com.example.forohub.Topicos.TopicosDTO;
import com.example.forohub.Usuario.Usuario;
import com.example.forohub.Usuario.UsuarioDTO;
import com.example.forohub.Roles.Roles;
import com.example.forohub.Roles.TiposRoles;
import com.example.forohub.repositorio.UsuarioRepositorio;
import com.example.forohub.repositorio.cursoRepositorio;
import com.example.forohub.repositorio.respuestaRepositorio;
import com.example.forohub.repositorio.topicosRepositorio;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@ResponseBody
public class PrincipalController {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private cursoRepositorio cursoRepositorio;
    @Autowired
    private topicosRepositorio topicosRepositorio;
    @Autowired
    private respuestaRepositorio respuestaRepositorio;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/hola")
    public String Hello() {
        return "a gugu tata";
    }


    @GetMapping("/holaNotsecured")
    public String HelloNS() {
        return "a gugu tata";
    }


    @PostMapping("/crearUsuario")
    public ResponseEntity<?> crearUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        Set<Roles> roles = usuarioDTO.getRoles().stream()
                .map(role -> Roles.builder()
                        .rol(TiposRoles.valueOf(role)).build())
                .collect(Collectors.toSet());

        Usuario usuario = Usuario.builder()
                .username(usuarioDTO.getUsername())
                .password(passwordEncoder.encode(usuarioDTO.getPassword()))
                .email(usuarioDTO.getEmail())
                .fechaadmision(LocalDate.now())
                .roles(roles)
                .activo(usuarioDTO.getActivo()).build();
        usuarioRepositorio.save(usuario);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/crearCursos")
    public ResponseEntity<?> InsertarCurso(@Valid @RequestBody CursoDTO CursoDTO) {
        Cursos curso = Cursos.builder()
                .nombrecurso(CursoDTO.getNombrecurso())
                .categoria(Categoria.valueOf(CursoDTO.getCategoria()))
                .build();
        cursoRepositorio.save(curso);
        return ResponseEntity.ok(curso);
    }

    @PostMapping("/crearTopico")
    public ResponseEntity InsertarCurso(@Valid @RequestBody TopicosDTO TopicosDTO) {

        Optional<Cursos> cursos = cursoRepositorio.findById(Long.valueOf(TopicosDTO.curso()));
        Optional<Usuario> usuario = usuarioRepositorio.findById(Long.valueOf(TopicosDTO.usuario()));
        System.out.println("hola1");
        Cursos cursos1 = null;
        Usuario usuario1 = null;
        Topicos topico = null;

        if (cursos.isPresent()) {
            System.out.println("hola");
            cursos1 = cursos.get();
            usuario1 = usuario.get();
            System.out.println(cursos1);
            System.out.println(usuario1);


            topico = Topicos.builder()
                    .titulo(TopicosDTO.titulo())
                    .fechacreacion(TopicosDTO.fechacreacion())
                    .mensaje(TopicosDTO.mensaje())
                    .curso(cursos1)
                    .autor(usuario1.getUsername())
                    .cursos(cursos1.getNombrecurso())
                    .activo(TopicosDTO.activo())
                    .status(TopicosDTO.status())
                    .usuario(usuario1).build();
            topicosRepositorio.save(topico);
            return ResponseEntity.ok(topico);
        } else {
            return ResponseEntity.ok(topico);
        }

        //topicosRepositorio.save(topico);

    }

    @GetMapping("topicos/{id}")
    public ResponseEntity<Page<MostrarTopicos>> listadoTopicos(@PageableDefault(size = 2) Pageable paginacion) {
//
        return ResponseEntity.ok(topicosRepositorio.findByStatusTrue(paginacion).map(MostrarTopicos::new));
    }
//    @GetMapping
//    public ResponseEntity<List<MostrarTopicos>> listadoTopicosporCurso(String curso) {
////
//   return ResponseEntity.ok(topicosRepositorio.findByCurso(curso)
//           .stream().map(MostrarTopicos::new).collect(Collectors.toList()));


    @PutMapping("topicos/{id}")
    @Transactional
    public ResponseEntity<?> ActualizarTopico(@PathVariable Long id, @RequestBody TopicosActualizar topicosActualizar) {
        Optional<Topicos> topicos = topicosRepositorio.findById(id);
        if (topicos.isPresent()) {
            Topicos topicos1 = topicos.get();
            topicos1.ActualizarTopicos(topicosActualizar);

            return ResponseEntity.ok(topicos);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("topicos/{id}")
    @Transactional
    public ResponseEntity<?> DeactivarTopico(@PathVariable Long id) {
        Optional<Topicos> topicos = topicosRepositorio.findById(id);
        if (topicos.isPresent()) {
            Topicos topicos1 = topicos.get();
            topicos1.desactivarTopico();
            topicos = topicosRepositorio.findById(id);
            return ResponseEntity.ok(topicos);
        } else {
            return ResponseEntity.notFound().build();
        }

    }




//    @PostMapping("/topicoresponder/{id}")
//    public ResponseEntity<?> enviarRespuesta (@PathVariable Long id, @RequestBody DatosRespuesta datosRespuesta) {
//        Optional<Topicos> topicos = topicosRepositorio.findById(id);
//        // conseguir el id de la persona que responde
//        System.out.println("entro en el post");
//        if (topicos.isPresent()) {
//            System.out.println("encontro el topico");
//            Topicos topicos1 = topicos.get();
//            Optional<Usuario> usuario = usuarioRepositorio.findById(id);;
//
//            if (usuario.isPresent()) {
//                Usuario usuario1 = usuario.get();
//                System.out.println("encontro el usuario");
//
////                Respuestas RespuestaTopico = respuestaRepositorio.save(new Respuestas(null, datosRespuesta.mensaje(), topicos1, LocalDateTime.now()
////                        , usuario1, null, true));
//
//                Respuestas RespuestaTopico =new Respuestas(null, datosRespuesta.mensaje(), topicos1, datosRespuesta.fechacreacion()
//                        , usuario1, null, true);
//                return ResponseEntity.ok(RespuestaTopico);
//
//            }
//
//        }
//        else {
//            return ResponseEntity.notFound().build();}
//        return ResponseEntity.notFound().build();
//    }
//}



@PostMapping("/topicoresponder/{id}")
public ResponseEntity<?> enviarRespuesta (@PathVariable Long id, @RequestBody DatosRespuesta datosRespuesta) {
    System.out.println("entro en el post");
//    Optional<Topicos> topicos = topicosRepositorio.findById(id);
Usuario usuario = new Usuario();
Topicos topicos = new Topicos();
    Respuestas RespuestaTopico =new Respuestas(null, datosRespuesta.mensaje(), topicos, datosRespuesta.fechacreacion(), usuario, null, true);
//    // conseguir el id de la persona que responde
//
//    if (topicos.isPresent()) {
//        System.out.println("encontro el topico");
//        Topicos topicos1 = topicos.get();
//        Optional<Usuario> usuario = usuarioRepositorio.findById(id);;
//
//        if (usuario.isPresent()) {
//            Usuario usuario1 = usuario.get();
//            System.out.println("encontro el usuario");
//
////                Respuestas RespuestaTopico = respuestaRepositorio.save(new Respuestas(null, datosRespuesta.mensaje(), topicos1, LocalDateTime.now()
////                        , usuario1, null, true));
//
//            Respuestas RespuestaTopico =new Respuestas(null, datosRespuesta.mensaje(), topicos1, datosRespuesta.fechacreacion()
//                    , usuario1, null, true);
//            return ResponseEntity.ok(RespuestaTopico);
//
//        }
//
//    }
//    else {
//        return ResponseEntity.notFound().build();}
   return ResponseEntity.ok().build();


     }
}


















