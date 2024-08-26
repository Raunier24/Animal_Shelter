package com.project.Animal_Shelter.security.jwt;

import com.project.Animal_Shelter.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {

    private Long id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
            .map(role -> new SimpleGrantedAuthority(role.getRoleName().name()))
            .collect(Collectors.toList());

        return new UserDetailsImpl(
            user.getId(),
            user.getUsername(),
            user.getPassword(),
            authorities);
    }

    // Métodos de UserDetails como getAuthorities(), getUsername(), etc.
}
