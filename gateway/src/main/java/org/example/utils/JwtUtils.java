package org.example.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class JwtUtils {

    private final String SECRET = "nM662ygSSPp8SIjRkgbOvvou4SANdpfLrN9IAXd1lM4=";

    public void validate(String token) {
        Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token);
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
