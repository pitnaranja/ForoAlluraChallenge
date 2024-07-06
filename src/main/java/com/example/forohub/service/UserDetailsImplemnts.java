package com.example.forohub.service;

import com.example.forohub.Usuario.Usuario;
import com.example.forohub.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;
@Component
public class UserDetailsImplemnts implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario=usuarioRepositorio.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("El usuario no esta en la base"));

        Collection<? extends GrantedAuthority> autorizaciones =usuario.getRoles()
                .stream()
                .map(role ->new SimpleGrantedAuthority(("ROLE_".concat(role.getRol().name()))))
                .collect(Collectors.toSet());

        return new User(usuario.getUsername(),
                usuario.getPassword(),
                true,true,true,true,autorizaciones);
    }
}
