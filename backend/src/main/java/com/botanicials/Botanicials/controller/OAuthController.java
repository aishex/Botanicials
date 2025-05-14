package com.botanicials.Botanicials.controller;

import com.botanicials.Botanicials.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
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
    public Map<String, Object> getCurrentUser(@AuthenticationPrincipal Map<String, Object> user){
        if (user == null || user.isEmpty()){
            return Map.of();
        }
        return user;
    }
}