package com.minimarket.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {
    
    // Genera una clave segura y dinámica para firmar el token
    private final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    // 10 horas de duración en milisegundos
    private final long JWT_EXPIRATION = 1000 * 60 * 60 * 10; 

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username) // Claim necesario con el nombre de usuario
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION))
                .signWith(SECRET_KEY) // Firma segura
                .compact();
    }

    public boolean validateToken(String token, String usernameDetails) {
        final String username = extractUsername(token);
        return (username.equals(usernameDetails) && !isTokenExpired(token));
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
}  
