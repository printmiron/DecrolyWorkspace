package com.example.dcuniverse.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import com.example.dcuniverse.configurations.JwtService;
import com.example.dcuniverse.model.AuthenticationResponse;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")

@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authenticationManager,
            UserDetailsService userDetailsService,
            JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody Map<String, String> request) {
        // 1. Autentica contra la DB
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.get("username"), request.get("password"))
        );

        // 2. Carga los detalles del usuario y genera el token
        UserDetails user = userDetailsService.loadUserByUsername(request.get("username"));
        String jwtToken = jwtService.generateToken(user);

        // 3. Devolvemos el objeto AuthenticationResponse que tiene el campo 'accessToken'
        // IMPORTANTE: El tipo de retorno debe ser ResponseEntity<AuthenticationResponse>
        return ResponseEntity.ok(new AuthenticationResponse(jwtToken));
    }
}
