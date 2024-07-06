package com.example.forohub.Security;

import com.example.forohub.Security.filters.JWTAuthorizationFilter;
import com.example.forohub.Security.filters.JWTAuthenticationFilter;
import com.example.forohub.Security.jwt.JWTUtils;
import com.example.forohub.service.UserDetailsImplemnts;
import org.apache.catalina.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration

public class SecurityConfig {
    @Autowired
    JWTUtils jwtUtils;
    @Autowired
    UserDetailsImplemnts userDetailsImplemnts;
    @Autowired
    JWTAuthorizationFilter authorizationFilter;



    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity
            ,AuthenticationManager authenticationManager) throws Exception { // com´portamiendto de acceso a los enponints

        JWTAuthenticationFilter jwtAuthenticationFilter =new JWTAuthenticationFilter(jwtUtils);
        jwtAuthenticationFilter.setAuthenticationManager(authenticationManager);
        jwtAuthenticationFilter.setFilterProcessesUrl("/login");
        System.out.println("entro");
        return httpSecurity
                .csrf(config -> config.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/hello").permitAll();
                    auth.anyRequest().authenticated();

                })
                .sessionManagement(session -> {
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);  // manejo de la sesion

                })
//                .httpBasic()
                .addFilter(jwtAuthenticationFilter)  // se valida el token
                .addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class) // se valida la utorizacion primero el token y luego el username
                .build();

    }

//    UserDetailsService userDetailsService (){    /// Crea un usuario que pueda acceder en memoria
//
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("Pedro")
//                .password("12345")
//                .roles().build());
//        return manager;}







    /// como gestionara los accesoos de los ususarios - requiere un paswword encoder JWT Beacrypt
    @Bean // administra la autenticacion
AuthenticationManager authenticationManager (HttpSecurity httpSecurity, PasswordEncoder passwordEncoder) throws Exception {
        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsImplemnts)
                .passwordEncoder(passwordEncoder)
                .and().build();


    }
@Bean
    PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }



}