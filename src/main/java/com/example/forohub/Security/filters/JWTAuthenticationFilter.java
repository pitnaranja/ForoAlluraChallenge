package com.example.forohub.Security.filters;

import com.example.forohub.Security.jwt.JWTUtils;
import com.example.forohub.Usuario.Usuario;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    @Autowired
    private JWTUtils jwtUtils;
    public JWTAuthenticationFilter (JWTUtils jwtUtils)
    {this.jwtUtils=jwtUtils;}
    // crea esta clase y este objeto para poder traer a esta clase los metodos deJWTutils que realizaran la autenticacion

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        Usuario usuario = null;
         String username =null;
         String password =null;


        try {
           usuario = new ObjectMapper().readValue(request.getInputStream(), Usuario.class); // toma el request que es donde va la informacion del usuatio y lo convierte en un objeto de tipo usuario
           username=  usuario.getUsername();
           password=  usuario.getPassword();

        } catch ( StreamReadException e)
        {throw new RuntimeException (e);}
        catch (DatabindException e)
        {throw new RuntimeException (e);}
        catch (IOException e)
        {throw new RuntimeException (e);}

        UsernamePasswordAuthenticationToken authenticationToken =new UsernamePasswordAuthenticationToken(username,password);

        return getAuthenticationManager().authenticate(authenticationToken);


    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException { // authResult contine los datos del usuario

        User user = (User) authResult.getPrincipal();
        String token = jwtUtils.GenerarToken(user.getUsername());
        response.addHeader("Authorization", token);

        Map<String, Object> httpResponse = new HashMap<>();
        httpResponse.put("token", token);
        httpResponse.put("message", "autenticacion correcta");
        httpResponse.put("Username", user.getUsername());

        response.getWriter().write(new ObjectMapper().writeValueAsString((httpResponse)));
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE); // cual sera el contenido de la respuesta
        response.getWriter().flush(); // se escribe en el response


        super.successfulAuthentication(request, response, chain, authResult);



    }
}
