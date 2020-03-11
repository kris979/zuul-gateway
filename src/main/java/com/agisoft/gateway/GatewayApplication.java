package com.agisoft.gateway;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;
import java.util.Date;

@SpringBootApplication
@EnableZuulProxy
@RestController
public class GatewayApplication {

    @GetMapping("/token")
    public String getToken() {

        return Jwts.builder().setSubject("kris").setExpiration(new Date(Instant.now().plusSeconds(3600).toEpochMilli()))
                   .signWith(
                           SignatureAlgorithm.HS256, "secret").compact();
    }

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

}
