package com.gafahtec.condominio.security;

import com.gafahtec.condominio.exception.CondominioException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;
import java.time.Instant;
import java.sql.Date;

import static io.jsonwebtoken.Jwts.parser;

@Service
public class JwtProvider {

    private KeyStore keystore;

    @Value("90000")
    private Long jwtExpirationInMillis;

    @PostConstruct
    public void init(){


        try {
            keystore = keystore.getInstance("JKS");
            InputStream resourceAsStream = getClass().getResourceAsStream("/gafahtec.jks");
            keystore.load(resourceAsStream,"Ascarz@2h".toCharArray());
        } catch (KeyStoreException | IOException | NoSuchAlgorithmException | CertificateException e) {
            throw new CondominioException("Exception occurred while loading keystore",e);
        }
    }

    public String generateToken(Authentication authentication){
        User principal = (User)authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(principal.getUsername())
                .setIssuedAt(Date.from(Instant.now()))
                .signWith(getPrivateKey())
                .setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationInMillis)))
                .compact();
    }

    public String generateTokenWithUserName(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(java.util.Date.from(Instant.now()))
                .signWith(getPrivateKey())
                .setExpiration(java.util.Date.from(Instant.now().plusMillis(jwtExpirationInMillis)))
                .compact();
    }

    private PrivateKey getPrivateKey(){
        try {
            return (PrivateKey) keystore.getKey("tomcat","Ascarz@2h".toCharArray());
        } catch (KeyStoreException  | NoSuchAlgorithmException | UnrecoverableKeyException e) {
            throw new CondominioException("Exceptioana occured while retrieving public key from keystore");
        }
    }

    private PublicKey getPublickey() {
        try {
            return keystore.getCertificate("tomcat").getPublicKey();
        } catch (KeyStoreException e) {
            throw new CondominioException("Exception occured while " +
                    "retrieving public key from keystore", e);
        }
    }

    public boolean validateToken(String jwt){
        parser().setSigningKey(getPublickey()).parseClaimsJws(jwt);
        return true;
    }

    public String getUsernameFromJwt(String token){
        Claims claims = parser()
                .setSigningKey(getPublickey())
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public Long getJwtExpirationInMillis(){
        return jwtExpirationInMillis;
    }
}
