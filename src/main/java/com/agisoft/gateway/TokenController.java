package com.agisoft.gateway;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Date;

@RestController
public class TokenController {

    @GetMapping("/token")
    public String getToken() {

        Date expiry = new Date(Instant.now().plusSeconds(3600).toEpochMilli());
        return Jwts.builder().setSubject("kris").setExpiration(expiry).signWith(
                SignatureAlgorithm.HS256, "secret").compact();
    }

}
