package com.project.Animal_Shelter.service;

import com.project.Animal_Shelter.dto.LoginRequest;
import com.project.Animal_Shelter.dto.RegisterRequest;
import com.project.Animal_Shelter.model.Role;
import com.project.Animal_Shelter.model.RoleName;
import com.project.Animal_Shelter.model.User;
import com.project.Animal_Shelter.repository.RoleRepository;
import com.project.Animal_Shelter.repository.UserRepository;
import com.project.Animal_Shelter.security.jwt.JwtUtils;
import com.project.Animal_Shelter.security.jwt.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Autentica al usuario y genera el token JWT.
     * @param loginRequest - contiene las credenciales de login del usuario.
     * @return JWT token si la autenticación es exitosa.
     */
    public String authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return jwtUtils.generateJwtToken(authentication);
    }

    /**
     * Registra un nuevo usuario con el rol por defecto (ROLE_USER).
     * @param registerRequest - contiene la información del nuevo usuario.
     * @return Mensaje indicando el resultado del registro.
     */
    public String registerUser(RegisterRequest registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            return "Error: El nombre de usuario ya está tomado.";
        }

        // Crear nuevo usuario
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        // Asignar rol por defecto (ROLE_USER)
        Set<Role> roles = new HashSet<>();
        Optional<Role> userRole = roleRepository.findByName(RoleName.ROLE_USER);

        if (userRole.isPresent()) {
            roles.add(userRole.get());
        } else {
            return "Error: No se encontró el rol de usuario.";
        }

        user.setRoles(roles);
        userRepository.save(user);

        return "Usuario registrado exitosamente.";
    }
}
