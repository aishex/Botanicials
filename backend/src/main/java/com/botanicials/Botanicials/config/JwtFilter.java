package com.botanicials.Botanicials.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Component
public class JwtFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException{
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException{

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // get cookie
        Optional<Cookie> authCookie = Arrays.stream(httpRequest.getCookies())
                .filter(cookie -> "auth".equals(cookie.getName()))
                .findFirst();

        if (authCookie.isPresent()){
            String token = authCookie.get().getValue();

            try{
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(JwtUtil.getKey())
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

                httpRequest.setAttribute("userClaims", claims);
            } catch (Exception e) {
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy(){
    }
}
