package com.product.productordersystem.controller;

import com.product.productordersystem.DTO.LoginDTO;
import com.product.productordersystem.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        try {
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());

            Authentication auth = authenticationManager.authenticate(authToken);

            org.springframework.security.core.userdetails.User springUser =
                    (org.springframework.security.core.userdetails.User) auth.getPrincipal();

            return ResponseEntity.ok(Map.of(
                    "username", springUser.getUsername(),
                    "role", springUser.getAuthorities().iterator().next().getAuthority()
            ));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }
}