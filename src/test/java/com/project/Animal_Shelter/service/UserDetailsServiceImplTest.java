package com.project.Animal_Shelter.service;

import com.project.Animal_Shelter.model.Role;
import com.project.Animal_Shelter.model.User;
import com.project.Animal_Shelter.repository.UserRepository;
import com.project.Animal_Shelter.security.jwt.UserDetailsImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserDetailsServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoadUserByUsername_Success() {
        // Arrange
        String username = "testuser";
        User user = new User();
        user.setUsername(username);
        user.setId(1L);
        user.setPassword("password");
        Set<Role> roles = new HashSet<>();
        roles.add(new Role("ROLE_USER"));
        user.setRoles(roles);

        UserDetails userDetails = UserDetailsImpl.build(user);
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        // Act
        UserDetails result = userDetailsService.loadUserByUsername(username);

        // Assert
        assertEquals(userDetails.getUsername(), result.getUsername());
        assertEquals(userDetails.getPassword(), result.getPassword());
        assertEquals(userDetails.getAuthorities(), result.getAuthorities());
    }

    @Test
    public void testLoadUserByUsername_NotFound() {
        // Arrange
        String username = "testuser";
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(UsernameNotFoundException.class, () -> {
            userDetailsService.loadUserByUsername(username);
        });
        assertEquals("Usuario no encontrado con el nombre: " + username, exception.getMessage());
    }
}