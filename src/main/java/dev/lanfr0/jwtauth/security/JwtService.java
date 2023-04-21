package dev.lanfr0.jwtauth.security;

import dev.lanfr0.jwtauth.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static dev.lanfr0.jwtauth.util.Constants.TOKEN_ENCRYPTION_KEY;
import static dev.lanfr0.jwtauth.util.Constants.TOKEN_EXPIRATION_TIME;

@Service
public class JwtService {

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    /*
     * Override if I would not pass extra claims
     * */
    public String generateToken(User user) {
        return generateToken(new HashMap<>(), user);
    }

    /*
     * extraClaims: contain the claims or the extra claims that we want to add
     * */
    public String generateToken(
            Map<String, Object> extraClaims,
            User user
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(user.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /*
     * Validate token
     * token
     * user: we want to validate if this token belong to the user
     * */
    public boolean isTokenValid(String token, User user) {
        final String email = extractEmail(token);

        return email.equals(user.getEmail()) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public Claims getClaimsFromToken(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(TOKEN_ENCRYPTION_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
