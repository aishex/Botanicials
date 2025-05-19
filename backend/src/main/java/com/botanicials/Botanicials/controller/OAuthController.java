package com.botanicials.Botanicials.controller;

import com.botanicials.Botanicials.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.Principal;
import java.util.Map;

@RestController
public class OAuthController {

    @Autowired
    private UserService userService;

    // google redirect
    @GetMapping("/auth/google")
    public void redirectToGoogleAuth(HttpServletResponse response) throws IOException{
        response.sendRedirect("/oauth2/authorization/google");
    }

    // return user info
    @GetMapping("/auth/me")
    public ResponseEntity<?> getMe(){

        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || "anonymousUser".equals(auth.getName())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String email = auth.getName();
        var user = userService.findByEmail(email);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Map.of("user", user));
    }

    // logout
    @PostMapping("/auth/logout")
    public void logout(HttpServletResponse response) throws IOException {
        response.sendRedirect("/logout");
    }


}