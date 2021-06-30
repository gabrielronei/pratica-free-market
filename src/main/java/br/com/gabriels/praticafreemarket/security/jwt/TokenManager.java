package br.com.gabriels.praticafreemarket.security.jwt;

import br.com.gabriels.praticafreemarket.security.usuarioLogado.UsuarioLogadoDetails;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenManager {

    @Value("${free.market.jwt.secret}")
    private String jwtSecret;

    @Value("${free.market.jwt.expiration}")
    private long expiracaoEmMillis;

    public String gerarToken(Authentication authentication) {

        UsuarioLogadoDetails usuario = (UsuarioLogadoDetails) authentication.getPrincipal();

        final Date now = new Date();
        final Date expiration = new Date(now.getTime() + this.expiracaoEmMillis);

        return Jwts.builder()
                .setIssuer("Prática Free Market")
                .setSubject(Long.toString(usuario.getId()))
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, this.jwtSecret)
                .compact();
    }

    public boolean isValid(String jwt) {
        try {
            Jwts.parser().setSigningKey(this.jwtSecret).parseClaimsJws(jwt);
            return true;

        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public Long getUserIdFromToken(String jwt) {
        Claims claims = Jwts.parser().setSigningKey(this.jwtSecret)
                .parseClaimsJws(jwt).getBody();

        return Long.parseLong(claims.getSubject());
    }

}

