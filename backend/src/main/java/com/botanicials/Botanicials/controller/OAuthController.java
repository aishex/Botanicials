package com.botanicials.Botanicials.controller;

import com.botanicials.Botanicials.config.JwtUtil;
import com.botanicials.Botanicials.model.User;
import com.botanicials.Botanicials.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class OAuthController {

    @Autowired
    private UserService userService;

    // google redirect
    @GetMapping("/google/auth")
    public String redirectToGoogleAuth() {
        return "redirect:/oauth2/authorization/google";
    }

    // callback
    @GetMapping("/google/auth/callback")
    public void callback(@AuthenticationPrincipal OAuth2User user, HttpServletResponse response){
        if (user == null){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // jwt
        String email = user.getAttribute("email");
        User foundUser = userService.findByEmail(email);

        Long userId = foundUser.getId();
        String token = JwtUtil.generateToken(Map.of("id", userId));

        Cookie cookie = new Cookie("auth", token);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setMaxAge(7 * 86400);
        cookie.setAttribute("SameSite", "Lax");
        response.addCookie(cookie);

        response.setStatus(HttpServletResponse.SC_FOUND);
        response.setHeader("Location", "http://localhost:3000/");
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
