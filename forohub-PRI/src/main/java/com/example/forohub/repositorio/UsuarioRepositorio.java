package com.example.forohub.repositorio;

import com.example.forohub.Usuario.Usuario;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UsuarioRepositorio extends JpaRepository <Usuario,Long> {


//     @Query("SELECT u FROM Usuario u WHERE u.id = :id AND u.activo = :activo")
//     Optional<Usuario> findByIdAndActivo (@Param("id") Long id, @Param("activo") Boolean activo);

     @Query("SELECT u FROM Usuario u WHERE u.id = :id AND u.activo = :activo")
     Boolean findByIdAndActivoTrue (@Param("id") Long id, @Param("activo") Boolean activo );

     Optional<Usuario> findByUsername(String Username);


}
