package com.example.forohub.security.filters;

import com.example.forohub.security.jwt.JWTUtils;
import com.example.forohub.security.jwt.JWTUtils;

import com.example.forohub.service.UserDetailsServiceImpl;
import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {
    @Autowired
    private JWTUtils jwtUtils;
   @Autowired
   private UserDetailsServiceImpl userDetailsService;
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request
            ,@NonNull HttpServletResponse response
            ,@NonNull FilterChain filterChain) throws ServletException, IOException {
        System.out.println("entro en do filter");
        String tokenenviado =request.getHeader("Authorization");
        if (tokenenviado!=null && tokenenviado.startsWith("Bearer "))
        { System.out.println("entro en el if  do filter");
            String token =tokenenviado.substring(7);
            if (jwtUtils.EsValidoEltoken(token))
            { System.out.println("token valido");
                String username= jwtUtils.obtenerUsuarioDelToken(token);
                System.out.println("el usuarios es:"+username);
                UserDetails userDetails= userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(username,null,userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);

    }
}
