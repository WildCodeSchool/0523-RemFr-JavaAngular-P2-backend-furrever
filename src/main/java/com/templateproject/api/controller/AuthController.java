package com.templateproject.api.controller;

import com.templateproject.api.dto.LoginResponse;
import com.templateproject.api.entity.Location;
import com.templateproject.api.entity.Role;
import com.templateproject.api.entity.User;
import com.templateproject.api.repository.LocationRepository;
import com.templateproject.api.repository.RoleRepository;
import com.templateproject.api.repository.UserRepository;
import com.templateproject.api.service.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final AuthenticationManager authManager;
    private final TokenService tokenService;
    private final RoleRepository roleRepository;
    private final LocationRepository locationRepository;
    public AuthController(
            UserRepository userRepositoryInjected,
            AuthenticationManager authManagerInjected,
            TokenService tokenServiceInjected,
            RoleRepository roleRepositoryInjected,
            LocationRepository locationRepository) {
        this.userRepository = userRepositoryInjected;
        this.authManager = authManagerInjected;
        this.tokenService = tokenServiceInjected;
        this.roleRepository = roleRepositoryInjected;
        this.locationRepository = locationRepository;
    }

    @PostMapping("/register")
    public User register(@RequestBody User newUser) {
        // si l'utilisateur existe déjà sur cet email
        if (this.userRepository.findByEmail(newUser.getEmail()).isPresent()) {
            // lever une exception
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Un utilisateur est déjà enregistré avec cet email : " + newUser.getEmail());
        }
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        // associer le role ROLE_USER à mon nouvel utilisateur
        Role userRole = this.roleRepository.findByName("ROLE_USER")
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "No ROLE_USER found"));
        newUser.setRoles(Set.of(userRole));
        if (newUser.getPetSitter()){
            Role petSitterRole = this.roleRepository.findByName("ROLE_PETSITTER")
                    .orElseThrow(() ->
                            new ResponseStatusException(HttpStatus.NOT_FOUND,
                                    "No ROLE_PETSITTER found"));
            newUser.setRoles(Set.of(petSitterRole));
        }
        Location location = new Location();

        this.locationRepository.save(location);
        newUser.setLocation(location);

        return this.userRepository.save(newUser);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody User user) {
        // j'ai besoin de récupérer l'objet d'authentification de Spring pour cet utilisateur
        Authentication auth = this.authManager.authenticate(new UsernamePasswordAuthenticationToken(
                user.getEmail(), user.getPassword()
        ));
        String token = tokenService.generateToken(auth);
        return new LoginResponse(token);
    }
}
