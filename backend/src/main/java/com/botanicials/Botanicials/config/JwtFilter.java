package com.botanicials.Botanicials.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException{

        Cookie[] cookies = request.getCookies();
        Optional<Cookie> authCookie = cookies != null
                ? Arrays.stream(cookies)
                .filter(cookie -> "auth".equals(cookie.getName()))
                .findFirst()
                : Optional.empty();

        if (authCookie.isPresent()){
            String token = authCookie.get().getValue();

            try{
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(JwtUtil.getKey())
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

                request.setAttribute("userClaims", claims);
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
