package com.botanicials.Botanicials.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class OAuthController {

    @GetMapping("/api/me")
    public Map<String, Object> getCurrentUser(@AuthenticationPrincipal OAuth2User user){
        if (user == null){
            return Map.of();
        }
        return Map.of(
                "name", user.getAttribute("name"),
                "email", user.getAttribute("email"),
                "image", user.getAttribute("image")
        );
    }
}
