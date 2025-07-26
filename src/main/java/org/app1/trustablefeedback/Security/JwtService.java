package org.app1.trustablefeedback.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;


import io.micrometer.common.lang.Nullable;
import org.app1.trustablefeedback.ClientDetails.ClientDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
    @Value("${JWT_KEY}")
    private String key;

    public String generateToken(ClientDetails clientDetails){
        return Jwts.builder()
                .setSubject(clientDetails.getUsername())
                .claim("role", clientDetails.getAuthorities().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 7200000))
                .signWith(Keys.hmacShaKeyFor(key.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    @Nullable
    public String extractUsername(String token){
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(key.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    @Nullable
    public String extractRole(String token){
        Claims claim = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(key.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claim.get("role").toString();
    }

    public Boolean isExpirated(String token){
        Date expiration = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(key.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();

        return expiration.before(new Date());
    }

    public Boolean validateToken(String token, ClientDetails client){
        String username = extractUsername(token);
        Boolean isExpirated = isExpirated(token);

        if (username != null) {
            return username.equals(client.getUsername()) && !isExpirated;
        }
        return false;
    }

}
