package com.example.forohub.service;

import com.example.forohub.Usuario.Usuario;
import com.example.forohub.repositorio.UsuarioRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService  {
@Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

Usuario userDetails= usuarioRepositorio.findByUsername(username)
        .orElseThrow(()->new UsernameNotFoundException("El usuarios no ha sido encontrado"));

        Collection<? extends GrantedAuthority> authorities =userDetails.getRoles().stream()
                .map(role->new SimpleGrantedAuthority("ROLE_".concat(role.getRol().name()))).collect(Collectors.toSet());

        return new User(userDetails.getUsername(),userDetails.getPassword(),
               true,true,true,true,authorities );
    }
}
