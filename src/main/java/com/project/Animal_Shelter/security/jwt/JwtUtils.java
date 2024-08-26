package com.project.Animal_Shelter.security.jwt;

import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    private final String jwtSecret = "SecretKeyForJWTs";
    private final int jwtExpirationMs = 86400000; // 24 horas

    // Genera el token JWT
    public String generateJwtToken(Authentication authentication) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        
        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    // Obtiene el nombre de usuario del token JWT
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    // Valida el token JWT
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            System.out.println("Firma inválida del token JWT: " + e.getMessage());
        } catch (MalformedJwtException e) {
            System.out.println("Token JWT malformado: " + e.getMessage());
        } catch (ExpiredJwtException e) {
            System.out.println("Token JWT expirado: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println("Token JWT no soportado: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("JWT claims string vacío: " + e.getMessage());
        }
        return false;
    }
}
