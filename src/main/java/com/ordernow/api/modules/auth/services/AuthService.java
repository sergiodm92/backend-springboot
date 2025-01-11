package com.ordernow.api.modules.auth.services;

import com.ordernow.api.modules.auth.entities.User;
import com.ordernow.api.modules.users.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

@Service
public class AuthService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long validityInMilliseconds;

    @Autowired
    private UserService userService; // Inyectar UserService

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public String registerUser(String username, String email, String password) {
        validateUserExists(username, email);

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(password));
        userService.save(newUser); // Ahora usamos el UserService para guardar el usuario

        return generateJwtToken(newUser);
    }

    public String authenticateUser(String username, String password) {
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return generateJwtToken(user);
    }

    public String refreshJwtToken(String token) {
        if (validateJwtToken(token)) {
            String username = getUsernameFromToken(token);
            User user = userService.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            return generateJwtToken(user);
        }
        throw new RuntimeException("Invalid token or token expired");
    }

    private String generateJwtToken(User user) {
        try {
            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject(user.getUsername())
                    .issueTime(new Date())
                    .expirationTime(new Date(System.currentTimeMillis() + validityInMilliseconds))
                    .claim("roles", user.getRoles())
                    .build();

            SignedJWT signedJWT = new SignedJWT(
                    new JWSHeader(JWSAlgorithm.HS256),
                    claimsSet
            );

            byte[] secretBytes = secretKey.getBytes();
            signedJWT.sign(new MACSigner(secretBytes));

            return signedJWT.serialize();
        } catch (Exception e) {
            throw new RuntimeException("Error generating JWT token", e);
        }
    }

    private void validateUserExists(String username, String email) {
        if (userService.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username is already taken");
        }
        if (userService.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email is already taken");
        }
    }

    // Métodos para validar y extraer información del JWT token
    public boolean validateJwtToken(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            byte[] secretBytes = secretKey.getBytes();

            if (!signedJWT.verify(new MACVerifier(secretBytes))) {
                return false;
            }

            Date expiration = signedJWT.getJWTClaimsSet().getExpirationTime();
            return expiration != null && expiration.after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public String getUsernameFromToken(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            return signedJWT.getJWTClaimsSet().getSubject();
        } catch (Exception e) {
            throw new RuntimeException("Error extracting username from token", e);
        }
    }

    public String getRolesFromToken(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            return (String) signedJWT.getJWTClaimsSet().getClaim("roles");
        } catch (Exception e) {
            throw new RuntimeException("Error extracting roles from token", e);
        }
    }

     public void logout() {
        // Limpia el contexto de seguridad para cerrar sesión
        SecurityContextHolder.clearContext();
    }
}
