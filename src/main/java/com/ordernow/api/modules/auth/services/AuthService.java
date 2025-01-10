package com.ordernow.api.modules.auth.services;

import com.ordernow.api.modules.auth.entities.User;
import com.ordernow.api.modules.auth.repositories.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

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
    private AuthRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public String registerUser(String username, String email, String password) {
        validateUserExists(username, email);

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(password));
        userRepository.save(newUser);

        return generateJwtToken(newUser);
    }

    public String authenticateUser(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return generateJwtToken(user);
    }

    private String generateJwtToken(User user) {
        try {
            // Crear los claims del token
            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject(user.getUsername())
                    .issueTime(new Date())
                    .expirationTime(new Date(System.currentTimeMillis() + validityInMilliseconds))
                    .claim("roles", user.getRoles())
                    .build();
    
            // Crear el objeto JWT firmado
            SignedJWT signedJWT = new SignedJWT(
                    new JWSHeader(JWSAlgorithm.HS256),
                    claimsSet
            );
    
            // Convertir la clave secreta a byte[]
            byte[] secretBytes = secretKey.getBytes();
    
            // Firmar el JWT
            signedJWT.sign(new MACSigner(secretBytes));
    
            return signedJWT.serialize();
        } catch (Exception e) {
            throw new RuntimeException("Error generating JWT token", e);
        }
    }
    

    public boolean validateJwtToken(String token) {
        try {
            // Parsear el token firmado
            SignedJWT signedJWT = SignedJWT.parse(token);
    
            // Convertir la clave secreta a byte[]
            byte[] secretBytes = secretKey.getBytes();
    
            // Verificar la firma del token
            if (!signedJWT.verify(new MACVerifier(secretBytes))) {
                return false; // Firma no válida
            }
    
            // Validar la expiración del token
            Date expiration = signedJWT.getJWTClaimsSet().getExpirationTime();
            return expiration != null && expiration.after(new Date()); // True si el token no ha expirado
        } catch (Exception e) {
            return false; // Si ocurre un error, el token no es válido
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

    public Authentication getAuthenticatedUser() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public String refreshJwtToken(String token) {
        if (validateJwtToken(token)) {
            String username = getUsernameFromToken(token);
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            return generateJwtToken(user);
        }
        throw new RuntimeException("Invalid token or token expired");
    }

    public void logout() {
        SecurityContextHolder.clearContext();
    }

    private void validateUserExists(String username, String email) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username is already taken");
        }
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email is already taken");
        }
    }

    private Key getSecretKey() {
        byte[] secretBytes = Base64.getDecoder().decode(secretKey);
        return new javax.crypto.spec.SecretKeySpec(secretBytes, "HmacSHA256");
    }
}
