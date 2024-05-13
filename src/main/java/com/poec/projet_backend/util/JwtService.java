package com.poec.projet_backend.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    // On récupère la valeur stockée dans le application.properties
    @Value("${application.security.jwt.secretKey}")
    private String SECRET_KEY;

    /* J'appelle une méthode générique "extractClaim" et je lui demande de m'extraire 'getsubject', c'est-à-dire le username */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /* Extrait les claims - aka : role, sub, iat, exp */
    /* claimsResolver est utilisé pour appliquer un traitement personnalisé
     * Ici, on  extrait le subject */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /* Extrait toutes les claims. Même si je ne veux que le "username", je dois toutes les extraire */
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /* Map<String, Object> contains all our Claims that we want to add to our Token */
    /* The subject is my userName or my userEmail contained in userDetails*/
    /* Event if we use useremail for storing a unique user in DB, for Spring Framework, it will be named "userEmail" */
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername()) /* On utilise "getUsername" mais nous avons override pour dire que ça retourne l'email*/
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000 ))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact(); /* Generate and return the token */
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY); /* Decode secret key */
        return Keys.hmacShaKeyFor(keyBytes); /* algorithm to decode our SECRET_KEY */
    }


    /* We need userDetails because we want to make sure that the token belongs to the right userDetails */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }



}




