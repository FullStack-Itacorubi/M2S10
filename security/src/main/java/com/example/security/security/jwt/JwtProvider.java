package com.example.security.security.jwt;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;

import java.util.Date;

@Component
public class JwtProvider {

    private String SECRET = "Senha_Segura";

    private int expirationMs = 24 * 60 * 60 * 1 * 1000;

    public String generateToken(String username) {
        return "Bearer ".concat(Jwts
                                    .builder()
                                    .setSubject(username)
                                    .setExpiration(new Date(new Date().getTime() - 1000 /*+ expirationMs*/))
                                    .signWith(SignatureAlgorithm.HS256, SECRET)
                                    .compact()
                                );
    }

    public String getUsernameFromJwt(String token) {
        String username = Jwts
                .parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

        return username;
    }

    public Boolean validateToken(String token) {
        Jws<Claims> claimsJws = Jwts
                .parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token);
        return true;
    }



}
