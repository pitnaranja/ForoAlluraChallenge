package com.example.forohub.security.jwt;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.digester.Digester;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;


@Component
@Slf4j
public class JWTUtils {

//    @Value("${jwt.secret.key}")

    final String secretKey = "73503a245e72316e66443b56543f395b6f7e5849424e41495132425a4d307d22";
//    @Value("${jwt.time.expiration}") http://network-logix.net/

    final String timeExpiration= "68400000";

    // generar token acceso

    public String GenerarToken ( String Usuario)
    {
        return Jwts.builder()
                .setSubject(Usuario)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ Long.parseLong(timeExpiration)))
                .signWith(getSignatureKey(), SignatureAlgorithm.HS256)
                .compact();

    }

    public Boolean EsValidoEltoken ( String Token) {
        try {
            Jwts.parser()
                    .setSigningKey(getSignatureKey())
                    .build()
                    .parseClaimsJws(Token)
                    .getBody();
            return true;
        } catch
        (Exception e) {
            log.error("Token invalido");
            return false;


        }
    }
    public  <T> T ObtenerUnSoloClaim ( String Token, Function<Claims,T> Reclamos)
    {
        Claims claims = ObtenerClaimsPayload(Token);
        return Reclamos.apply(claims);
    }
    public String obtenerUsuarioDelToken ( String Token) {

        return ObtenerUnSoloClaim(Token,Claims::getSubject);
    }

    // obtener informacion del TOKEN - osea los claims

    public Claims ObtenerClaimsPayload ( String Token){
        return Jwts.parser()
                .setSigningKey(getSignatureKey())
                .build()
                .parseSignedClaims(Token)
                .getBody();
    }
    public Key getSignatureKey (){
        byte [] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }


}