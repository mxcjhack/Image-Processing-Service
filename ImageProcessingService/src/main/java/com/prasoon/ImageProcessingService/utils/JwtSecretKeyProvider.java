package com.prasoon.ImageProcessingService.utils;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.SecureRandom;

@Component
@Data
public class JwtSecretKeyProvider {

    private SecretKey key;

    @Value("${jwt.secret:null}")
    private String configuredSecret;

    @PostConstruct
    public void init() {
        if (configuredSecret != null && !configuredSecret.isEmpty()) {

            try {
                byte[] keyBytes = Decoders.BASE64.decode(configuredSecret);
                this.key = Keys.hmacShaKeyFor(keyBytes);
            } catch (Exception e) {
                generateRandomKey();
            }
        } else {
            generateRandomKey();
        }
    }

    private void generateRandomKey() {
        byte[] keyBytes = new byte[32];
        new SecureRandom().nextBytes(keyBytes);

        this.key = Keys.hmacShaKeyFor(keyBytes);
    }
}

