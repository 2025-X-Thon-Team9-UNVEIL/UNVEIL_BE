package com.unveil.domain.user.service;

import com.unveil.common.TokenDto;
import com.unveil.domain.user.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class TokenService {

    @Value("${jwt.secret:unveil-secret-key-for-jwt-token-generation-minimum-256-bits}")
    private String secret;

    @Value("${jwt.access-token-expiration:3600000}") // 기본 1시간 (ms)
    private long accessTokenExpiration;

    @Value("${jwt.refresh-token-expiration:604800000}") // 기본 7일 (ms)
    private long refreshTokenExpiration;

    public TokenDto createAndStoreTokens(User user) {
        String accessToken = generateAccessToken(user);
        String refreshToken = generateRefreshToken(user);

        // expiresIn: 초 단위 남은 시간
        long accessTokenExpiresIn = accessTokenExpiration / 1000;
        long refreshTokenExpiresIn = refreshTokenExpiration / 1000;

        return new TokenDto(accessToken, refreshToken, accessTokenExpiresIn, refreshTokenExpiresIn);
    }

    public Long getUserIdFromToken(String authorizationHeader) {
        String token = authorizationHeader;
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return Long.valueOf(claims.getSubject());
    }

    private String generateAccessToken(User user) {
        return generateToken(user, accessTokenExpiration);
    }

    private String generateRefreshToken(User user) {
        return generateToken(user, refreshTokenExpiration);
    }

    private String generateToken(User user, long expiration) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration);

        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .subject(String.valueOf(user.getId()))
                .claim("email", user.getEmail())
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(key)
                .compact();
    }
}

