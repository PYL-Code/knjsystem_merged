package edu.du.userservice.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // 시크릿 키는 최소 256bit 이상 (32자 이상 문자열)
    private static final String SECRET = "your-256-bit-secret-your-256-bit-secret";
    private static final long EXPIRATION = 1000 * 60 * 60; // 1시간

    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    public String createToken(String userId, String role, Long seqNoA001) {
        return Jwts.builder()
                .setSubject(userId)
                .claim("role", role)
                .claim("seqNoA001", seqNoA001)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}