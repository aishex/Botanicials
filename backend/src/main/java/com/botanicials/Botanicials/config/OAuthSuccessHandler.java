package com.botanicials.Botanicials.config;

import com.botanicials.Botanicials.model.User;
import com.botanicials.Botanicials.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class OAuthSuccessHandler implements AuthenticationSuccessHandler {

    private final UserService userService;

    public OAuthSuccessHandler(UserService userService){
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException{

        OAuth2User oauthUser = (OAuth2User) authentication.getPrincipal();
        String email = oauthUser.getAttribute("email");
        User foundUser = userService.findByEmail(email);
        Long userId = foundUser.getId();
        String token = JwtUtil.generateToken(Map.of(
                "id", userId,
                "email", email
        ));

        Cookie cookie = new Cookie("auth", token);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setMaxAge(7 * 86400);
        cookie.setAttribute("SameSite", "Lax");

        response.addCookie(cookie);
        response.sendRedirect("http://localhost:5173/");
    }
}
