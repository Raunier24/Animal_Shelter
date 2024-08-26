package com.project.Animal_Shelter.controller;

import com.project.Animal_Shelter.dto.LoginRequest;
import com.project.Animal_Shelter.dto.RegisterRequest;
import com.project.Animal_Shelter.model.User;
import com.project.Animal_Shelter.repository.UserRepository;
import com.project.Animal_Shelter.security.jwt.JwtUtils;
import com.project.Animal_Shelter.security.jwt.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public String authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String jwt = jwtUtils.generateJwtToken(authentication);

        return jwt;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody RegisterRequest registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            return "Error: El nombre de usuario ya está tomado.";
        }

        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        // Asignar rol por defecto aquí

        userRepository.save(user);
        return "Usuario registrado exitosamente.";
    }
}