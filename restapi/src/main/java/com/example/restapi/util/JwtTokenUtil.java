package com.example.restapi.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.internal.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil {
    private static final long JWT_TOKEN_DURATION = 5 * 60 * 60;

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(UserDetails userDetails) {
        Map<String,Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_DURATION * 1000))
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }

    public String generateToken(String email) {
        Map<String,Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_DURATION * 1000))
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token,Claims::getSubject);
    }

    public boolean validateToken(String token , UserDetails userDetails) {
        final String email= getUsernameFromToken(token);
        return email.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        final Date expirationDate = getClaimFromToken(token,Claims::getExpiration);
        return expirationDate.before(new Date());
    }

    private <T> T getClaimFromToken(String token, Function<Claims,T> claimResolver) {
        final Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return claimResolver.apply(claims);
    }
}
