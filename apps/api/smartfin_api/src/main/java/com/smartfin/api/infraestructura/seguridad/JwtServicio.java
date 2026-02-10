package com.smartfin.api.infraestructura.seguridad;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class JwtServicio {

    private final byte[] claveFirma;
    private final long minutosExpiracion;
    private final String emisor;

    public JwtServicio(
            @Value("${smartfin.jwt.secreto}") String secreto,
            @Value("${smartfin.jwt.minutos_expiracion:120}") long minutosExpiracion,
            @Value("${smartfin.jwt.emisor:smartfin-api}") String emisor
    ) {
        this.claveFirma = derivarClave(secreto);
        this.minutosExpiracion = minutosExpiracion;
        this.emisor = emisor;
    }

    public String generarToken(UUID usuarioId, String correo, List<String> roles) {
        Instant ahora = Instant.now();
        Instant expira = ahora.plusSeconds(minutosExpiracion * 60);

        return Jwts.builder()
                .setIssuer(emisor)
                .setSubject(correo)
                .setIssuedAt(Date.from(ahora))
                .setExpiration(Date.from(expira))
                .claim("usuario_id", usuarioId.toString())
                .claim("roles", roles)
                .signWith(SignatureAlgorithm.HS256, claveFirma)
                .compact();
    }

    public Claims validarYObtenerClaims(String token) {
        return Jwts.parser()
                .setSigningKey(claveFirma)
                .parseClaimsJws(token)
                .getBody();
    }

    public String obtenerCorreo(Claims claims) {
        return claims.getSubject();
    }

    @SuppressWarnings("unchecked")
    public List<String> obtenerRoles(Claims claims) {
        Object valor = claims.get("roles");
        if (valor == null) {
            return List.of();
        }
        return (List<String>) valor;
    }

    private byte[] derivarClave(String secreto) {
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            return sha256.digest(secreto.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            throw new IllegalStateException("No se pudo derivar la clave de firma para JWT.", e);
        }
    }
}
