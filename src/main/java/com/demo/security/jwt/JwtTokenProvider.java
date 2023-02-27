package com.demo.security.jwt;

import com.demo.config.ApplicationProperties;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenProvider {
    private final ApplicationProperties properties;
    private final JwtBuilder jwtBuilder;
    private final JwtParser jwtParser;

    public JwtTokenProvider(ApplicationProperties properties) {
        this.properties = properties;
        Key key = Keys.hmacShaKeyFor(properties.getJwtSecretKey().getBytes(StandardCharsets.UTF_8));
        jwtBuilder = Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS256);
        jwtParser = Jwts.parserBuilder()
                .setSigningKey(key).build();
    }

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        String role = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .findAny().get();
        Date expiredTime = new Date((new Date()).getTime() + 1000 * 60 * properties.getTokenExpireTimeInMinutes());
        return jwtBuilder
                .setSubject(username)
                .claim("role", role)
                .setExpiration(expiredTime)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = jwtParser.parseClaimsJws(token).getBody();
        String username = claims.getSubject();
        Collection<? extends GrantedAuthority> authorities =
                List.of(new SimpleGrantedAuthority(claims.get("role").toString()));
        return new UsernamePasswordAuthenticationToken(username, token, authorities);
    }

    public boolean validateToken(String token) {
        try {
            jwtParser.parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
